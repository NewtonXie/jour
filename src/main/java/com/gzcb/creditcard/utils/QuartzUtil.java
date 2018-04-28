package com.gzcb.creditcard.utils;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created at 2018/1/31.
 * @author yjz
 */
public class QuartzUtil {

    private static final Logger logger = LoggerFactory.getLogger(QuartzUtil.class);

    private static final String DEFAULT_JOB = "defaultJob";

    private static final String DEFAULT_GROUP = "defaultGroup";

    private static final String DEFAULT_TRIGGER_NAME = "triggerName";

    private static final String DEFAULT_TRIGGER_GROUP = "triggerGroup";

    private static Scheduler scheduler = null;

    static{
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error("[atac][quartz] init error",e);
        }
    }

    public static void scheduler(String cron, Class jobClass) throws Exception {
        QuartzUtil.scheduler(cron,DEFAULT_GROUP,DEFAULT_JOB,
                DEFAULT_TRIGGER_NAME,DEFAULT_TRIGGER_GROUP,jobClass);
    }

    public static void scheduler(String corn, String groupId, String jobId, String triggerName, String triggerGroup, Class jobClass) throws Exception {
        if(corn != null && !"".equals(corn)){
            JobDetail job = JobBuilder.newJob(jobClass).withIdentity(jobId,groupId).build();
            Trigger trigger = TriggerBuilder.newTrigger().
                    withIdentity(triggerName,triggerGroup)
                    .startNow().withSchedule(CronScheduleBuilder.cronSchedule(corn)).build();
            try {
                scheduler.scheduleJob(job,trigger);
            } catch (SchedulerException e) {
                logger.error("[atac][quartz] schedule job error",e);
            }
        }else{
            throw new RuntimeException("Cron Expression can not be null");
        }
    }
}
