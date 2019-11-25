package net.loyintean.blog.task;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务基类
 */
public abstract class BaseTask<T> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private String taskName;
    private long waitTimeForOnePiece;
    private ThreadPoolTaskExecutor executor;

    /**
     * 做默认的初始化
     */
    public BaseTask() {
        taskName = this.getClass().getName();
        waitTimeForOnePiece = 5 * 1000 * 60;

        executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(2 * Runtime.getRuntime().availableProcessors());
        executor.setKeepAliveSeconds(60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setKeepAliveSeconds(60);
        executor.setQueueCapacity(1000);
        executor.setDaemon(true);
        executor.setThreadNamePrefix(taskName);
    }

    /**
     * 执行方法
     */
    public void executeTask() {
        log.info("定时任务-{}:开始", taskName);

        long startAt = System.currentTimeMillis();


        List<T> targetList = queryTarget();

        while (CollectionUtils.isNotEmpty(targetList)) {
            log.info("定时任务-{}：本次处理的数据量有：{}", taskName, targetList.size());


            try {
                CountDownLatch latch = submitTask(targetList);

                boolean isCompleted = latch.await(waitTimeForOnePiece, TimeUnit.MILLISECONDS);

                log.info("定时任务-{}:等待执行结果:{}", taskName, isCompleted ? "处理完成" : "等待超时");

            } catch (InterruptedException e) {
                log.warn("定时任务-{}:等待发生异常。继续执行下一分片数据", taskName, e);
            }

            targetList = queryTarget();
        }


        long endAt = System.currentTimeMillis();

        log.info("定时任务-{}:结束。用时:{} ms", taskName, (endAt - startAt));
    }

    private CountDownLatch submitTask(List<T> targetList) {
        CountDownLatch latch = new CountDownLatch(targetList.size());
        for (Iterator<T> iterator = targetList.iterator(); iterator.hasNext(); ) {
            T target = iterator.next();

            submitTask(target, latch);

            // 一边处理一边清除队列，尽快释放内存
            iterator.remove();
        }
        return latch;
    }

    /**
     * 提交到线程池
     *
     * @param target 待执行的任务数据
     */
    private void submitTask(T target, CountDownLatch latch) {

        if (tryLockTask(target)) {
            executor.execute(new RunnableTask(target, latch));
        } else {
            latch.countDown();
        }
    }

    /**
     * 尝试对目标数据加锁，用于处理集群环境下的分布式任务。
     * <p>
     * 注意自己处理超时。主任务不会解锁。
     *
     * @param target 目标数据
     * @return true:锁定成功；当前服务会处理这条数据；false：锁定失败，当前服务不处理这条数据
     */
    protected boolean tryLockTask(T target) {
        log.debug("尝试对target加锁。默认永远锁定成功。target:{}", target);
        return true;
    }

    /**
     * 实际要处理的操作。
     * <p>
     * 需要自己考虑幂等性。
     *
     * @param target 待处理的数据
     */
    protected abstract void executeTask(T target);

    /**
     * 查出本次定时任务的目标数据。
     *
     * @return 要求：非null；限制大小。
     */
    protected abstract List<T> queryTarget();

    public void setExecutor(ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }

    public void setWaitTimeForOnePiece(long waitTimeForOnePiece) {
        this.waitTimeForOnePiece = waitTimeForOnePiece;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 处理任务的子线程
     */
    private class RunnableTask implements Runnable {
        private T target;
        private CountDownLatch latch;

        private RunnableTask(T target, CountDownLatch latch) {
            this.target = target;
            this.latch = latch;
        }

        @Override
        public void run() {

            log.debug("定时任务-{}:子线程开始。target:{}", taskName, target);

            long startAt = System.currentTimeMillis();
            try {

                executeTask(target);

            } catch (Exception e) {
                log.warn("定时任务-{},子线程发生异常，继续处理下一数据。target:{}", taskName, e);
            } finally {
                latch.countDown();
            }

            long endAt = System.currentTimeMillis();

            log.info("定时任务-{}:子线程结束。target:{}，latch:{}, 用时：{}", taskName, target, latch, (endAt - startAt));

        }
    }
}
