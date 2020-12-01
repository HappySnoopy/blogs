/**
 * All Rights Reserved
 */
package net.loyintean.blog.jms.manage;

import org.apache.activemq.broker.region.Destination;
import org.apache.activemq.broker.region.DestinationFilter;

/**
 * @author Snoopy
 * @since 2017年11月1日
 */
public class SnoopyDetinationFilter extends DestinationFilter {

    /**
     * @param next
     */
    public SnoopyDetinationFilter(Destination next) {
        super(next);
        FileUtils.write("new SnoopyDetinationFilter");
    }

}
