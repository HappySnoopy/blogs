/**
 * Copyright(c) 2011-2017 by  Inc.
 * All Rights Reserved
 */
package net.loyintean.blog.redislock.redisson;

/**
 * @author linjun
 * @since 2017年6月16日
 */
public interface DistributedLocker {

    /**
     * 获取锁
     *
     * @param resourceName
     *        锁的名称
     * @param worker
     *        获取锁后的处理类
     * @param <T>
     * @return 处理完具体的业务逻辑要返回的数据
     * @throws UnableToAquireLockException
     * @throws Exception
     */
    <T> T lock(String resourceName, AquiredLockWorker<T> worker)
            throws UnableToAquireLockException, Exception;

    /**带超时
     * @author linjun
     * @since 2017年6月16日
     * @param resourceName
     * @param worker
     * @param lockTime
     * @return
     * @throws UnableToAquireLockException
     * @throws Exception
     */
    <T> T lock(String resourceName, AquiredLockWorker<T> worker, int lockTime)
            throws UnableToAquireLockException, Exception;

}
