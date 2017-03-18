/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.dao;

import java.util.List;

/**
 * @author winters1224@163.com
 * @param <I>
 *        数据库操作入参数据类型
 * @param <O>
 *        数据库操作出参数据类型
 */
public interface BasicSelectPagedListDao<I, O> {

    /**
     * 生成并执行select语句
     * 除分页相关条件(limit/between等)之外，from .. where 应与
     * {@link #selectTotalCount(Object)}一致
     *
     * @param param
     * @return 返回分页后的list数据
     */
    List<O> selectList(I param);

    /**
     * 生成并执行select count(*)语句
     * <p>
     * 除分页相关条件(limit/between等)之外，from .. where 应与 {@link #selectList(Object)}一致
     *
     * @param param
     * @return
     */
    long selectTotalCount(I param);
}
