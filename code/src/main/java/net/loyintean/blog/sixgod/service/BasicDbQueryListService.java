/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.service;

import java.util.List;

/**
 * @author winters1224@163.com
 * @param <I> 接口入参数据类型
 * @param <O> 接口出参数据类型
 */
public interface BasicDbQueryListService<I, O> {
    /**
     * 查询列表数据
     * <p>
     * 不做分页处理
     *
     * @param param
     * @return
     */
    List<O> queryList(I param);
}
