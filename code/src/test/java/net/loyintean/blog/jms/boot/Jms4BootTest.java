/**
 * Copyright(c) 2011-2017 by  Inc.
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

        IntStream.range(0, 10000).parallel().mapToObj(i -> "message_" + i)
            .forEach(msg -> {
                try {
                    this.producer4Boot.send(msg);
                } catch (Exception e) {
                    System.out.println(
                        "=============================" + e.getMessage());
                    Jms4BootTest.FAIL_MESSAGE_SET.put(msg, e.getMessage());
                }

            });

        long end = System.currentTimeMillis();

        System.out.println(this.activeMQConnectionFactory.getClass().getName()
            + " : " + (end - start));

        while (true) {
            System.out.println(Producer4Boot.MESSAGE_SET.size() + " : "
                + Jms4BootTest.FAIL_MESSAGE_SET.size());
            try {
                Thread.sleep(10000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Producer4Boot.MESSAGE_SET + " : "
                + Jms4BootTest.FAIL_MESSAGE_SET);
        }
    }
}
