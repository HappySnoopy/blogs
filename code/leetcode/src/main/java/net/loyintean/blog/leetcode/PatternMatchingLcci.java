package net.loyintean.blog.leetcode;

import java.util.Objects;

/**
 * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。
 * 例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。
 * 但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pattern-matching-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Snoopy
 * @date 2020 -05-14
 * @see <a href="https://leetcode-cn.com/problems/pattern-matching-lcci/">模式匹配</a>
 */
public class PatternMatchingLcci {

    /**
     * The type Solution.
     *
     * @author Snoopy
     * @date 2020 -05-14
     */
    class Solution {
        /**
         * 首先解方程，计算出a和b分别代表的字符个数（会有多个解。有些解可以直接判定为true）
         * <p>
         * 然后根据方程式的解，将value拆分为不同的子串
         * <p>
         * 最后根据模式，比较各子串
         *
         * @param pattern the pattern
         * @param value   the value
         * @return the boolean
         */
        public boolean patternMatching(String pattern, String value) {

            System.out.println("pattern=" + pattern + "; value=" + value);

            if (isBlank(pattern)) {
                return isBlank(value);
            }

            // 计算出a和b的字符个数
            int aCount = countA(pattern);
            int bCount = pattern.length() - aCount;

            // 由于前面处理了isBlank(pattern)，aCount和bCount不可能都为0
            // 如果a模式长度为0，则b模式的长度是固定的；如果b模式长度为0，则a模式的长度是固定的
            // 此时，可以根据这个固定的长度直接判断是否
            if (aCount == 0) {
                if (value.length() / bCount > 0) {
                    return isMatch(pattern, value, 0, value.length() / bCount);
                } else {
                    for (int i = 1; i < value.length(); i++) {
                        if (isMatch(pattern, value, 0, i)) {
                            return true;
                        }
                    }
                    return false;
                }
            } else if (bCount == 0) {
                return isMatch(pattern, value, value.length() / aCount, 0);
            }

            // 只有当二者都不为0的时候，需要解方程
            // 既然二者都不为0，那么，x的最大值就是value.length()/acount

            // 解方程： x * aCount + y * bCount = value.length()，求x和y都有哪几种组合
            for (int x = 0; x <= value.length() / aCount; x++) {
                int y = (value.length() - (x * aCount)) / bCount;
                // 虽然y是根据x计算出来的，但是考虑到有除法，这里还需要再做一次计算
                // 找到符合条件的x和y
                if (x * aCount + y * bCount == value.length()) {
                    System.out.println(x + "*" + aCount + "+" + y + "*" + bCount + "=" + value.length());
                    if (isMatch(pattern, value, x, y)) {
                        return true;
                    }
                } else {
                    System.out.println(x + "*" + aCount + "+" + y + "*" + bCount + "!=" + value.length());
                }
            }

            return false;
        }

        /**
         * 根据给定的参数，判断value是否匹配pattern
         *
         * @param pattern 目标模式
         * @param value   待匹配字符串
         * @param aLength pattern中'a'模式的长度
         * @param bLength pattern中'b'模式的长度
         * @return true为匹配；false为不匹配
         */
        private boolean isMatch(String pattern, String value, int aLength, int bLength) {
            System.out.println(
                    "pattern = " + pattern + "; value = " + value + "; aLength = " + aLength + "; bLength = " + bLength);
            // 依次取出a的模式串和b的模式串
            int pattenStart = 0;
            String aPattern = null;
            String bPattern = null;
            boolean matched = true;
            for (char c : pattern.toCharArray()) {
                if ('a' == c) {
                    String aStr = value.substring(pattenStart, pattenStart + aLength);
                    System.out.println(
                            "from pattenStart" + pattenStart + " to " + (pattenStart + aLength) + "; aPattern = " + aPattern + "; a = " + aStr);
                    if (aPattern == null) {
                        // 找到了模式
                        aPattern = aStr;
                    } else if (!aPattern.equals(aStr)) {
                        // 模式不匹配，则跳出此循环，继续比较后面的模式
                        matched = false;
                        break;
                    }
                    pattenStart += aLength;
                } else {
                    String bStr = value.substring(pattenStart, pattenStart + bLength);
                    System.out.println(
                            "from pattenStart" + pattenStart + " to " + (pattenStart + bLength) + "; bPattern = " + bPattern + "; b = " + bStr);
                    if (bPattern == null) {
                        // 找到了模式
                        bPattern = bStr;
                    } else if (!bPattern.equals(bStr)) {
                        // 模式不匹配，则跳出此循环，继续比较后面的模式
                        matched = false;
                        break;
                    }
                    pattenStart += bLength;
                }
            }
            // 如果找到了匹配的模式，并且a模式和b模式不相等，那么直接返回结果
            return matched && !Objects.equals(aPattern, bPattern);
        }

        /**
         * Count a int.
         *
         * @param pattern the pattern
         * @return the int
         */
        private int countA(String pattern) {
            int aCount = 0;
            for (char c : pattern.toCharArray()) {
                if ('a' == c) {
                    aCount++;
                }
            }
            return aCount;
        }

        /**
         * Is blank boolean.
         *
         * @param s the s
         * @return the boolean
         */
        private boolean isBlank(String s) {
            return s == null || s.length() == 0;
        }
    }
}
