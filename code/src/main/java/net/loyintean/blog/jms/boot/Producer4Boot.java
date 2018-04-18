/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.boot;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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


    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    @Qualifier("topic")
    private Destination destination;

    public void send(String msg) {
        Producer4Boot.LOGGER.info("text send:{}", msg);

        this.jmsMessagingTemplate.convertAndSend(this.destination, msg);
    }
}
