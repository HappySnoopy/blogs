/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.dao;

/**
 * @param <I> 数据库操作入参数据类型
 * @param <O> 数据库操作出参数据类型
 * @author Snoopy
 */
public interface BasicInsertDao<I, O> {

    /**
     * 生成并执行insert语句
     *
     * @param param
     * @return
     */
    O insert(I param);
}
