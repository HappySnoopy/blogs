/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.boot;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    public static final Map<String, String> MESSAGE_SET = new ConcurrentHashMap<>();

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void send(String msg) {
        Producer4Boot.LOGGER.info("text send:{}", msg);
        Producer4Boot.MESSAGE_SET.put(msg, msg);
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }
}
