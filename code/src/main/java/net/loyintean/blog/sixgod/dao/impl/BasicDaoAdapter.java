/*
 * 宜人贷
 * All rights reserved.
 */
package net.loyintean.blog.sixgod.dao.impl;


import net.loyintean.blog.sixgod.dao.*;

import java.util.List;

/**
 * 此适配器不实现任何方法，全部抛出 {@linkplain UnsupportedOperationException}
 *
 * @param <T> 数据库交互类；同时封装有查询条件和查询结果
 * @author winters1224@163.com
 */
public class BasicDaoAdapter<T> implements BasicDeleteDao<T, Integer>,
        BasicInsertDao<T, Integer>, BasicSelectDao<T, T>,
        BasicSelectListDao<T, T>, BasicSelectPagedListDao<T, T>,
        BasicUpdateDao<T, Integer> {

    /**
     * 异常信息
     */
    private static final String EXCEPTION_MESSAGE = "默认实现类，不支持此方法。";

    /**
     */
    @Override
    public Integer update(T param) {
        throw new UnsupportedOperationException(BasicDaoAdapter
                .EXCEPTION_MESSAGE);

    }

    /**
     */
    @Override
    public long selectTotalCount(T param) {
        throw new UnsupportedOperationException(BasicDaoAdapter
                .EXCEPTION_MESSAGE);
    }

    /**
     */
    @Override
    public List<T> selectList(T param) {
        throw new UnsupportedOperationException(BasicDaoAdapter
                .EXCEPTION_MESSAGE);

    }

    /**
     */
    @Override
    public T select(T param) {
        throw new UnsupportedOperationException(BasicDaoAdapter
                .EXCEPTION_MESSAGE);

    }

    /**
     */
    @Override
    public Integer insert(T param) {
        throw new UnsupportedOperationException(BasicDaoAdapter
                .EXCEPTION_MESSAGE);

    }

    /**
     */
    @Override
    public Integer delete(T param) {
        throw new UnsupportedOperationException(BasicDaoAdapter
                .EXCEPTION_MESSAGE);

    }

}
