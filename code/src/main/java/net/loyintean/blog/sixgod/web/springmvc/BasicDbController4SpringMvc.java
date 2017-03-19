/**
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.web.springmvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.loyintean.blog.sixgod.dto.ResultDto;
import net.loyintean.blog.sixgod.dto.ResultDto4List;
import net.loyintean.blog.sixgod.dto.ResultDto4PagedList;
import net.loyintean.blog.sixgod.web.BasicDbControllerAdapter;

/**
 * @author winters1224@163.com
 * @param <I>
 * @param <O>
 */
public class BasicDbController4SpringMvc<I, O>
        extends BasicDbControllerAdapter<I, O> {

    /**
     * @see net.loyintean.blog.sixgod.web.BasicDbControllerAdapter#query(java.lang.Object)
     */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    @ResponseBody
    @Override
    public ResultDto<O> query(@RequestParam(required = false) I param) {
        return super.query(param);
    }

    /**
     * @see net.loyintean.blog.sixgod.web.BasicDbControllerAdapter#save(java.lang.Object)
     */
    @RequestMapping(method = RequestMethod.POST, path = "/")
    @ResponseBody
    @Override
    public ResultDto<O> save(@RequestParam(required = false) I param) {
        return super.save(param);
    }

    /**
     * @see net.loyintean.blog.sixgod.web.BasicDbControllerAdapter#edit(java.lang.Object)
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/")
    @ResponseBody
    @Override
    public ResultDto<O> edit(@RequestParam(required = false) I param) {
        return super.edit(param);
    }

    /**
     * @see net.loyintean.blog.sixgod.web.BasicDbControllerAdapter#remove(java.lang.Object)
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/")
    @ResponseBody
    @Override
    public ResultDto<O> remove(@RequestParam(required = false) I param) {
        return super.remove(param);
    }

    /**
     * @see net.loyintean.blog.sixgod.web.BasicDbControllerAdapter#queryList(java.lang.Object)
     */
    @RequestMapping(method = RequestMethod.GET, path = "/list/")
    @ResponseBody
    @Override
    public ResultDto4List<O> queryList(
            @RequestParam(required = false) I param) {
        return super.queryList(param);
    }

    /**
     * @see net.loyintean.blog.sixgod.web.BasicDbControllerAdapter#queryPagedList(java.lang.Object)
     */
    @RequestMapping(method = RequestMethod.GET, path = "/paged/")
    @ResponseBody
    @Override
    public ResultDto4PagedList<O> queryPagedList(
            @RequestParam(required = false) I param) {
        return super.queryPagedList(param);
    }

}
