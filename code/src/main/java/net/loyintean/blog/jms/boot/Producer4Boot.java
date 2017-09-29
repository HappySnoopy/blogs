/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.boot;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Queue;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author linjun
 * @since 2017年7月11日
 */
@Service
public class Producer4Boot {
    private static final Logger LOGGER = org.slf4j.LoggerFactory
        .getLogger(Producer4Boot.class);

    public static final AtomicInteger COUNTER = new AtomicInteger(0);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void send(String msg) {
        Producer4Boot.LOGGER.info("text send:{}", msg);

        //        int currentCount = Producer4Boot.COUNTER.getAndIncrement();

        //        System.out.println("send count: " + currentCount);

        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }
}
