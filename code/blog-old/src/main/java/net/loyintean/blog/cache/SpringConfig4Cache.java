/**
 * All Rights Reserved
 */
package net.loyintean.blog.cache;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * @author Snoopy
 * @since 2017年9月27日
 */
@Configuration
public class SpringConfig4Cache {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public CacheInterceptor cacheInterceptor() {
        CacheInterceptor interceptor = new CacheInterceptor4Blog();
        return interceptor;
    }

}
