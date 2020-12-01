/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.manage;

import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerPlugin;

/**
 * @author Snoopy
 * @since 2017年10月30日
 */
public class PlugIn implements BrokerPlugin {
    /**
     * @param b
     * @return
     * @author Snoopy
     * @see org.apache.activemq.broker.BrokerPlugin#installPlugin(org.apache.activemq.broker.Broker)
     * @since 2017年10月30日
     */
    @Override
    public Broker installPlugin(Broker b) {
        return new RemoveDestination(b);
    }
}
