/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.boot;

import org.slf4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author Snoopy
 * @since 2017年7月11日
 */
@Service
public class Consumer4Boot {
    private static final Logger LOGGER = org.slf4j.LoggerFactory
        .getLogger(Consumer4Boot.class);

    @JmsListener(
            destination = "Consumer.springboot.VirtualTopic.spring.boot.test",
            concurrency = "5-10")
    public void receiveQueue(String text) {
        Consumer4Boot.LOGGER.info("text received:{}", text);
    }
}
