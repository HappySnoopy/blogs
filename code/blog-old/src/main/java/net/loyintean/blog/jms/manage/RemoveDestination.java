/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.manage;

import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerFilter;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.region.Destination;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.util.BrokerSupport;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 自动移除限制的Destination
 *
 * @author Snoopy
 * @since 2017年10月30日
 */
public class RemoveDestination extends BrokerFilter {

    private Timer timer;

    /**
     * @param next
     */
    public RemoveDestination(Broker next) {
        super(next);

        // 声明为守护线程，避免它阻塞关闭activeMQ的进程
        this.timer = new Timer(true);
    }

    @Override
    public void start() throws Exception {
        super.start();

        // DONE Snoopy 2017-11-01 改为定时调度
        this.timer.schedule(new TimerTask() {

            @Override
            public void run() {
                RemoveDestination.this.remove();
            }
        }, 3000, 3000);
    }

    private void remove() {

        Map<ActiveMQDestination, Destination> destinationMap = this
            .getDestinationMap();
        ConnectionContext context = BrokerSupport.getConnectionContext(this);

        destinationMap.entrySet().forEach(entry -> {
            Destination destination = entry.getValue();

            // 无人监听了
            // DONE Snoopy 2017-11-01 只处理queue，不处理topic
            if (destination.getDestinationStatistics().getConsumers()
                .getCount() == 0) {
                ActiveMQDestination activeMQDestination = entry.getKey();
                if (activeMQDestination.isQueue()) {
                    try {
                        this.removeDestination(context,
                            activeMQDestination, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        });

    }

}
