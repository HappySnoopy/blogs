/*
 * 宜人贷
 * All rights reserved.
 */
package net.loyintean.blog.sixgod.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 基于MyBatis实现的数据库操作方法。
 * <p>
 * MyBatis的Mapper-id：入参类全名+"."+执行的方法名<br> 如：test.order.bean.Order.select
 *
 * @author Snoopy
 * @since 2018年5月25日
 */
@Repository
public class BasicDaoByMyBatis<T> extends BasicDaoAdapter<T> {

    private static final Logger log = LoggerFactory.getLogger
            (BasicDaoByMyBatis.class);

    /**
     * 执行MyBatis操作
     */
    @Resource
    private SqlSession template;

    /**
     */
    @Override
    public Integer update(T param) {
        log.debug("param:{}", param);
        int updatedRows = this.template.update(param.getClass().getName() + "" +
                ".update", param);
        log.debug("updatedRows:{}", updatedRows);
        return updatedRows;
    }

    /**
     */
    @Override
    public long selectTotalCount(T param) {
        log.debug("param:{}", param);
        long totalCount = this.template.selectOne(param.getClass().getName()
                + ".selectTotalCount", param);
        log.debug("totalCount:{}", totalCount);
        return totalCount;
    }

    /**
     */
    @Override
    public List<T> selectList(T param) {
        log.debug("param:{}", param);
        List<T> list = this.template.selectList(param.getClass().getName() +
                ".selectList", param);
        // 没有数据时，不会返回null，而是返回emptyList
        log.debug("list:{}", list.size());
        return list;
    }

    /**
     */
    @Override
    public T select(T param) {
        log.debug("param:{}", param);
        T result = this.template.selectOne(param.getClass().getName() + "" +
                ".select", param);
        log.debug("result:{}", result);
        return result;
    }

    /**
     */
    @Override
    public Integer insert(T param) {
        log.debug("param:{}", param);
        int insertedRows = this.template.insert(param.getClass().getName() +
                ".insert", param);
        log.debug("insertedRows:{}", insertedRows);
        return insertedRows;
    }

    /**
     */
    @Override
    public Integer delete(T param) {
        log.debug("param:{}", param);
        int deletedRows = this.template.delete(param.getClass().getName() + "" +
                ".delete", param);
        log.debug("deletedRows:{}", deletedRows);
        return deletedRows;
    }
}
