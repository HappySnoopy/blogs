/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

/**
 * 基础数据库操作服务接口适配器
 * <p>
 *
 * @author winters1224@163.com
 * @param <I>
 *        接口入参数据类型
 * @param <O>
 *        接口出参数据类型
 */
public class BasicDbServiceAdapter<I, O>
        implements BasicDbEditService<I, O>, BasicDbQueryListService<I, O>,
        BasicDbQueryPagedListService<I, O>, BasicDbQueryService<I, O>,
        BasicDbRemoveService<I, O>, BasicDbSaveService<I, O> {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory
        .getLogger(BasicDbServiceAdapter.class);

    /**
     * {@link #remove(Object)}
     */
    private BasicDeleteDao<I, O> basicDeleteDao = new BasicDaoAdapter<>();
    /**
     * {@link #save(Object)}
     */
    private BasicInsertDao<I, O> basicInsertDao = new BasicDaoAdapter<>();
    /**
     * {@link #query(Object)}
     */
    private BasicSelectDao<I, O> basicSelectDao = new BasicDaoAdapter<>();
    /**
     * {@link #queryList(Object)}
     */
    private BasicSelectListDao<I, O> basicSelectListDao = new BasicDaoAdapter<>();
    /**
     * {@link #queryPagedList(Object)}<br>
     * {@link #queryTotalCount(Object)}
     */
    private BasicSelectPagedListDao<I, O> basicSelectPagedListDao = new BasicDaoAdapter<>();
    /**
     * {@link #edit(Object)}
     */
    private BasicUpdateDao<I, O> basicUpdateDao = new BasicDaoAdapter<>();

    /**
     * @see net.loyintean.blog.sixgod.service.BasicDbSaveService
     *      #save(java.lang.Object)
     */
    @Override
    public O save(I param) {
        BasicDbServiceAdapter.LOGGER.info("save param:{}", param);
        O result = this.basicInsertDao.insert(param);
        BasicDbServiceAdapter.LOGGER.info("save param:{},result:{}", param,
            result);
        return result;
    }

    /**
     * @see net.loyintean.blog.sixgod.service.BasicDbRemoveService
     *      #remove(java.lang.Object)
     */
    @Override
    public O remove(I param) {
        BasicDbServiceAdapter.LOGGER.info("remove param:{}", param);
        O result = this.basicDeleteDao.delete(param);
        BasicDbServiceAdapter.LOGGER.info("remove param:{},result:{}", param,
            result);
        return result;
    }

    /**
     * @see net.loyintean.blog.sixgod.service.BasicDbQueryService
     *      #query(java.lang.Object)
     */
    @Override
    public O query(I param) {
        BasicDbServiceAdapter.LOGGER.info("query param:{}", param);
        O result = this.basicSelectDao.select(param);
        BasicDbServiceAdapter.LOGGER.info("query param:{},result:{}", param,
            result);
        return result;
    }

    /**
     * @see net.loyintean.blog.sixgod.service.BasicDbQueryListService
     *      #queryList(java.lang.Object)
     */
    @Override
    public List<O> queryList(I param) {
        BasicDbServiceAdapter.LOGGER.info("queryList param:{}", param);
        List<O> result = this.basicSelectListDao.selectList(param);
        BasicDbServiceAdapter.LOGGER.info("queryList param:{},result:{}", param,
            result);
        return result;
    }

    /**
     * @see net.loyintean.blog.sixgod.service.BasicDbEditService
     *      #edit(java.lang.Object)
     */
    @Override
    public O edit(I param) {
        BasicDbServiceAdapter.LOGGER.info("edit param:{}", param);
        O result = this.basicUpdateDao.update(param);
        BasicDbServiceAdapter.LOGGER.info("edit param:{},result:{}", param,
            result);
        return result;
    }

    /**
     * @see net.loyintean.blog.sixgod.service.BasicDbQueryPagedListService
     *      #queryTotalCount(java.lang.Object)
     */
    @Override
    public long queryTotalCount(I param) {
        BasicDbServiceAdapter.LOGGER.info("queryTotalCount param:{}", param);
        long result = this.basicSelectPagedListDao.selectTotalCount(param);
        BasicDbServiceAdapter.LOGGER.info("queryTotalCount param:{},result:{}",
            param, result);
        return result;
    }

    /**
     * @see net.loyintean.blog.sixgod.service.BasicDbQueryPagedListService
     *      #queryPagedList(java.lang.Object)
     */
    @Override
    public List<O> queryPagedList(I param) {
        BasicDbServiceAdapter.LOGGER.info("queryPagedList param:{}", param);
        List<O> result = this.basicSelectPagedListDao.selectList(param);
        BasicDbServiceAdapter.LOGGER.info("queryPagedList param:{},result:{}",
            param, result);
        return result;
    }

    /**
     * @param basicDeleteDao
     *        the {@link #basicDeleteDao} to set
     */
    public void setBasicDeleteDao(BasicDeleteDao<I, O> basicDeleteDao) {
        this.basicDeleteDao = basicDeleteDao;
    }

    /**
     * @param basicInsertDao
     *        the {@link #basicInsertDao} to set
     */
    public void setBasicInsertDao(BasicInsertDao<I, O> basicInsertDao) {
        this.basicInsertDao = basicInsertDao;
    }

    /**
     * @param basicSelectDao
     *        the {@link #basicSelectDao} to set
     */
    public void setBasicSelectDao(BasicSelectDao<I, O> basicSelectDao) {
        this.basicSelectDao = basicSelectDao;
    }

    /**
     * @param basicSelectListDao
     *        the {@link #basicSelectListDao} to set
     */
    public void setBasicSelectListDao(
            BasicSelectListDao<I, O> basicSelectListDao) {
        this.basicSelectListDao = basicSelectListDao;
    }

    /**
     * @param basicSelectPagedListDao
     *        the {@link #basicSelectPagedListDao} to set
     */
    public void setBasicSelectPagedListDao(
            BasicSelectPagedListDao<I, O> basicSelectPagedListDao) {
        this.basicSelectPagedListDao = basicSelectPagedListDao;
    }

    /**
     * @param basicUpdateDao
     *        the {@link #basicUpdateDao} to set
     */
    public void setBasicUpdateDao(BasicUpdateDao<I, O> basicUpdateDao) {
        this.basicUpdateDao = basicUpdateDao;
    }

}
