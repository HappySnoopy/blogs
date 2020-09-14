package net.loyintean.springmvcbase.common.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记方法需要做重复请求处理。
 * <p>
 * 包括：
 * 1. 并发处理。
 * 通过 {@link #lockPrefix()} 和{@link #lockKey()}来指定分布式锁的key
 * 通过{@link #lockWaitTime()}和{@link #lockReleaseTime()}来指定分布式锁的等待时间和释放时间
 * <p>
 * 2. 幂等处理。
 * 通过 {@link #idempotentBean()}来指定幂等处理的bean
 *
 * @author Snoopy
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Repeatable {

    /**
     * 并发处理时，分布式锁的前缀
     *
     * @return 如果为空字符串，则等价于不加前缀
     */
    String lockPrefix() default "";

    /**
     * 分布式锁的key
     * <p>
     * 可以是简单的字符串，也可以是SPEL表达式。
     * <p>
     * SPEL表达式中，可以使用标记方法的参数名。如
     * <code public String query(String id){...} />
     * 则可以用 #id 作为key值
     *
     * @return 如果为空字符串，意为不加分布式锁
     */
    String lockKey() default "";

    /**
     * 分布式锁的等待时间。在这个时长内没有拿到锁，则判定为获取失败。
     * <p>
     * 单位：毫秒。
     *
     * @return 默认10毫秒。设置为0或者负数的效果，需要视底层实现而定。
     */
    long lockWaitTime() default 10L;

    /**
     * 分布式锁的释放时间。从拿到锁之后，在这个时长内，即使没有手动释放，也会自动释放分布式锁。
     * <p>
     * 单位：毫秒。
     *
     * @return 默认10秒。不可设置为0或负数。
     */
    long lockReleaseTime() default 10_000L;

    /**
     * 用于幂等处理的bean的名称。
     * <p>
     * 根据该名称，应当可以从Spring上下文中获取一个实例。
     * <p>
     * 该实例必须是被注解标记的类的一个子类。当前方法会用该子类实现的被标记方法来做幂等处理。
     * <p>
     * 如果幂等处理实例的方法返回null，则调用被标记的类的方法；如果返回非null，则直接返回该返回结果。
     *
     * @return 幂等处理实例的bean名称
     */
    String idempotentBean() default "";
}
