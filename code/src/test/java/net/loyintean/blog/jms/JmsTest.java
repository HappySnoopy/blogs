/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.jms;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author winters1224@163.com
 */

@ContextConfiguration("/jms.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class JmsTest {

    @Resource
    private Producer producer;

    @Test
    public void test_concurrency() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            this.producer.send_concurrency("abc_" + i);
        }
        // 为了保证所有重试都完成
        Thread.sleep(1000000000l);
    }

    @Test
    public void test_rediliver() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            this.producer.send_rediliver("abc_" + i);
        }

        // 为了保证所有重试都完成
        Thread.sleep(1000000000l);
    }

}
