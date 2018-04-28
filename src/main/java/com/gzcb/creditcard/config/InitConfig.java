package com.gzcb.creditcard.config;

import com.gzcb.creditcard.job.JobManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletException;

@Configuration
public class InitConfig {

    private static Logger logger = LoggerFactory.getLogger(InitConfig.class);
    @Bean(name = "InitServletConfig")
    public JobManager InitServletConfig() throws ServletException {
        logger.info("初始化定时任务");
        try {
            JobManager.getJobManager().init();
        } catch (Exception e) {
            logger.error("初始化定时任务失败", e);
        }
        return JobManager.getJobManager();
    }

}
