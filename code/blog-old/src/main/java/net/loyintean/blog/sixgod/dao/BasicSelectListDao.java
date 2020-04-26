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
public interface BasicSelectListDao<I, O> {

    /**
     * 生成并执行select语句
     *
     * @param param
     * @return 返回list数据
     */
    List<O> selectList(I param);
}
