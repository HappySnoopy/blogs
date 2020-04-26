/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.web;

import net.loyintean.blog.sixgod.dto.ResultDto;
import net.loyintean.blog.sixgod.dto.ResultDto4List;
import net.loyintean.blog.sixgod.dto.ResultDto4PagedList;
import net.loyintean.blog.sixgod.service.*;
import net.loyintean.blog.sixgod.service.impl.BasicDbServiceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;

/**
 * @author winters1224@163.com
 */
public abstract class BasicDbControllerAdapter<T>
        implements BasicDbController<T,T> {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory
        .getLogger(BasicDbControllerAdapter.class);
    /**
     * 查询服务
     */
    private BasicDbQueryService<T,T> basicDbQueryService = new
            BasicDbServiceAdapter<>();
    /**
     * 查询不分页列表服务
     */
    private BasicDbQueryListService<T,T> basicDbQueryListService = new BasicDbServiceAdapter<>();
    /**
     * 查询分页列表服务
     */
    private BasicDbQueryPagedListService<T,T> basicDbQueryPagedListService = new BasicDbServiceAdapter<>();
    /**
     * 新增服务
     */
    private BasicDbSaveService<T,T> basicDbSaveService = new BasicDbServiceAdapter<>();
    /**
     * 更新服务
     */
    private BasicDbEditService<T,T> basicDbEditService = new BasicDbServiceAdapter<>();
    /**
     * 删除服务
     */
    private BasicDbRemoveService<T,T> basicDbRemoveService = new BasicDbServiceAdapter<>();

    /**
     * 委托给 {@link #operate(Object, Function)}
     *
     * @see net.loyintean.blog.sixgod.web.BasicDbController#query(java.lang.Object)
     */
    @Override
    public ResultDto<T> query(T param) {
        return this.operate(param,
            queryParam -> this.basicDbQueryService.query(queryParam));
    }

    /**
     * 委托给 {@link #operate(Object, Function)}
     *
     * @see net.loyintean.blog.sixgod.web.BasicDbController#save(java.lang.Object)
     */
    @Override
    public ResultDto<T> save(T param) {
        return this.operate(param,
            queryParam -> this.basicDbSaveService.save(queryParam));
    }

    /**
     * 委托给 {@link #operate(Object, Function)}
     *
     * @see net.loyintean.blog.sixgod.web.BasicDbController#edit(java.lang.Object)
     */
    @Override
    public ResultDto<T> edit(T param) {
        return this.operate(param,
            queryParam -> this.basicDbEditService.edit(queryParam));
    }

    /**
     * 委托给 {@link #operate(Object, Function)}
     *
     * @see net.loyintean.blog.sixgod.web.BasicDbController#remove(java.lang.Object)
     */
    @Override
    public ResultDto<T> remove(T param) {
        return this.operate(param,
            queryParam -> this.basicDbRemoveService.remove(queryParam));
    }

    /**
     * 执行一个操作，返回单独的结果
     *
     * @param param
     * @param function
     * @return
     */
    protected ResultDto<T> operate(T param, Function<T,T> function) {
        BasicDbControllerAdapter.LOGGER.info("param:{}", param);

        ResultDto<T> result = new ResultDto<>();
        try {
            T queryResult = function.apply(param);
            BasicDbControllerAdapter.LOGGER.debug("param:{}, inner result:{}",
                param, queryResult);
            result.setSuccess(true);
            result.setData(queryResult);
            result.setMessage(null);
        } catch (Exception e) {
            BasicDbControllerAdapter.LOGGER.info("error! param:{}", param, e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        BasicDbControllerAdapter.LOGGER.info("param:{},finally result:{}",
            param, result);
        return result;
    }

    /**
     * 直接查询列表
     *
     * @see net.loyintean.blog.sixgod.web.BasicDbController#queryList(java.lang.Object)
     */
    @Override
    public ResultDto4List<T> queryList(T param) {
        BasicDbControllerAdapter.LOGGER.info("param:{}", param);

        ResultDto4List<T> result = new ResultDto4List<>();
        try {
            List<T> queryResult = this.basicDbQueryListService.queryList(param);
            BasicDbControllerAdapter.LOGGER.debug("param:{}, inner result:{}",
                param, queryResult);
            result.setSuccess(true);
            result.setList(queryResult);
            result.setMessage(null);
        } catch (Exception e) {
            BasicDbControllerAdapter.LOGGER.info("error! param:{}", param, e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        BasicDbControllerAdapter.LOGGER.info("param:{},finally result:{}",
            param, result);
        return result;
    }

    /**
     * 先查询总数；再根据总数查询列表
     *
     * @see net.loyintean.blog.sixgod.web.BasicDbController#queryPagedList(java.lang.Object)
     */
    @Override
    public ResultDto4PagedList<T> queryPagedList(T param) {
        BasicDbControllerAdapter.LOGGER.info("param:{}", param);

        ResultDto4PagedList<T> result = new ResultDto4PagedList<>();
        try {
            long totalCount = this.basicDbQueryPagedListService
                .queryTotalCount(param);

            if (totalCount > 0L) {
                List<T> queryResult = this.basicDbQueryListService
                    .queryList(param);
                BasicDbControllerAdapter.LOGGER
                    .debug("param:{}, inner result:{}", param, queryResult);
                result.setList(queryResult);
            }
            result.setTotalCount(totalCount);
            result.setMessage(null);
            result.setSuccess(true);
        } catch (Exception e) {
            BasicDbControllerAdapter.LOGGER.info("error! param:{}", param, e);
            result.setTotalCount(0L);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        BasicDbControllerAdapter.LOGGER.info("param:{},finally result:{}",
            param, result);
        return result;
    }

    /**
     * @param basicDbQueryService
     *        the {@link #basicDbQueryService} to set
     */
    public void setBasicDbQueryService(
            BasicDbQueryService<T,T> basicDbQueryService) {
        this.basicDbQueryService = basicDbQueryService;
    }

    /**
     * @param basicDbQueryListService
     *        the {@link #basicDbQueryListService} to set
     */
    public void setBasicDbQueryListService(
            BasicDbQueryListService<T,T> basicDbQueryListService) {
        this.basicDbQueryListService = basicDbQueryListService;
    }

    /**
     * @param basicDbQueryPagedListService
     *        the {@link #basicDbQueryPagedListService} to set
     */
    public void setBasicDbQueryPagedListService(
            BasicDbQueryPagedListService<T,T> basicDbQueryPagedListService) {
        this.basicDbQueryPagedListService = basicDbQueryPagedListService;
    }

    /**
     * @param basicDbSaveService
     *        the {@link #basicDbSaveService} to set
     */
    public void setBasicDbSaveService(
            BasicDbSaveService<T,T> basicDbSaveService) {
        this.basicDbSaveService = basicDbSaveService;
    }

    /**
     * @param basicDbEditService
     *        the {@link #basicDbEditService} to set
     */
    public void setBasicDbEditService(
            BasicDbEditService<T,T> basicDbEditService) {
        this.basicDbEditService = basicDbEditService;
    }

    /**
     * @param basicDbRemoveService
     *        the {@link #basicDbRemoveService} to set
     */
    public void setBasicDbRemoveService(
            BasicDbRemoveService<T,T> basicDbRemoveService) {
        this.basicDbRemoveService = basicDbRemoveService;
    }
}