/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.manage;

import org.apache.activemq.broker.region.Destination;
import org.apache.activemq.broker.region.DestinationFilter;

/**
 * @author linjun
 * @since 2017年11月1日
 */
public class LinjunDetinationFilter extends DestinationFilter {

    /**
     * @param next
     */
    public LinjunDetinationFilter(Destination next) {
        super(next);
        FileUtils.write("new LinjunDetinationFilter");
    }

}
