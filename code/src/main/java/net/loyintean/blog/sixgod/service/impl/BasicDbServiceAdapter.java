/**
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.service.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import net.loyintean.blog.sixgod.dao.BasicDeleteDao;
import net.loyintean.blog.sixgod.dao.BasicInsertDao;
import net.loyintean.blog.sixgod.dao.BasicSelectDao;
import net.loyintean.blog.sixgod.dao.BasicSelectListDao;
import net.loyintean.blog.sixgod.dao.BasicSelectPagedListDao;
import net.loyintean.blog.sixgod.dao.BasicUpdateDao;
import net.loyintean.blog.sixgod.dao.impl.BasicDaoAdapter;
import net.loyintean.blog.sixgod.service.BasicDbEditService;
import net.loyintean.blog.sixgod.service.BasicDbQueryListService;
import net.loyintean.blog.sixgod.service.BasicDbQueryPagedListService;
import net.loyintean.blog.sixgod.service.BasicDbQueryService;
import net.loyintean.blog.sixgod.service.BasicDbRemoveService;
import net.loyintean.blog.sixgod.service.BasicDbSaveService;

import javax.annotation.Resource;

/**
 * 基础数据库操作服务接口适配器
 * <p>
 * 原则上，只有数据库写操作开启了事务。
 *
 * @author winters1224@163.com
 * @param <T> 同时用做入参和出参
 */
public class BasicDbServiceAdapter<T>
        implements BasicDbEditService<T, T>, BasicDbQueryListService<T, T>,
        BasicDbQueryPagedListService<T, T>, BasicDbQueryService<T, T>,
        BasicDbRemoveService<T, T>, BasicDbSaveService<T, T> {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(BasicDbServiceAdapter.class);

    /**
     * {@link #remove(Object)}
     */
    private BasicDeleteDao<T, Integer> basicDeleteDao;
    /**
     * {@link #save(Object)}
     */
    private BasicInsertDao<T, Integer> basicInsertDao;
    /**
     * {@link #query(Object)}
     */
    private BasicSelectDao<T, T> basicSelectDao;
    /**
     * {@link #queryList(Object)}
     */
    private BasicSelectListDao<T, T> basicSelectListDao;
    /**
     * {@link #queryPagedList(Object)}<br>
     * {@link #queryTotalCount(Object)}
     */
    private BasicSelectPagedListDao<T, T> basicSelectPagedListDao;
    /**
     * {@link #edit(Object)}
     */
    private BasicUpdateDao<T, Integer> basicUpdateDao;

    /**
     * @param param
     * @return
     */
    @Transactional
    @Override
    public T save(T param) {
        BasicDbServiceAdapter.LOGGER.info("save param:{}", param);
        Integer result = this.basicInsertDao.insert(param);
        BasicDbServiceAdapter.LOGGER.info("save param:{},result:{}", param,
                result);
        return param;
    }

    /**
     * @param param
     * @return
     */
    @Transactional
    @Override
    public T remove(T param) {
        BasicDbServiceAdapter.LOGGER.info("remove param:{}", param);
        Integer result = this.basicDeleteDao.delete(param);
        BasicDbServiceAdapter.LOGGER.info("remove param:{},result:{}", param,
                result);
        return param;
    }

    /**
     * @param param
     * @return
     */
    @Override
    public T query(T param) {
        BasicDbServiceAdapter.LOGGER.info("query param:{}", param);
        T result = this.basicSelectDao.select(param);
        BasicDbServiceAdapter.LOGGER.info("query param:{},result:{}", param,
                result);
        return result;
    }

    /**
     * 保证返回列表不为null
     *
     * @param param
     * @return
     */
    @Override
    public List<T> queryList(T param) {
        BasicDbServiceAdapter.LOGGER.info("queryList param:{}", param);
        List<T> result = this.basicSelectListDao.selectList(param);
        BasicDbServiceAdapter.LOGGER.info("queryList param:{},result:{}", param,
                result);
        return result == null ? Collections.emptyList() : result;
    }

    /**
     * @param param
     * @return
     */
    @Transactional
    @Override
    public T edit(T param) {
        BasicDbServiceAdapter.LOGGER.info("edit param:{}", param);
        Integer result = this.basicUpdateDao.update(param);
        BasicDbServiceAdapter.LOGGER.info("edit param:{},result:{}", param,
                result);
        return param;
    }

    /**
     * @param param
     * @return
     */
    @Override
    public long queryTotalCount(T param) {
        BasicDbServiceAdapter.LOGGER.info("queryTotalCount param:{}", param);
        long result = this.basicSelectPagedListDao.selectTotalCount(param);
        BasicDbServiceAdapter.LOGGER
                .info("queryTotalCount param:{}," + "result:{}", param, result);
        return result;
    }

    /**
     * @param param
     * @return
     */
    @Override
    public List<T> queryPagedList(T param) {
        BasicDbServiceAdapter.LOGGER.info("queryPagedList param:{}", param);
        List<T> result = this.basicSelectPagedListDao.selectList(param);
        BasicDbServiceAdapter.LOGGER
                .info("queryPagedList param:{}," + "result:{}", param, result);
        return result;
    }

    /**
     * @param basicDeleteDao
     *        the {@link #basicDeleteDao} to set
     */
    public void setBasicDeleteDao(BasicDeleteDao<T, Integer> basicDeleteDao) {
        this.basicDeleteDao = basicDeleteDao;
    }

    /**
     * @param basicInsertDao
     *        the {@link #basicInsertDao} to set
     */
    public void setBasicInsertDao(BasicInsertDao<T, Integer> basicInsertDao) {
        this.basicInsertDao = basicInsertDao;
    }

    /**
     * @param basicSelectDao
     *        the {@link #basicSelectDao} to set
     */
    public void setBasicSelectDao(BasicSelectDao<T, T> basicSelectDao) {
        this.basicSelectDao = basicSelectDao;
    }

    /**
     * @param basicSelectListDao
     *        the {@link #basicSelectListDao} to set
     */
    public void setBasicSelectListDao(
            BasicSelectListDao<T, T> basicSelectListDao) {
        this.basicSelectListDao = basicSelectListDao;
    }

    /**
     * @param basicSelectPagedListDao
     *        the {@link #basicSelectPagedListDao} to set
     */
    public void setBasicSelectPagedListDao(
            BasicSelectPagedListDao<T, T> basicSelectPagedListDao) {
        this.basicSelectPagedListDao = basicSelectPagedListDao;
    }

    /**
     * @param basicUpdateDao
     *        the {@link #basicUpdateDao} to set
     */
    public void setBasicUpdateDao(BasicUpdateDao<T, Integer> basicUpdateDao) {
        this.basicUpdateDao = basicUpdateDao;
    }

}
