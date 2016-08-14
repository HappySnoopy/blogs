package net.loyintean.blog.jms;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Queue rediliverQueue;
    @Autowired
    private Queue concurrencyQueue;

    public void send_concurrency(String msg) {
        this.jmsTemplate.convertAndSend(this.concurrencyQueue, msg);
    }

    public void send_rediliver(String msg) {
        this.jmsTemplate.convertAndSend(this.rediliverQueue, msg);
    }
}