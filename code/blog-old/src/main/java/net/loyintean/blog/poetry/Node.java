/**
 * All Rights Reserved
 */
package net.loyintean.blog.poetry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Snoopy
 * @since 2018年2月5日
 */
public class Node {

    private String type;

    private List<String> nodes;

    /**
     * @param type
     * @param nodes
     *        不带任何分隔符，直接拼接的字符串。<br>
     *        如：“庚更横盛正应乘胜兴行莹茔凭”
     */
    public Node(String type, String nodes) {
        super();
        this.type = type;

        char[] chars = nodes.toCharArray();
        this.nodes = new ArrayList<>(chars.length);

        for (char d : chars) {
            this.nodes.add(Character.toString(d));
        }
    }

    /**
     * @return the {@link #type}
     */
    public String getType() {
        return this.type;
    }

    /**
     * 获取韵脚
     *
     * @param i
     * @return
     * @author Snoopy
     * @since 2018年2月5日
     */
    public String getRhyme(int i) {
        return this.nodes.get(i % this.nodes.size());
    }
}
