/**
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.web.springmvc;

import net.loyintean.blog.sixgod.dto.ResultDto;
import net.loyintean.blog.sixgod.dto.ResultDto4List;
import net.loyintean.blog.sixgod.dto.ResultDto4PagedList;
import net.loyintean.blog.sixgod.web.BasicDbControllerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @param <T>
 * @author Snoopy
 */
public class BasicDbController4SpringMvc<T>
        extends BasicDbControllerAdapter<T> {

    /**
     * @see net.loyintean.blog.sixgod.web.BasicDbControllerAdapter#query(java.lang.Object)
     */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    @ResponseBody
    @Override
    public ResultDto<T> query(@RequestParam(required = false) T param) {
        return super.query(param);
    }

    /**
     * @see net.loyintean.blog.sixgod.web.BasicDbControllerAdapter#save(java.lang.Object)
     */
    @RequestMapping(method = RequestMethod.POST, path = "/")
    @ResponseBody
    @Override
    public ResultDto<T> save(@RequestParam(required = false) T param) {
        return super.save(param);
    }

    /**
     * @see net.loyintean.blog.sixgod.web.BasicDbControllerAdapter#edit(java.lang.Object)
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/")
    @ResponseBody
    @Override
    public ResultDto<T> edit(@RequestParam(required = false) T param) {
        return super.edit(param);
    }

    /**
     * @see net.loyintean.blog.sixgod.web.BasicDbControllerAdapter#remove(java.lang.Object)
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/")
    @ResponseBody
    @Override
    public ResultDto<T> remove(@RequestParam(required = false) T param) {
        return super.remove(param);
    }

    /**
     * @see net.loyintean.blog.sixgod.web.BasicDbControllerAdapter#queryList(java.lang.Object)
     */
    @RequestMapping(method = RequestMethod.GET, path = "/list/")
    @ResponseBody
    @Override
    public ResultDto4List<T> queryList(
            @RequestParam(required = false) T param) {
        return super.queryList(param);
    }

    /**
     * @see net.loyintean.blog.sixgod.web.BasicDbControllerAdapter#queryPagedList(java.lang.Object)
     */
    @RequestMapping(method = RequestMethod.GET, path = "/paged/")
    @ResponseBody
    @Override
    public ResultDto4PagedList<T> queryPagedList(
            @RequestParam(required = false) T param) {
        return super.queryPagedList(param);
    }

}
