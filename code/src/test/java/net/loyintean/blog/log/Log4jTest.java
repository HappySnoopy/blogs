/**
 *
 * All Rights Reserved
 */
package net.loyintean.blog.log;

import java.util.stream.IntStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author winters1224@163.com
 */
public class Log4jTest {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(Log4jTest.class);

    @Test
    public void test() {

        MDC.put("cord", "123123");

        Log4jTest.LOGGER.info(Log4jTest.LOGGER.getClass().getName());
        // 2016-01-27 16:27:30,325 [INFO ] Log4jTest#test()@27 -
        // org.slf4j.impl.Log4jLoggerAdapter

    }

    @Test
    public void test1() {

        MDC.put("cord", "bb234");

        Log4jTest.LOGGER.info(Log4jTest.LOGGER.getClass().getName());
        // 2016-01-27 16:27:30,325 [INFO ] Log4jTest#test()@27 -
        // org.slf4j.impl.Log4jLoggerAdapter

        Log4jTest.LOGGER.error("this is error!!!!");
    }

    /**
     * times=100000：<br>
     * 同步：8861，8605，8834<br>
     * AsyncAppender：1948，1735，1645。并且默认没有%c%m%l<br>
     * AsyncRoot:295,272,269。并且默认没有%c%m%l<br>
     * AsyncRoot+AsyncAppender：312，297，298。并且默认没有%c%m%l<br>
     * 全异步：249，293，174。并且默认没有%c%m%l<br>
     * 全异步+AsyncAppender：326，213，280。并且默认没有%c%m%l<br>
     *
     * @throws InterruptedException
     *
     */
    @Test
    public void test_performance() throws InterruptedException {
        int times = 100000;

        long start = System.currentTimeMillis();
        IntStream.range(1, times).mapToObj(i -> Integer.toString(i))
                .forEach(i -> LOGGER.error(i));
        long end = System.currentTimeMillis();

        Thread.sleep(10000l);
        System.out.println(end - start);
    }
}
