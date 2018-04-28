package com.gzcb.creditcard;

import com.gzcb.creditcard.job.DemoJob;
import com.gzcb.creditcard.utils.PropertiesUtil;
import com.gzcb.creditcard.utils.QuartzUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.util.Properties;

/**
 * @SpringBootApplication 默认集成了@Configuration @EnableAutoConfiguration @ComponentScan @EnableWebMvc
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class,args);

//        QuartzUtil.scheduler(new PropertiesUtil("/core.properties").getString("cron_schedule"), DemoJob.class);
    }
}
