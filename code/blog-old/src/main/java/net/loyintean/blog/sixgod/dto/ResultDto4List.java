/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.dto;

import java.util.Collections;
import java.util.List;

/**
 * 查询列表数据的结果
 *
 * @param <T> 列表元素数据类型
 * @author Snoopy
 */
public class ResultDto4List<T> extends ResultDto<T> {

    /**
     *
     */
    private static final long serialVersionUID = 1539281431549524424L;

    /**
     * 列表结果
     * <p>
     * 如果没有查到数据，应返回emptyList
     */
    private List<T> list = Collections.emptyList();

    /**
     * @return the {@link #list}
     */
    public List<T> getList() {
        return this.list;
    }

    /**
     * @param list
     *        the {@link #list} to set
     */
    public void setList(List<T> list) {
        this.list = list;
    }
}
