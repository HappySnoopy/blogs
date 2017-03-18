/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.service;

/**
 * @author winters1224@163.com
 * @param <I> 接口入参数据类型
 * @param <O> 接口出参数据类型
 */
public interface BasicDbSaveService<I, O> {

    /**
     * 向数据库中保存一条数据，并返回相关的数据O
     * <p>
     * 返回值可以是被保存的实体，也可以只是主键。
     *
     * @param param
     * @return
     */
    O save(I param);
}
