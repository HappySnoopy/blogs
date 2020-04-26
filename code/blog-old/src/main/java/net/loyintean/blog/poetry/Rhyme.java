/**
 * All Rights Reserved
 */
package net.loyintean.blog.poetry;

/**
 * @author linjun
 * @since 2018年2月5日
 */
public interface Rhyme {

    /**
     * 获取韵部名称
     *
     * @author linjun
     * @since 2018年2月5日
     * @param i
     * @return
     */
    String getRhymeName();

    /** 获取指定的韵部节点
     * @author linjun
     * @since 2018年2月5日
     * @param i
     * @return
     */
    Node getNode(int i);

}
