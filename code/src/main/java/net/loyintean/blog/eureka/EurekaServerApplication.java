/**
 * All Rights Reserved
 */
package net.loyintean.blog.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author linjun
 * @since 2017年11月30日
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {


    /**
     * @author linjun
     * @since 2017年11月30日
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);

    }
}
