/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.service;

/**
 * @param <I> 接口入参数据类型
 * @param <O> 接口出参数据类型
 * @author Snoopy
 */
public interface BasicDbEditService<I, O> {

    /**
     * 向数据库中更新一条数据，并返回相关的数据O
     * <p>
     * 返回值可以是被更新的实体，也可以只是主键、数据量等。
     *
     * @param param
     * @return
     */
    O edit(I param);
}
