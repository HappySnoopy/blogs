/**
 * Copyright(c) 2011-2017 by Inc.
 * All Rights Reserved
 */
package net.loyintean.blog.jms.boot;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author linjun
 * @since 2017年7月11日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JmsBootApplication.class)
public class Jms4BootTest {
    @Resource
    private Producer4Boot producer4Boot;

    @Resource
    private ConnectionFactory activeMQConnectionFactory;

    public static final Map<String, String> FAIL_MESSAGE_SET = new ConcurrentHashMap<>();

    @Test
    public void test() {
        long start = System.currentTimeMillis();

        IntStream.range(0, 1000).mapToObj(i -> "message_" + i).forEach(msg -> {
            try {
                this.producer4Boot.send(msg);

                //                    Thread.sleep(5000l);

            } catch (Exception e) {
                System.out
                    .println("=============================" + e.getMessage());
                Jms4BootTest.FAIL_MESSAGE_SET.put(msg, e.getMessage());
            }

            //            System.out.println("=============================" + msg);

        });

        long end = System.currentTimeMillis();

        System.out.println(this.activeMQConnectionFactory.getClass().getName()
            + " : " + (end - start));

        try {
            Thread.sleep(500000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
