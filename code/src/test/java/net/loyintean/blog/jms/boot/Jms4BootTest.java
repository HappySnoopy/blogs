/**
 * Copyright(c) 2011-2017 by Inc.
 * All Rights Reserved
 */
package net.loyintean.blog.jms.boot;

import java.util.stream.IntStream;

import javax.annotation.Resource;

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

    @Test
    public void test() {
        long start = System.currentTimeMillis();

        IntStream.range(0, 10000).mapToObj(i -> "message_" + i).forEach(msg -> {
            try {
                this.producer4Boot.send(msg);

                //                    Thread.sleep(5000l);

            } catch (Exception e) {
                System.out
                    .println("=============================" + e.getMessage());
            }

            //            System.out.println("=============================" + msg);

        });

        long end = System.currentTimeMillis();

        System.out.println(end - start);

        try {
            Thread.sleep(500000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
