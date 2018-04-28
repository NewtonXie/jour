package com.gzcb.creditcard.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gcb on 2018/2/1.
 * @author yjz
 */
public class DemoJob implements Job{

    private static Logger logger = LoggerFactory.getLogger(DemoJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("I am running.....");
        try {
            Long l1 = System.currentTimeMillis();
            System.out.println("ddddd");
            Long l2 = System.currentTimeMillis();
            logger.info(l2-l1+"毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
