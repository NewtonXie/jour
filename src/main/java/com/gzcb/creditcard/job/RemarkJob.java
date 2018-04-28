package com.gzcb.creditcard.job;

import com.gzcb.creditcard.dao.entities.TContent;
import com.gzcb.creditcard.dao.entities.TJour;
import com.gzcb.creditcard.utils.ResponseUtil;
import com.gzcb.creditcard.vo.JobVo;
import com.gzcb.creditcard.websocket.IdoWebSocket;
import jdk.nashorn.internal.scripts.JO;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 备忘录
 */
public class RemarkJob implements Job {
    static Logger logger = LoggerFactory.getLogger(BaseJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        JobVo job = (JobVo) jobDataMap.get("job");
        logger.info("RemarkJob开始执行WebSocket任务({})({})", job.getId(), job.getMobile(),job.getContent());
        try {
            pushRemark(job);
        } catch (Exception e) {
            logger.error("定时备忘录失败");
        }
    }
    public void pushRemark(JobVo jobVo)throws Exception{
        IdoWebSocket.sendMessage(jobVo.getMobile(),ResponseUtil.succeed(jobVo).toJSONString());
    }
}
