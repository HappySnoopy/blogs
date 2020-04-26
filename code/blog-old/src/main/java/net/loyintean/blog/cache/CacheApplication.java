/**
 * All Rights Reserved
 */
package net.loyintean.blog.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author linjun
 * @since 2017年9月27日
 */
@SpringBootApplication
@EnableCaching
class CacheApplication {

    /**
     * @author linjun
     * @since 2017年9月27日
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);

    }
}
