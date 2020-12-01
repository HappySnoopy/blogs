/**
 * All Rights Reserved
 */
package net.loyintean.blog.cache;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 被缓存的基础操作
 *
 * @author Snoopy
 * @since 2017年9月27日
 */
@Service
public class BasicOperator {

    @Cacheable("names")
    public String read(String name) {
        System.out.println(name);
        return name;
    }

    @CachePut("names")
    public String create(String name) {
        System.out.println(name);
        return name + RandomStringUtils.random(3);
    }

    @CachePut("names")
    public String update(String name) {
        System.out.println(name);
        return name + RandomStringUtils.random(3);
    }

    @CacheEvict("names")
    public void delete(String name) {
        System.out.println(name);
    }
}
