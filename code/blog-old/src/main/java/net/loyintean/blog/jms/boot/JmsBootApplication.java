/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.boot;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;

/**
 * @author linjun
 * @since 2017年7月11日
 */
@EnableJms
@SpringBootApplication
public class JmsBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmsBootApplication.class, args);
    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("spring-boot-test");
    }

    @Bean
    public Destination topic() {
        return new ActiveMQTopic("VirtualTopic.spring.boot.test");

    }

    /*
     * @Bean
     * public Destination topicCustomer() {
     * return new ActiveMQQueue(
     * "Consumer.springboot.VirtualTopic.springboot.test");
     * }
     */
    @Bean
    public ConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(
            ActiveMQConnectionFactory.DEFAULT_USER,
            ActiveMQConnectionFactory.DEFAULT_PASSWORD,
            "failover:(tcp://127.0.0.1:61616)");
        //        return activeMQConnectionFactory;
        //        return new PooledConnectionFactory(activeMQConnectionFactory);
        //        return new SingleConnectionFactory(activeMQConnectionFactory);
        CachingConnectionFactory factory = new CachingConnectionFactory(
            activeMQConnectionFactory);
        factory.setSessionCacheSize(100);

        return factory;
    }
}
