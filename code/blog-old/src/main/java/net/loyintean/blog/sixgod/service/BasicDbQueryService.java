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
public interface BasicDbQueryService<I, O> {

    /**
     * 从数据库中查询一条数据
     *
     * @param param
     * @return
     */
    O query(I param);
}
