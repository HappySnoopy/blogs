/**
 * All Rights Reserved
 */
package net.loyintean.blog.poetry;

/**
 * @author Snoopy
 * @since 2018年2月5日
 */
public interface Rhyme {

    /**
     * 获取韵部名称
     *
     * @param i
     * @return
     * @author Snoopy
     * @since 2018年2月5日
     */
    String getRhymeName();

    /**
     * 获取指定的韵部节点
     *
     * @param i
     * @return
     * @author Snoopy
     * @since 2018年2月5日
     */
    Node getNode(int i);

}
