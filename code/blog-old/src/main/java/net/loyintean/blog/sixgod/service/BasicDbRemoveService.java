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
public interface BasicDbRemoveService<I, O> {

    /**
     * 从数据库中删除一条数据，并返回相关的数据O
     * <p>
     * 返回值可以是被删除的数据，也可以是被删除的主键、数据量等。
     *
     * @param param
     * @return
     */
    O remove(I param);
}
