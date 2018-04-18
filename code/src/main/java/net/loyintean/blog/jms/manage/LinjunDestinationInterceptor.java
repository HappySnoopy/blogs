/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.manage;

import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.region.Destination;
import org.apache.activemq.broker.region.DestinationInterceptor;
import org.apache.activemq.command.ActiveMQDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author linjun
 * @since 2017年11月1日
 */
public class LinjunDestinationInterceptor implements DestinationInterceptor {

    /**
     * 日志
     *
     * @author linjun
     * @since 2017年11月1日
     */
    private static final Logger LOGGER = LoggerFactory
        .getLogger(LinjunDestinationInterceptor.class);

    /**
     * @author linjun
     * @since 2017年11月1日
     * @param destination
     * @return
     * @see org.apache.activemq.broker.region.DestinationInterceptor#intercept(org.apache.activemq.broker.region.Destination)
     */
    @Override
    public Destination intercept(Destination destination) {
        FileUtils.write("创建LinjunDetinationFilter");
        LinjunDestinationInterceptor.LOGGER.info("创建LinjunDetinationFilter");
        return new LinjunDetinationFilter(destination);
    }

    /**
     * @author linjun
     * @since 2017年11月1日
     * @param destination
     * @see org.apache.activemq.broker.region.DestinationInterceptor#remove(org.apache.activemq.broker.region.Destination)
     */
    @Override
    public void remove(Destination destination) {
        FileUtils.write("删除LinjunDetinationFilter");
        LinjunDestinationInterceptor.LOGGER.info("删除LinjunDetinationFilter");
    }

    /**
     * @author linjun
     * @since 2017年11月1日
     * @param broker
     * @param context
     * @param destination
     * @see org.apache.activemq.broker.region.DestinationInterceptor#create(org.apache.activemq.broker.Broker,
     *      org.apache.activemq.broker.ConnectionContext,
     *      org.apache.activemq.command.ActiveMQDestination)
     */
    @Override
    public void create(Broker broker, ConnectionContext context,
            ActiveMQDestination destination) {
        FileUtils.write("create LinjunDetinationFilter ");
        LinjunDestinationInterceptor.LOGGER
            .info("createLinjunDetinationFilter");
    }
}
