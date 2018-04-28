package com.gzcb.creditcard.job;

import com.gzcb.creditcard.dao.entities.TContent;
import com.gzcb.creditcard.utils.ResponseUtil;
import com.gzcb.creditcard.vo.JobVo;
import com.gzcb.creditcard.websocket.IdoWebSocket;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseJob implements Job{
    static Logger logger = LoggerFactory.getLogger(BaseJob.class);


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        JobVo job = (JobVo) jobDataMap.get("job");
        logger.info("BaseJob开始执行任务({})({})", job.getMobile(), job.getName());
        try {
            pushMessage(job);
        } catch (Exception e) {
            logger.error("推送消息失败!");
        }
    }
    public void pushMessage(JobVo jobVo)throws Exception{
        IdoWebSocket.sendMessage(jobVo.getMobile(),ResponseUtil.succeed(jobVo).toJSONString());
    }
}

