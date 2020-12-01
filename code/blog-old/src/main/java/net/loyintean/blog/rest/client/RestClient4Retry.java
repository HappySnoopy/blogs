/**
 * All Rights Reserved
 */
package net.loyintean.blog.rest.client;

import net.loyintean.blog.rest.RetryConfiguration;
import net.loyintean.blog.rest.exception.RetryFailOverTimesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 带重试操作的rest客户端
 *
 * @author Snoopy
 */
class RestClient4Retry extends RestClient4Wrapper {
    /**
     * 日志记录器
     */
    private static final Logger LOGGER = LoggerFactory
        .getLogger(RestClient4Retry.class);

    /**
     * 重试配置项
     */
    private RetryConfiguration retryConfiguration;

    /**
     * 指定需要重试的服务
     *
     * @param delegator
     */
    RestClient4Retry(RestClient delegator, RetryConfiguration retryConfiguration) {
        this.delegator = delegator;
        this.retryConfiguration = retryConfiguration;
    }

    /**
     * 有可能返回null，有可能抛出异常
     *
     * @return
     * @throws RetryFailOverTimesException
     *         如果超过重试次数，仍然未能成功，则抛出此异常
     */
    @Override
    public <T> T get() {
        T result = this.callRetry(() -> this.delegator.get());
        return result;
    }

    /**
     * 有可能返回null，有可能抛出异常
     *
     * @return
     * @throws RetryFailOverTimesException
     *         如果超过重试次数，仍然未能成功，则抛出此异常
     */
    @Override
    public <T> T post() {
        return this.callRetry(() -> this.delegator.post());
    }

    private <T> T callRetry(RetryCallback<T> callback) {
        T result = null;
        // 默认为不成功
        boolean isSuccess = false;
        int retryTimes = this.retryConfiguration.retryTimes();
        // 不成功的情况下，遍历区间为：[0,retryTimes]
        for (int i = 0; !isSuccess && i <= retryTimes; i++) {
            try {
                result = callback.doRetry();
                isSuccess = this.retryConfiguration.isSuccess(result);
            } catch (Exception e) {
                isSuccess = this.retryConfiguration.isSuccess(e);
            }
            RestClient4Retry.LOGGER.debug(
                "currentTime:{},retryTimes:{},isSuccess:{},result:{}", i,
                retryTimes, isSuccess, result);
        }
        // 如果超过了重试次数，而仍然未成功，将抛出异常
        if (!isSuccess) {
            throw new RetryFailOverTimesException();
        }

        return result;
    }

    /**
     * 重试回调接口
     * <p>
     * 内部接口，用来定义重试的实际操作
     *
     * @author Snoopy
     */
    @FunctionalInterface
    private interface RetryCallback<T> {
        /**
         * 重试的实际操作
         *
         * @return
         */
        T doRetry();
    }

}
