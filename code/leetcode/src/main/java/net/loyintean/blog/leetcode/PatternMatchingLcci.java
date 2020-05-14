package net.loyintean.blog.leetcode;

/**
 * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。
 * 例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。
 * 但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pattern-matching-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 林俊 <junlin8@creditease.cn>
 * @date 2020 -05-14
 * @see <a href="https://leetcode-cn.com/problems/pattern-matching-lcci/">模式匹配</a>
 */
public class PatternMatchingLcci {

    /**
     * The type Solution.
     *
     * @author 林俊 <junlin8@creditease.cn>
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

            // 解方程： x * aCount + y * bCount = value.length()，求x和y都有哪几种组合
            int bound = value.length();
            for (int x = 0; x <= bound; x++) {
                int y = bCount == 0 ? 0 : Math.max(0, (bound - (x * aCount)) / bCount);
                // 找到符合条件的x和y
                if (x * aCount + y * bCount == value.length()) {
                    System.out.println(x + "*" + aCount + "+" + y + "*" + bCount + "=" + value.length());
                    // 依次取出a的模式串和b的模式串
                    int pattenStart = 0;
                    String aPattern = null;
                    String bPattern = null;
                    boolean matched = true;
                    for (char c : pattern.toCharArray()) {
                        if ('a' == c) {
                            String aStr = value.substring(pattenStart, pattenStart + x);
                            System.out.println(
                                    "from pattenStart" + pattenStart + " to " + (pattenStart + x) + "; aPattern = " + aPattern + "; a = " + aStr);
                            if (aPattern == null) {
                                // 找到了模式
                                aPattern = aStr;
                            } else if (!aPattern.equals(aStr)) {
                                // 模式不匹配，则跳出此循环，继续比较后面的模式
                                matched = false;
                                break;
                            }
                            pattenStart += x;
                        } else {
                            String bStr = value.substring(pattenStart, pattenStart + y);
                            System.out.println(
                                    "from pattenStart" + pattenStart + " to " + (pattenStart + y) + "; bPattern = " + bPattern + "; b = " + bStr);
                            if (bPattern == null) {
                                // 找到了模式
                                bPattern = bStr;
                            } else if (!bPattern.equals(bStr)) {
                                // 模式不匹配，则跳出此循环，继续比较后面的模式
                                matched = false;
                                break;
                            }
                            pattenStart += y;
                        }
                    }
                    // 如果找到了匹配的模式，并且a模式和b模式不相等，那么直接返回结果
                    if (matched && (aPattern != bPattern)) {
                        return true;
                    }
                } else {
                    System.out.println(x + "*" + aCount + "+" + y + "*" + bCount + "!=" + value.length());
                }
            }

            return false;
        }

        private int countA(String pattern) {
            int aCount = 0;
            for (char c : pattern.toCharArray()) {
                if ('a' == c) {
                    aCount++;
                }
            }
            return aCount;
        }

        private boolean isBlank(String s) {
            return s == null || s.length() == 0;
        }
    }
}
