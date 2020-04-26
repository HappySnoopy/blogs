/**
 * All Rights Reserved
 */
package net.loyintean.blog.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 当写缓存操作发生异常时，启动子线程，进行多次重试。
 * <p>
 * 不会对读缓存操作进行重试
 *
 * @author linjun
 * @since 2017年9月27日
 */
public class CacheErrorHandler4Retry implements CacheErrorHandler {

    /**
     * 日志
     *
     * @author linjun
     * @since 2017年9月27日
     */
    private static final Logger LOGGER = LoggerFactory
        .getLogger(CacheErrorHandler4Retry.class);

    /**
     * 重试次数
     *
     * @author linjun
     * @since 2017年9月27日
     */
    private int retryTimes = 3;

    /**
     * 重试步进时间（单位：秒）
     *
     * @author linjun
     * @since 2017年9月27日
     */
    private long retryStep = 100l;

    /**
     * 哪些异常需要重试
     * <p>
     * 默认为null，意为全都要重试
     *
     * @author linjun
     * @since 2017年9月27日
     */
    private Class<Throwable>[] includs;

    /**
     * 哪些异常需要重试
     * <p>
     * 默认为null，意为全都要重试
     *
     * @author linjun
     * @since 2017年9月27日
     */
    private Class<Throwable>[] excluds;

    /**
     * @author linjun
     * @since 2017年9月27日
     * @param exception
     * @param cache
     * @param key
     * @see org.springframework.cache.interceptor.CacheErrorHandler
     *      #handleCacheGetError(java.lang.RuntimeException,
     *      org.springframework.cache.Cache, java.lang.Object)
     */
    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache,
            Object key) {
        CacheErrorHandler4Retry.LOGGER.debug("读取缓存发生异常，但不做重试处理");
    }

    /**
     * 发生异常后，重试若干次
     * {@inheritDoc}
     *
     * @author linjun
     * @since 2017年9月27日
     * @param exception
     * @param cache
     * @param key
     * @param value
     * @see org.springframework.cache.interceptor.CacheErrorHandler
     *      #handleCachePutError(java.lang.RuntimeException,
     *      org.springframework.cache.Cache, java.lang.Object, java.lang.Object)
     */
    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache,
            Object key, Object value) {
        CacheErrorHandler4Retry.LOGGER.info(
            "put缓存发生异常，开始重试。retyrTimes:{},retryStep:{},cache:{},key:{},value:{}",
            this.retryTimes, this.retryStep, cache, key, value, exception);
        this.retry(exception, () -> cache.put(key, value));
    }

    /**
     * @author linjun
     * @since 2017年9月27日
     * @param exception
     * @param cache
     * @param key
     * @see org.springframework.cache.interceptor.CacheErrorHandler
     *      #handleCacheEvictError(java.lang.RuntimeException,
     *      org.springframework.cache.Cache, java.lang.Object)
     */
    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache,
            Object key) {
        CacheErrorHandler4Retry.LOGGER.info(
            "evict缓存发生异常，开始重试。retyrTimes:{},retryStep:{},cache:{},key:{}",
            this.retryTimes, this.retryStep, cache, key, exception);
        this.retry(exception, () -> cache.evict(key));
    }

    /**
     * @author linjun
     * @since 2017年9月27日
     * @param exception
     * @param cache
     * @see org.springframework.cache.interceptor.CacheErrorHandler
     *      #handleCacheClearError(java.lang.RuntimeException,
     *      org.springframework.cache.Cache)
     */
    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        CacheErrorHandler4Retry.LOGGER.info(
            "clear缓存发生异常，开始重试。retyrTimes:{},retryStep:{},cache:{}",
            this.retryTimes, this.retryStep, cache, exception);
        this.retry(exception, () -> cache.clear());
    }

    /**
     * 实际上的重试操作
     *
     * @author linjun
     * @since 2017年9月27日
     * @param cacheWriter
     */
    private void retry(RuntimeException exception, CacheWriter cacheWriter) {

        if (this.isIncluded(exception) && !this.isExcluded(exception)) {
            this.doRetry(cacheWriter);
        } else {
            CacheErrorHandler4Retry.LOGGER.warn(
                "根据参数配置，不对当前异常进行重试。exception:{},includes:{},excludes:{}",
                exception, Arrays.toString(this.includs),
                Arrays.toString(this.excluds));
        }
    }

    /**
     * 是否包含在需要重试的异常类中
     *
     * @author linjun
     * @since 2017年9月27日
     * @param e
     * @return {@link #includs}为null，或者入参e是{@link #includs}中某个类/接口的子类
     */
    private boolean isIncluded(Throwable e) {
        return this.includs == null || Stream.of(this.includs)
            .filter(t -> t.isAssignableFrom(e.getClass())).findAny()
            .isPresent();
    }

    /**
     * 是否包含在不需要重试的异常类中
     *
     * @author linjun
     * @since 2017年9月27日
     * @param e
     * @return {@link #excluds}非null，并且入参e是{@link #excluds}中某个类/接口的子类
     */
    private boolean isExcluded(Throwable e) {
        return this.excluds != null && Stream.of(this.excluds)
            .filter(t -> t.isAssignableFrom(e.getClass())).findAny()
            .isPresent();
    }

    private void doRetry(CacheWriter cacheWriter) {

        // 启动一个子线程，重试3次删除操作
        new Thread(() -> {
            int time = 0;
            while (time < this.retryTimes) {
                // 先等一小段时间，
                try {
                    Thread.sleep(time * this.retryStep);
                } catch (InterruptedException e) {
                    CacheErrorHandler4Retry.LOGGER.warn("重试发生线程中断异常", e);
                }

                // 重试一次
                try {
                    CacheErrorHandler4Retry.LOGGER.info(
                        "写缓存发生异常，开始重试。retyrTimes:{},retryStep:{},time:{}",
                        this.retryTimes, this.retryStep, time);
                    cacheWriter.write();
                    break;
                } catch (Exception e) {
                    // 如果发生异常，再次重试
                    if (time < this.retryTimes) {
                        CacheErrorHandler4Retry.LOGGER.warn(
                            "重试发生异常，再次重试。time:{},retryTimes:{}", time,
                            this.retryTimes, e);
                    } else {
                        CacheErrorHandler4Retry.LOGGER.error(
                            "重试发生异常。已达重试次数上限，不再重试。time:{},retryTimes:{}", time,
                            this.retryTimes, e);
                    }
                    time++;
                }
            }
        }).start();
    }

    /**
     * 写缓存操作接口
     *
     * @author linjun
     * @since 2017年9月27日
     */
    interface CacheWriter {

        /**
         * 执行实际写入操作
         *
         * @author linjun
         * @since 2017年9月27日
         */
        void write();
    }

    /**
     * @param retryTimes
     *        the {@link #retryTimes} to set
     */
    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    /**
     * @param retryStep
     *        the {@link #retryStep} to set
     */
    public void setRetryStep(long retryStep) {
        this.retryStep = retryStep;
    }

    /**
     * @param includs
     *        the {@link #includs} to set
     */
    public void setIncluds(Class<Throwable>[] includs) {
        this.includs = includs;
    }

    /**
     * @param excluds
     *        the {@link #excluds} to set
     */
    public void setExcluds(Class<Throwable>[] excluds) {
        this.excluds = excluds;
    }

}
