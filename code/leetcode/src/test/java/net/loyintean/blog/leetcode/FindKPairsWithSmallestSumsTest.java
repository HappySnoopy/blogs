package net.loyintean.blog.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class FindKPairsWithSmallestSumsTest {
    FindKPairsWithSmallestSums.Solution solution = new FindKPairsWithSmallestSums().new Solution();

    @Test
    public void test() {
        test(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3, new Integer[][]{{1, 2}, {1, 4}, {1, 6}});
        test(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2, new Integer[][]{{1, 1}, {1, 1}});
        test(new int[]{1}, new int[]{1}, 2, new Integer[][]{{1, 1}, {1, 1}});
        test(new int[]{}, new int[]{}, 5, new Integer[][]{});

        test(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 10,
                new Integer[][]{{1, 1}, {1, 1}, {2, 1}, {1, 2}, {1, 2}, {2, 2}, {1, 3}, {1, 3}, {2, 3}});
    }

    private void test(int[] nums1, int[] nums2, int k, Integer[][] assertArray) {
        List<List<Integer>> lists = solution.kSmallestPairs(nums1, nums2, k);
        System.out.println("assert = " + this.toString(assertArray));

        assertEquals(assertArray.length, lists.size());

        for (int array1Index = 0; array1Index < assertArray.length; array1Index++) {
            Integer[] array1 = assertArray[array1Index];
            List<Integer> list1 = lists.get(array1Index);
            assertEquals(array1.length, list1.size());
            for (int array2Index = 0; array2Index < array1.length; array2Index++) {
                assertEquals(array1[array2Index], list1.get(array2Index));
            }
        }
    }

    private String toString(Integer[][] assertArray) {
        return Stream.of(assertArray).map(Arrays::toString).collect(Collectors.joining(",", "[", "]"));
    }
}