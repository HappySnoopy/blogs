/**
 * Copyright(c) 2011-2017 by  Inc.
 * All Rights Reserved
 */
package net.loyintean.blog.redislock.redisson;

/**
 * 得到锁之后的回调接口
 *
 * @param <T>
 * @author Snoopy
 * @since 2017年6月16日
 */
public interface AquiredLockWorker<T> {
    T invokeAfterLockAquire() throws Exception;

}
