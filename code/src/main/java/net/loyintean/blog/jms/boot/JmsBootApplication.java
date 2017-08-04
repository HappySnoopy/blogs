/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.boot;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;

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
    public ConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(
            ActiveMQConnectionFactory.DEFAULT_USER,
            ActiveMQConnectionFactory.DEFAULT_PASSWORD,
            "failover:(tcp://127.0.0.1:61616,tcp://10.255.33.108:61616)?randomize=false");
        //        return activeMQConnectionFactory;
        //        return new PooledConnectionFactory(activeMQConnectionFactory);
        //        return new SingleConnectionFactory(activeMQConnectionFactory);
        return new CachingConnectionFactory(activeMQConnectionFactory);
    }
}
