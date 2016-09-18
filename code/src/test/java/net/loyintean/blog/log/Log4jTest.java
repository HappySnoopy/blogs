/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author linjun
 */
public class Log4jTest {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(Log4jTest.class);

    @Test
    public void test() {

        MDC.put("cord", "123123");

        Log4jTest.LOGGER.info(Log4jTest.LOGGER.getClass().getName());
        // 2016-01-27 16:27:30,325 [INFO ] Log4jTest#test()@27  - org.slf4j.impl.Log4jLoggerAdapter

    }

    @Test
    public void test1() {

        MDC.put("cord", "bb234");

        Log4jTest.LOGGER.info(Log4jTest.LOGGER.getClass().getName());
        // 2016-01-27 16:27:30,325 [INFO ] Log4jTest#test()@27  - org.slf4j.impl.Log4jLoggerAdapter

        Log4jTest.LOGGER.error("this is error!!!!");
    }
}
