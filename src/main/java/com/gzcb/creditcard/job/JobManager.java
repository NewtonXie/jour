package com.gzcb.creditcard.job;

import com.gzcb.creditcard.controller.AdminController;
import com.gzcb.creditcard.dao.entities.TContent;
import com.gzcb.creditcard.dao.entities.TJour;
import com.gzcb.creditcard.service.ContentService;
import com.gzcb.creditcard.service.JourService;
import com.gzcb.creditcard.service.UserService;
import com.gzcb.creditcard.utils.BeanUtil;
import com.gzcb.creditcard.utils.ResponseUtil;
import com.gzcb.creditcard.vo.JobVo;
import com.gzcb.creditcard.vo.RedisVo;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class JobManager {

    private static Logger logger = LoggerFactory.getLogger(JobManager.class);

    private SchedulerFactory schedulerfactory;
    private static JobManager jobManager = null;
    private Scheduler scheduler;

    private ContentService contentService = BeanUtil.getApplicationContext().getBean(ContentService.class);

    private JourService jourService = BeanUtil.getApplicationContext().getBean(JourService.class);

    private AdminController adminController = BeanUtil.getApplicationContext().getBean(AdminController.class);

    private UserService userService = BeanUtil.getApplicationContext().getBean(UserService.class);

    public JobManager() {

    }

    public static JobManager getJobManager() {
        if (jobManager == null) {
            synchronized (JobManager.class) {
                if (jobManager == null) {
                    jobManager = new JobManager();
                }
            }
        }
        return jobManager;
    }

    public void init() {
        try {
            logger.info("JobManager init");
            schedulerfactory = new StdSchedulerFactory();
            scheduler = schedulerfactory.getScheduler();
            scheduler.start();
            addAllJob();
        } catch (Exception e) {
            logger.error("初始化定时管理器出错", e);
        }
    }
    public boolean isNotNull(String s){
        if(null!=s&&!"".equals(s)){
            return true;
        }
        return false;
    }

    /**
     * 推送消息指定号码推送
     */
    public void pushJobByMobile(String mobile){
        List<RedisVo> redisVoList= adminController.getMessage(mobile);
        logger.info("BaseJob:{}",ResponseUtil.succeed(redisVoList));
        if (null==redisVoList||redisVoList.size()<=0) {
            logger.info("没有推送信息！");
        } else {
            redisVoList.forEach(tContent -> {
                try {
                    JobVo job = new JobVo();
                    job.setCron(tContent.getCron());
                    job.setGroup(BaseJob.class.getName());
                    job.setId(tContent.getId());
                    job.setName(tContent.getName());
                    job.setContent(tContent.getContent());
                    job.setMobile(mobile);
                    if (isNotNull(job.getCron())&&isNotNull(job.getGroup())){
                        addJob(job);
                    }else {
                        logger.info("发送socket");
                        MessageOne.pushMessage(job);
                    }
                } catch (Exception e) {
                    logger.error("添加定时任务({})({})失败", tContent.getId(), tContent.getName());
                }
            });
        }
    }
    public void addAllJob() {
        /**
         * 添加推送信息为所有人
         */
        try {
            userService.selectAllUser().forEach(user->{
                pushJobByMobile(user.getMobile());
            });
        } catch (Exception e) {
            logger.error("添加定时推送信息失败");
        }
        /**
         * 备忘录信息
         */
        try {
            List<TJour> tJours = jourService.selectAllJour(null, 3);
            logger.info("{}",ResponseUtil.succeed(tJours));
            if (tJours.isEmpty()) {
                logger.info("没有备忘录信息！");
            } else {
                tJours.forEach(tContent -> {
                    try {
                        JobVo job = new JobVo();
                        job.setCron(tContent.getCron());
                        job.setGroup(RemarkJob.class.getName());
                        job.setId(tContent.getId());
                        job.setName(tContent.getName());
                        job.setContent(tContent.getJour());
                        job.setMobile(userService.selectUserById(tContent.getCreatedBy()).getMobile());
                        logger.info("{}",ResponseUtil.succeed(job));
                        addJob(job);
                    } catch (Exception e) {
                        logger.error("添加定时备忘录信息({})失败", tContent.getName());
                    }
                });
            }
        } catch (Exception e) {
            logger.error("添加备忘录信息定时失败");
        }
    }

    public void addJob(JobVo job) throws Exception {
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getName(), job.getGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (null == trigger) {
            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(job.getGroup());
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getName(), job.getGroup()).build();
            jobDetail.getJobDataMap().put("job", job);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
            trigger = TriggerBuilder.newTrigger().withIdentity(job.getName(), job.getGroup()).withSchedule(scheduleBuilder).build();
            logger.info("添加新job{}",job.getName());
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            ((CronTriggerImpl) trigger).setStartTime(new Date());
            logger.info("更新job{}",job.getName());
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }


    public boolean hasJob(JobVo job) throws Exception {
        JobKey jobkey = JobKey.jobKey(job.getName(), job.getGroup());
        return scheduler.checkExists(jobkey);
    }

    public void deleteJob(JobVo job) throws Exception {
        if (hasJob(job)) {
            JobKey jobkey = JobKey.jobKey(job.getName(), job.getGroup());
            logger.info("存在定时任务({})({}),删除任务", jobkey.getName() + ":" + jobkey.getGroup(), job.getId());
            scheduler.deleteJob(jobkey);
        }
    }
}