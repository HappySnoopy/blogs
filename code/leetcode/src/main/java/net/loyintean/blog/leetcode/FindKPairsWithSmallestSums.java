package net.loyintean.blog.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/
 *
 * @author Snoopy
 * @date 2020 -05-15
 */
public class FindKPairsWithSmallestSums {

    /**
     * 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。
     * <p>
     * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。
     * <p>
     * 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @author Snoopy
     * @date 2020 -05-15
     */
    class Solution {
        /**
         * 用归并的思路：
         * <p>
         * 从左往右查找两个数组中最小的数字，组合成一对。
         * <p>
         * 如：nums1 = [1,7,11], nums2 = [2,4,6], k = 3
         * 初始化：[1,2]
         * n1[0] + n2[1] = 1+4 < n2[0] + n1[1] = 2+7 --> [1,4]
         * n1[0] + n2[2] = 1+6 < n2[0] + n1[1] = 2+7 --> [1,6]
         * n1[1] + n2[1] = 7+4 !< n2[0] + n1[1] = 2+7 --> [2,7]
         * n1[1] + n2[1] = 7+4 < n2[0] + n1[2] = 2+11 --> [7,4]
         * n1[1] + n2[2] = 7+6 !< n2[0] + n1[2] = 2+11 --> [2,11]
         * n1[1] + n2[2] = 7+6 <  n2[1] + n1[2] = 4+11 --> [7,6]
         * n1[2] + n2[1] = 11+4 !< n2[1] + n1[2] = 4+7 --> [7,6]
         *
         * @param nums1 the nums 1
         * @param nums2 the nums 2
         * @param k     the k
         * @return the list
         */
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            System.out.println("=================================");
            System.out.println("nums1=" + Arrays.toString(nums1) + "; nums2=" + Arrays.toString(nums2) + "; k=" + k);

            List<List<Integer>> result = new ArrayList<>(k);

            if (nums1.length == 0 || nums2.length == 0) {
                return result;
            }
            // 两个基准数的下标
            int base1 = 0, base2 = 0;
            // 先加上最初的这一对
            result.add(Arrays.asList(nums1[base1], nums2[base2]));
            // 两个加数的下标
            int with1 = Integer.min(base1 + 1, nums1.length - 1);
            int with2 = Integer.min(base2 + 1, nums2.length - 1);

            while (k > 1) {

                if (nums1[base1] + nums2[with2] < nums2[base2] + nums1[with1]) {
                    System.out.println(
                            "base1=" + base1 + "; with2=" + with2 + "; base2=" + base2 + "; with1=" + with1 + "; nums1" + "[base1]=" + nums1[base1] + "; nums2[with2]=" + nums2[with2] + "; nums2[base2]=" + nums2[base2] + "; nums1[with1]=" + nums1[with1] + "; find:" + nums1[base1] + "," + nums2[with2]);
                    // 在base1的基础上，用with2去扫描nums2
                    result.add(Arrays.asList(nums1[base1], nums2[with2]));
                    if (with2 == nums2.length - 1) {
                        // 如果nums2被扫完了，则重新定义base1
                        // 并开始扫描nums2中在base2之后的数据
                        base1 = Integer.min(base1 + 1, nums1.length - 1);
                        with2 = Integer.min(base2 + 1, nums2.length - 1);
                    } else {
                        with2++;
                    }
                } else {
                    System.out.println(
                            "base1=" + base1 + "; with2=" + with2 + "; base2=" + base2 + "; with1=" + with1 + "; nums1" + "[base1]=" + nums1[base1] + "; nums2[with2]=" + nums2[with2] + "; nums2[base2]=" + nums2[base2] + "; nums1[with1]=" + nums1[with1] + "; find:" + nums1[with1] + "," + nums2[base2]);
                    // 在base2的基础上，用with1去扫描nums1
                    result.add(Arrays.asList(nums1[with1], nums2[base2]));
                    if (with1 == nums1.length - 1) {
                        // 如果nums1被扫完了，则重新定义base2
                        // 并开始扫描nums1中在base1之后的数据
                        base2 = Integer.min(base2 + 1, nums1.length - 1);
                        with1 = Integer.min(base1 + 1, nums1.length - 1);
                    } else {
                        with1++;
                    }
                }
                // System.out.println(
                //         "nums1[base1]=" + nums1[base1] + "; nums2[with2]=" + nums2[with2] + "; nums2[base2]=" + nums2[base2] + "; nums1[with1]=" + nums1[with1] + "; result=" + result);
                k--;
            }

            System.out.println("result = " + result);
            return result;
        }
    }
}
