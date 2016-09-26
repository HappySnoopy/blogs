/**
 * All Rights Reserved
 */
package net.loyintean.blog.rest;

/**
 * 重试相关配置
 *
 * @author winters1224@163.com
 */
public class RetryConfiguration {

    /**
     * 重试次数
     * <p>
     * 指第一次尝试失败后的重试<br>
     *
     * @return
     */
    public int retryTimes() {
        return 3;
    }

    /**
     * 如果抛出异常，判断是否需要重试
     * <p>
     * 如果不重试，会导致返回结果为null
     *
     * @param throwable
     * @return
     */
    public boolean isSuccess(Throwable throwable) {
        return throwable == null;
    }

    /**
     * 如果得到了返回结果，判断是否需要重试
     *
     * @param result
     * @return
     */
    public boolean isSuccess(Object result) {
        return result != null;
    }
}
