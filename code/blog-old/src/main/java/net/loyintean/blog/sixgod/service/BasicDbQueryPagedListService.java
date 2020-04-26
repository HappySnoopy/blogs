/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.service;

import java.util.List;

/**
 * @author winters1224@163.com
 * @param <I>
 *        接口入参数据类型
 * @param <O>
 *        接口出参数据类型
 */
public interface BasicDbQueryPagedListService<I, O> {

    /**
     * 查询分页列表数据
     * <p>
     *
     * @param param
     * @return
     */
    List<O> queryPagedList(I param);

    /**
     * 查询总数据量
     *
     * @param param
     * @return
     */
    long queryTotalCount(I param);

}
