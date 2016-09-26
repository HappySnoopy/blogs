/**
 * All Rights Reserved
 */
package net.loyintean.blog.rest.client;

import java.util.ArrayList;
import java.util.List;

import net.loyintean.blog.rest.RetryConfiguration;
import net.loyintean.blog.rest.exception.RetryFailOverTimesException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author winters1224@163.com
 */
public class RestClient4RetryTest {
    @Test
    public void test_retry() {

        List<Integer> list = new ArrayList<>();

        RestClient restClient = new RestClientAsDefault() {

            @Override
            public <T> T get() {
                list.add(0);
                return null;
            }

        };

        RestClient retry = new RestClient4Retry(restClient,
            new RetryConfiguration());

        try {
            retry.get();
            Assert.fail();
        } catch (RetryFailOverTimesException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(list.size(), 4);
    }
}
