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
public interface BasicDbQueryService<I, O> {

    /**
     * 从数据库中查询一条数据
     *
     * @param param
     * @return
     */
    O query(I param);
}
