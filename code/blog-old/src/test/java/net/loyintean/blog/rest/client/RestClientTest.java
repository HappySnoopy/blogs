/**
 * All Rights Reserved
 */
package net.loyintean.blog.rest.client;

import net.loyintean.blog.repay.Result4Repay;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Snoopy
 * @since 2017年9月22日
 */
public class RestClientTest {

    private RestClientFactory factory;

    @Before
    public void setUp() {
        this.factory = new RestClientFactory();
        this.factory.setConnectionTimeout(3000);
        this.factory.setSocketTimeout(5000);
    }

    @Test
    public void test() {
        try {
            Result4Repay result = this.factory.newClient()
                .setUrl("http://localhost:8080/test/timeout1")
            .responseAs(Result4Repay.class).get();

            Assert.assertNull(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
