/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.manage;

import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerPlugin;

/**
 * @author linjun
 * @since 2017年10月30日
 */
public class PlugIn implements BrokerPlugin {
    /**
     * @author linjun
     * @since 2017年10月30日
     * @param b
     * @return
     * @see org.apache.activemq.broker.BrokerPlugin#installPlugin(org.apache.activemq.broker.Broker)
     */
    @Override
    public Broker installPlugin(Broker b) {
        return new RemoveDestination(b);
    }
}
