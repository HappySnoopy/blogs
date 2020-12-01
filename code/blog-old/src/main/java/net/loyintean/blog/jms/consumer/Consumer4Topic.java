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
public class Consumer4Topic {
    private static final Logger LOGGER = org.slf4j.LoggerFactory
        .getLogger(Consumer4Topic.class);

    @JmsListener(containerFactory = "jmsListenerContainerTopic",
            concurrency = "5-10", destination = "topic.test")
    public void receiveQueue(String text) {
        Consumer4Topic.LOGGER.info("text received:{}", text);

        System.out.println("text received: " + text);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
