/**
 * All Rights Reserved
 */
package net.loyintean.blog.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author Snoopy
 * @since 2017年9月27日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CacheApplication.class)
public class BasicOperatorTest {

    @Resource
    private BasicOperator basicOperator;

    @Resource
    private CacheInterceptor cacheInterceptor;

    @Resource
    private CacheManager cacheManager;

    private String name = "abc";

    @Test
    public void test_config() {
        System.out.println(this.cacheInterceptor.getClass().getName());
        System.out.println(this.cacheManager.getClass().getName());

    }

    @Test
    public void test() {

        this.basicOperator.read(this.name);
        this.basicOperator.read(this.name);

        this.basicOperator.delete(this.name);
        this.basicOperator.read(this.name);
        this.basicOperator.read(this.name);

    }
}
