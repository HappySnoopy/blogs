/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.consumer;

import org.slf4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author Snoopy
 * @since 2017年7月11日
 */
@Service
public class Consumer4Queue {
    private static final Logger LOGGER = org.slf4j.LoggerFactory
        .getLogger(Consumer4Queue.class);

    @JmsListener(
            destination = "queue.test",
            concurrency = "5-10")
    public void receiveQueue(String text) {
        Consumer4Queue.LOGGER.info("text received:{}", text);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
