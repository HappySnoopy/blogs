package net.loyintean.blog.redislock.redisson;

import javax.annotation.PostConstruct;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by fangzhipeng on 2017/4/5.
 * 获取RedissonClient连接类
 */
@Component
public class RedissonConnector {

   private RedissonClient redisson;

   @Autowired
   private Config config;

    @PostConstruct
    public void init(){
        this.redisson = Redisson.create(this.config);
    }

    public RedissonClient getClient(){
        return this.redisson;
    }

}