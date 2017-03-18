/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.dao.impl;

import java.util.List;

import net.loyintean.blog.sixgod.dao.BasicDeleteDao;
import net.loyintean.blog.sixgod.dao.BasicInsertDao;
import net.loyintean.blog.sixgod.dao.BasicSelectDao;
import net.loyintean.blog.sixgod.dao.BasicSelectListDao;
import net.loyintean.blog.sixgod.dao.BasicSelectPagedListDao;
import net.loyintean.blog.sixgod.dao.BasicUpdateDao;

/**
 * 此适配器不实现任何方法，全部抛出 {@linkplain UnsupportedOperationException}
 *
 * @author winters1224@163.com
 * @param <I>
 * @param <O>
 */
public class BasicDaoAdapter<I, O> implements BasicDeleteDao<I, O>,
        BasicInsertDao<I, O>, BasicSelectDao<I, O>, BasicSelectListDao<I, O>,
        BasicSelectPagedListDao<I, O>, BasicUpdateDao<I, O> {

    /**
     * 异常信息
     */
    private static final String EXCEPTION_MESSAGE = "默认实现类，不支持此方法。";

    /**
     * @see net.loyintean.blog.sixgod.dao.BasicUpdateDao#update(java.lang.Object)
     */
    @Override
    public O update(I param) {
        throw new UnsupportedOperationException(
            BasicDaoAdapter.EXCEPTION_MESSAGE);

    }

    /**
     * @see net.loyintean.blog.sixgod.dao.BasicSelectPagedListDao
     *      #selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(I param) {
        throw new UnsupportedOperationException(
            BasicDaoAdapter.EXCEPTION_MESSAGE);
    }

    /**
     * @see net.loyintean.blog.sixgod.dao.BasicSelectListDao
     *      #selectList(java.lang.Object)
     */
    @Override
    public List<O> selectList(I param) {
        throw new UnsupportedOperationException(
            BasicDaoAdapter.EXCEPTION_MESSAGE);

    }

    /**
     * @see net.loyintean.blog.sixgod.dao.BasicSelectDao
     *      #select(java.lang.Object)
     */
    @Override
    public O select(I param) {
        throw new UnsupportedOperationException(
            BasicDaoAdapter.EXCEPTION_MESSAGE);

    }

    /**
     * @see net.loyintean.blog.sixgod.dao.BasicInsertDao
     *      #insert(java.lang.Object)
     */
    @Override
    public O insert(I param) {
        throw new UnsupportedOperationException(
            BasicDaoAdapter.EXCEPTION_MESSAGE);

    }

    /**
     * @see net.loyintean.blog.sixgod.dao.BasicDeleteDao
     *      #delete(java.lang.Object)
     */
    @Override
    public O delete(I param) {
        throw new UnsupportedOperationException(
            BasicDaoAdapter.EXCEPTION_MESSAGE);

    }

}
