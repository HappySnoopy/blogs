/**
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.web;

import net.loyintean.blog.sixgod.dto.ResultDto4List;
import net.loyintean.blog.sixgod.dto.ResultDto4PagedList;
import net.loyintean.blog.sixgod.dto.ResultDto;

/**
 * 基本数据库操作的web入口
 *
 * @author winters1224@163.com
 * @param <I>
 *        接口入参数据类型
 * @param <O>
 *        接口出参数据类型
 */
public interface BasicDbController<I, O> {
    /**
     * 查询一条数据
     *
     * @param param
     * @return
     */
    ResultDto<O> query(I param);

    /**
     * 查询列表数据（不分页）
     *
     * @param param
     * @return
     */
    ResultDto4List<O> queryList(I param);

    /**
     * 查询分页列表数据
     *
     * @param param
     * @return
     */
    ResultDto4PagedList<O> queryPagedList(I param);

    /**
     * 新增一条数据
     *
     * @param param
     * @return
     */
    ResultDto<O> save(I param);

    /**
     * 更新一条数据
     *
     * @param param
     * @return
     */
    ResultDto<O> edit(I param);

    /**
     * 删除一条数据
     *
     * @param param
     * @return
     */
    ResultDto<O> remove(I param);
}
