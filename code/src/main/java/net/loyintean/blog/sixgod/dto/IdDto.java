/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.dto;

import java.io.Serializable;

/**
 * 带id的dto基础接口定义
 *
 * @author winters1224@163.com
 * @param <T>
 */
public interface IdDto<T extends Serializable> {

    /**
     * 获取id
     *
     * @return
     */
    T getId();

    /**
     * 设置id
     *
     * @param dtoId
     */
    void setId(T dtoId);
}
