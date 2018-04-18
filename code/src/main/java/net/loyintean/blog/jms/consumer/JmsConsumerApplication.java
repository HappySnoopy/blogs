/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.consumer;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;

/**
 * @author linjun
 * @since 2017年7月11日
 */
@EnableJms
@SpringBootApplication
public class JmsConsumerApplication
        implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args) {
        SpringApplication.run(JmsConsumerApplication.class, args);
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

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(
            ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }

    /**
     * @author linjun
     * @since 2017年11月2日
     * @param container
     * @see org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer#customize(org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer)
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8081);
    }
}
