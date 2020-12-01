package net.loyintean.blog.scheduer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author LAP02F2170925
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, EurekaClientAutoConfiguration.class})
@EnableScheduling
public class SchedulerApplication implements ApplicationContextInitializer {

    /**
     * 内置tomcat的初始化
     *
     * @param args
     * @author Snoopy
     * @since 2017年6月20日
     */
    public static void main(String[] args) {

        SpringApplication.run(SchedulerApplication.class, args);
    }

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        configurableApplicationContext.getEnvironment().addActiveProfile("dev");
    }
}
