package net.loyintean.blog.jms;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Resource
    private JmsTemplate jmsTemplate;
    @Resource
    private Queue rediliverQueue;
    @Resource
    private Queue concurrencyQueue;

    public void send_concurrency(String msg) {
        this.jmsTemplate.convertAndSend(this.concurrencyQueue, msg);
    }

    public void send_rediliver(String msg) {
        this.jmsTemplate.convertAndSend(this.rediliverQueue, msg);
    }
}