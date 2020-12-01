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
 * @author Snoopy
 * @since 2017年11月1日
 */
public class SnoopyDestinationInterceptor implements DestinationInterceptor {

    /**
     * 日志
     *
     * @author Snoopy
     * @since 2017年11月1日
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(SnoopyDestinationInterceptor.class);

    /**
     * @param destination
     * @return
     * @author Snoopy
     * @see org.apache.activemq.broker.region.DestinationInterceptor#intercept(org.apache.activemq.broker.region.Destination)
     * @since 2017年11月1日
     */
    @Override
    public Destination intercept(Destination destination) {
        FileUtils.write("创建SnoopyDetinationFilter");
        SnoopyDestinationInterceptor.LOGGER.info("创建SnoopyDetinationFilter");
        return new SnoopyDetinationFilter(destination);
    }

    /**
     * @param destination
     * @author Snoopy
     * @see org.apache.activemq.broker.region.DestinationInterceptor#remove(org.apache.activemq.broker.region.Destination)
     * @since 2017年11月1日
     */
    @Override
    public void remove(Destination destination) {
        FileUtils.write("删除SnoopyDetinationFilter");
        SnoopyDestinationInterceptor.LOGGER.info("删除SnoopyDetinationFilter");
    }

    /**
     * @param broker
     * @param context
     * @param destination
     * @author Snoopy
     * @see org.apache.activemq.broker.region.DestinationInterceptor#create(org.apache.activemq.broker.Broker,
     * org.apache.activemq.broker.ConnectionContext,
     * org.apache.activemq.command.ActiveMQDestination)
     * @since 2017年11月1日
     */
    @Override
    public void create(Broker broker, ConnectionContext context,
            ActiveMQDestination destination) {
        FileUtils.write("create SnoopyDetinationFilter ");
        SnoopyDestinationInterceptor.LOGGER
                .info("createSnoopyDetinationFilter");
    }
}
