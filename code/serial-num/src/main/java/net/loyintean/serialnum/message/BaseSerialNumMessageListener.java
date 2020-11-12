package net.loyintean.serialnum.message;

import net.loyintean.serialnum.utils.SerialNumUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * 通用的消息拦截器
 *
 * @author Snoopy
 * @date 2020年11月12日
 */
public abstract class BaseSerialNumMessageListener {

    /**
     * 拦截切面，在处理消息
     *
     * @param joinPoint 切面
     * @throws Throwable 这是joinPoint抛出的异常。
     */
    @Around("pointcut()")
    public void onMessage(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 先尝试从message中拿到对方传过来的值
            SerialNumUtils.setSerialNum(getSerialNum(joinPoint));
        } catch (Exception e) {
            // 如果发生异常，那么直接初始化成一个随机值
            SerialNumUtils.initSerialNum();
        }

        try {
            joinPoint.proceed();
        } finally {
            SerialNumUtils.clearSerialNum();
        }
    }

    /**
     * 定义切点
     */
    public abstract void pointcut();

    /**
     * 获取消息中的流水号
     *
     * @param joinPoint 切点
     * @return 消息中的流水号。允许为null
     * @throws Exception 获取过程中允许发生异常
     */
    public abstract Object getSerialNum(ProceedingJoinPoint joinPoint) throws Exception;
}
