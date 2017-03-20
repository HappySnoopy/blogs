/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package net.loyintean.blog.sisgod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author linjun
 */
@ComponentScan
@EnableAutoConfiguration
public class SpringMvcTest {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcTest.class, args);
    }
}
