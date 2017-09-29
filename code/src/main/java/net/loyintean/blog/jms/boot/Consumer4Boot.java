/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.boot;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author linjun
 * @since 2017年7月11日
 */
@Service
public class Consumer4Boot {
    private static final Logger LOGGER = org.slf4j.LoggerFactory
        .getLogger(Consumer4Boot.class);

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    private static long start = 0l;

    private static long end = 0l;

    @JmsListener(destination = "spring-boot-test", concurrency = "5-10")
    public void receiveQueue(String text) {
        Consumer4Boot.LOGGER.info("text received:{}", text);
        //        Producer4Boot.MESSAGE_SET.remove(text);
        int currentCount = Consumer4Boot.COUNTER.getAndIncrement();

        //        System.out.println("recieve count: " + currentCount);

        if (999 == currentCount) {
            Consumer4Boot.end = System.currentTimeMillis();

            System.out.println("==========================="
                + (Consumer4Boot.end - Consumer4Boot.start));
        }

        if (currentCount == 1) {
            Consumer4Boot.start = System.currentTimeMillis();
            System.out.println(
                "=========================start at:" + Consumer4Boot.start);
        }
    }
}
