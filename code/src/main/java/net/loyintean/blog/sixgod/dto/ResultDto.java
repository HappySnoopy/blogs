/**
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.dto;

import java.io.Serializable;

/**
 * 操作结果
 *
 * @author winters1224@163.com
 * @param <T>
 *        操作返回数据的类型
 */
public class ResultDto<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5982772372992557732L;

    /**
     * 成功/失败标志位。
     * <p>
     * true为成功，false为失败。<br>
     * 默认为成功
     */
    private boolean success = true;

    /**
     * 操作结果消息
     * <p>
     * 默认为null。<br>
     * 当操作失败时，应返回失败原因。<br>
     */
    private String message;

    /**
     * 查询到的数据
     * <p>
     * 如果没有查到数据，应返回null
     */
    private T data;

    /**
     * @return the {@link #success}
     */
    public boolean isSuccess() {
        return this.success;
    }

    /**
     * @return the {@link #message}
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @param success
     *        the {@link #success} to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @param message
     *        the {@link #message} to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the {@link #data}
     */
    public T getData() {
        return this.data;
    }

    /**
     * @param data
     *        the {@link #data} to set
     */
    public void setData(T data) {
        this.data = data;
    }

}
