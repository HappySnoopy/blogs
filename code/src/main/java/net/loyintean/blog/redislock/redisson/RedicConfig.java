/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package net.loyintean.blog.redislock.redisson;

import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author linjun
 * @since 2017年6月16日
 *
 */
@SpringBootApplication
public class RedicConfig {

    public static void main(String[] args) {
        SpringApplication.run(RedicConfig.class, args);
    }


    @Bean
    public Config config() {
        Config config=new Config();
        config.useClusterServers()
              .addNodeAddress("10.255.33.29:9001")
              .addNodeAddress("10.255.33.29:9002")
              .addNodeAddress("10.255.33.29:9003")
              .addNodeAddress("10.255.33.29:9004")
              .addNodeAddress("10.255.33.29:9005")
              .addNodeAddress("10.255.33.29:9006");
        return config;
    }

}
