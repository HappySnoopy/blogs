/*
 * 宜人贷
 * All rights reserved.
 */
package net.loyintean.blog.sixgod.dao;

/**
 * @author winters1224@163.com
 * @param <I>
 *            数据库操作入参数据类型
 * @param <O>
 *            数据库操作出参数据类型
 */
public interface BasicDeleteDao<I, O> {

    /**
     * 生成并执行delete语句
     * <p>
     * 不一定要物理删除；支持逻辑删除。
     *
     * @param param
     * @return
     */
    O delete(I param);
}
