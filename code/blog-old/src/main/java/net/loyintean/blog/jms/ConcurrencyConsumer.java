package net.loyintean.blog.jms;

import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.Random;

@Service
public class ConcurrencyConsumer implements MessageListener {

    private Random r = new Random(System.currentTimeMillis());

    /**
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    @Override
    public void onMessage(Message message) {

        TextMessage textMessage = (TextMessage) message;

        try {
            String threadName = Thread.currentThread().getName();

            System.out.println(threadName + ":" + textMessage.getText());

            boolean sleep = this.r.nextBoolean() && threadName.endsWith("2");

            if (sleep) {
                // 随机睡1s
                Thread.sleep(1000);
            }

        } catch (JMSException e) {
            e.printStackTrace();
            throw JmsUtils.convertJmsAccessException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}