/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.producer;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author linjun
 * @since 2017年7月11日
 */
public class Producer {
    private static final Logger LOGGER = org.slf4j.LoggerFactory
        .getLogger(Producer.class);

    private JmsTemplate jmsMessagingTemplate;

    private Destination destination;

    public Producer(JmsTemplate jmsMessagingTemplate, Destination destination) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.destination = destination;
    }

    public void send(String msg) {
        Producer.LOGGER.info("text send:{}", msg);

        this.jmsMessagingTemplate.convertAndSend(this.destination, msg);
    }
}
