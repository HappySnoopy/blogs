/**
 * All Rights Reserved
 */
package net.loyintean.blog.rest.client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author winters1224@163.com
 */
public class RestClient4AsyncTest {
    @Test
    public void test_aysnc() throws InterruptedException, ExecutionException,
            TimeoutException {

        RestClient4Async client4Async = new RestClient4Async(
            new RestClientAsDefault() {
                @Override
                public <T> T get() {
                    try {
                        Thread.sleep(4000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return (T) "abc";
                }
            }, Executors.newFixedThreadPool(1));

        Future<String> result = null;
        long start = System.currentTimeMillis();
        result = client4Async.responseAs(String.class).getAsync();
        long inner = System.currentTimeMillis();

        // start 和 inner 差距在1s上下
        Assert.assertTrue(inner - start < 1010l);

        result.get(5, TimeUnit.SECONDS);
        long end = System.currentTimeMillis();

        // end 和 inner 差距在4s上下
        Assert.assertTrue(end - inner < 4010l);

        System.out.println(start + "," + inner + "," + end);
    }
}
