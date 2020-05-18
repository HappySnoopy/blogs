package net.loyintean.blog.leetcode;

/**
 * https://leetcode-cn.com/problems/string-rotation-lcci/
 *
 * @author 林俊 <junlin8@creditease.cn>
 * @date 2020 -05-15
 */
public class StringRotationLcci {

    /**
     * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
     * <p>
     * 不是反转，而是按左出右进或者右出左进生成的新字符串
     * <p>
     * <p>
     * 提示：
     * <p>
     * 字符串长度在[0, 100000]范围内。
     * 说明:
     * <p>
     * 你能只调用一次检查子串的方法吗？
     *
     * @author 林俊 <junlin8@creditease.cn>
     * @date 2020 -05-15
     */
    class Solution {
        /**
         * 标准答案
         * <p>
         * 如果s2是s1轮转过来的，说明s2 = s1的结尾 + s1的开头
         * 那么s2+s2 = s1的结尾 + s1的开头 + s1的结尾 + s1的开头 = s1的结尾 + s1 + s1的开头
         * <p>
         * 这样，用一次contains就可以完成任务了。
         *
         * @param s1 the s 1
         * @param s2 the s 2
         * @return the boolean
         */
        public boolean isFlipedString(String s1, String s2) {
            if (s1.length() != s2.length()) {
                return false;
            }
            String ss = s2 + s2;
            return ss.contains(s1);
        }
    }
}
