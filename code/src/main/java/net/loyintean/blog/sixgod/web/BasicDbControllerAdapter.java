/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.web;

import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.loyintean.blog.sixgod.dto.ResultDto;
import net.loyintean.blog.sixgod.dto.ResultDto4List;
import net.loyintean.blog.sixgod.dto.ResultDto4PagedList;
import net.loyintean.blog.sixgod.service.BasicDbEditService;
import net.loyintean.blog.sixgod.service.BasicDbQueryListService;
import net.loyintean.blog.sixgod.service.BasicDbQueryPagedListService;
import net.loyintean.blog.sixgod.service.BasicDbQueryService;
import net.loyintean.blog.sixgod.service.BasicDbRemoveService;
import net.loyintean.blog.sixgod.service.BasicDbSaveService;
import net.loyintean.blog.sixgod.service.impl.BasicDbServiceAdapter;

/**
 * @author winters1224@163.com
 * @param <I>
 * @param <O>
 */
public abstract class BasicDbControllerAdapter<I, O>
        implements BasicDbController<I, O> {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory
        .getLogger(BasicDbControllerAdapter.class);
    /**
     * 查询服务
     */
    private BasicDbQueryService<I, O> basicDbQueryService = new BasicDbServiceAdapter<>();
    /**
     * 查询不分页列表服务
     */
    private BasicDbQueryListService<I, O> basicDbQueryListService = new BasicDbServiceAdapter<>();
    /**
     * 查询分页列表服务
     */
    private BasicDbQueryPagedListService<I, O> basicDbQueryPagedListService = new BasicDbServiceAdapter<>();
    /**
     * 新增服务
     */
    private BasicDbSaveService<I, O> basicDbSaveService = new BasicDbServiceAdapter<>();
    /**
     * 更新服务
     */
    private BasicDbEditService<I, O> basicDbEditService = new BasicDbServiceAdapter<>();
    /**
     * 删除服务
     */
    private BasicDbRemoveService<I, O> basicDbRemoveService = new BasicDbServiceAdapter<>();

    /**
     * 委托给 {@link #operate(Object, Function)}
     *
     * @see net.loyintean.blog.sixgod.web.BasicDbController#query(java.lang.Object)
     */
    @Override
    public ResultDto<O> query(I param) {
        return this.operate(param,
            queryParam -> this.basicDbQueryService.query(queryParam));
    }

    /**
     * 委托给 {@link #operate(Object, Function)}
     *
     * @see net.loyintean.blog.sixgod.web.BasicDbController#save(java.lang.Object)
     */
    @Override
    public ResultDto<O> save(I param) {
        return this.operate(param,
            queryParam -> this.basicDbSaveService.save(queryParam));
    }

    /**
     * 委托给 {@link #operate(Object, Function)}
     *
     * @see net.loyintean.blog.sixgod.web.BasicDbController#edit(java.lang.Object)
     */
    @Override
    public ResultDto<O> edit(I param) {
        return this.operate(param,
            queryParam -> this.basicDbEditService.edit(queryParam));
    }

    /**
     * 委托给 {@link #operate(Object, Function)}
     *
     * @see net.loyintean.blog.sixgod.web.BasicDbController#remove(java.lang.Object)
     */
    @Override
    public ResultDto<O> remove(I param) {
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
    protected ResultDto<O> operate(I param, Function<I, O> function) {
        BasicDbControllerAdapter.LOGGER.info("param:{}", param);

        ResultDto<O> result = new ResultDto<>();
        try {
            O queryResult = function.apply(param);
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
    public ResultDto4List<O> queryList(I param) {
        BasicDbControllerAdapter.LOGGER.info("param:{}", param);

        ResultDto4List<O> result = new ResultDto4List<>();
        try {
            List<O> queryResult = this.basicDbQueryListService.queryList(param);
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
    public ResultDto4PagedList<O> queryPagedList(I param) {
        BasicDbControllerAdapter.LOGGER.info("param:{}", param);

        ResultDto4PagedList<O> result = new ResultDto4PagedList<>();
        try {
            long totalCount = this.basicDbQueryPagedListService
                .queryTotalCount(param);

            if (totalCount > 0L) {
                List<O> queryResult = this.basicDbQueryListService
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
            BasicDbQueryService<I, O> basicDbQueryService) {
        this.basicDbQueryService = basicDbQueryService;
    }

    /**
     * @param basicDbQueryListService
     *        the {@link #basicDbQueryListService} to set
     */
    public void setBasicDbQueryListService(
            BasicDbQueryListService<I, O> basicDbQueryListService) {
        this.basicDbQueryListService = basicDbQueryListService;
    }

    /**
     * @param basicDbQueryPagedListService
     *        the {@link #basicDbQueryPagedListService} to set
     */
    public void setBasicDbQueryPagedListService(
            BasicDbQueryPagedListService<I, O> basicDbQueryPagedListService) {
        this.basicDbQueryPagedListService = basicDbQueryPagedListService;
    }

    /**
     * @param basicDbSaveService
     *        the {@link #basicDbSaveService} to set
     */
    public void setBasicDbSaveService(
            BasicDbSaveService<I, O> basicDbSaveService) {
        this.basicDbSaveService = basicDbSaveService;
    }

    /**
     * @param basicDbEditService
     *        the {@link #basicDbEditService} to set
     */
    public void setBasicDbEditService(
            BasicDbEditService<I, O> basicDbEditService) {
        this.basicDbEditService = basicDbEditService;
    }

    /**
     * @param basicDbRemoveService
     *        the {@link #basicDbRemoveService} to set
     */
    public void setBasicDbRemoveService(
            BasicDbRemoveService<I, O> basicDbRemoveService) {
        this.basicDbRemoveService = basicDbRemoveService;
    }

}
