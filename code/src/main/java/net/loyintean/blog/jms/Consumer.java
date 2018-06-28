package net.loyintean.blog.jms;

import java.util.Random;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Component;

@Component
public class Consumer implements MessageListener {
    private Random r = new Random(System.currentTimeMillis());

    /**
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    @Override
    public void onMessage(Message message) {

        TextMessage textMessage = (TextMessage) message;
        

        try {
            String threadName = Thread.currentThread().getName();
            String text = textMessage.getText();

            System.out.println(threadName + ":" + text);

            this.r.nextBoolean();

        } catch (JMSException e) {
            e.printStackTrace();
            throw JmsUtils.convertJmsAccessException(e);
        }

    }

}