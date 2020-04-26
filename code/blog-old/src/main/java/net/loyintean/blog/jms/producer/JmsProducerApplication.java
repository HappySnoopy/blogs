/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.producer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

/**
 * @author linjun
 * @since 2017年7月11日
 */
@EnableJms
@SpringBootApplication
public class JmsProducerApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication
            .run(JmsProducerApplication.class, args);

        JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);

        Destination queue = ctx.getBean("queue", Destination.class);
        Producer queueProducer = new Producer(jmsTemplate, queue);

        Destination topic = ctx.getBean("topic", Destination.class);
        Producer topicProducer = new Producer(jmsTemplate, topic);

        Destination virtualTopic = ctx.getBean("virtualTopic",
            Destination.class);
        Producer virtualTopicProducer = new Producer(jmsTemplate, virtualTopic);

        int i = 0;
        while (true) {
            queueProducer.send("queueMessage_" + i);
            topicProducer.send("topicMessage_" + i);
            virtualTopicProducer.send("topicMessage_" + i);

            i++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Bean
    public Destination queue() {
        return new ActiveMQQueue("queue.test");
    }

    @Bean
    public Destination topic() {
        return new ActiveMQTopic("topic.test");

    }

    @Bean
    public Destination virtualTopic() {
        return new ActiveMQTopic("VirtualTopic.spring.boot.test");

    }

    @Bean
    public ConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(
            ActiveMQConnectionFactory.DEFAULT_USER,
            ActiveMQConnectionFactory.DEFAULT_PASSWORD,
            "failover:(tcp://127.0.0.1:61616)");

        CachingConnectionFactory factory = new CachingConnectionFactory(
            activeMQConnectionFactory);
        factory.setSessionCacheSize(100);

        return factory;
    }
}
