/**
 * 
 * All Rights Reserved
 */
package net.loyintean.blog.sixgod.dto;

/**
 * 查询分页列表的结果
 * <p>
 * 除列表数据外，还需要提供总数据量
 *
 * @param <T> 列表元素数据类型
 * @author Snoopy
 */
public class ResultDto4PagedList<T> extends ResultDto4List<T> {
    /**
     *
     */
    private static final long serialVersionUID = -4275284556731931815L;
    /**
     * 总数据量
     * <p>
     * 如果没有任何数据，应返回0
     */
    private long totalCount;

    /**
     * @return the {@link #totalCount}
     */
    public long getTotalCount() {
        return this.totalCount;
    }

    /**
     * @param totalCount
     *        the {@link #totalCount} to set
     */
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
