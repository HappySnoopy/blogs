/**
 * All Rights Reserved
 */
package net.loyintean.blog.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Snoopy
 * @since 2017年9月22日
 */
@SpringBootApplication
@EnableWebMvc
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
