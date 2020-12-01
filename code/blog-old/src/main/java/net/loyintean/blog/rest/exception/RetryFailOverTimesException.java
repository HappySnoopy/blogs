/**
 * All Rights Reserved
 */
package net.loyintean.blog.rest.exception;

/**
 * 重试超过次数限制
 * <p>
 * 如果重试超过次数限制，仍然没有成功，则返回此异常
 *
 * @author Snoopy
 */
public class RetryFailOverTimesException extends RuntimeException {

    /**
         *
         */
    private static final long serialVersionUID = -7950958097260616616L;
}