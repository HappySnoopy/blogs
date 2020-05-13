package net.loyintean.blog.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BinaryTreeRightSideViewTest {

    /**
     * The Solution.
     */
    private BinaryTreeRightSideView.Solution solution;

    /**
     * Test.
     */
    @Test
    public void test() {
        solution = new BinaryTreeRightSideView.Solution();
        // pass
        test(Arrays.asList(1, 3, 4), 1, 2, 3, null, 5, null, 4);
        test(Arrays.asList(1, 3, 4), 1, 2, 3, 4);
    }

    /**
     * Test.
     */
    @Test
    public void test1() {
        solution = new BinaryTreeRightSideView.Solution1();
        // pass
        test(Arrays.asList(1, 3, 4), 1, 2, 3, null, 5, null, 4);
        test(Arrays.asList(1, 3, 4), 1, 2, 3, 4);
    }


    /**
     * Test.
     */
    @Test
    public void test2() {
        solution = new BinaryTreeRightSideView.Solution2();
        // pass
        test(Arrays.asList(1, 3, 4), 1, 2, 3, null, 5, null, 4);
        test(Arrays.asList(1, 3, 4), 1, 2, 3, 4);
    }


    /**
     * Test.
     *
     * @param result   the result
     * @param treeList the tree list
     */
    private void test(List<Integer> result, Integer... treeList) {

        BinaryTreeRightSideView.TreeNode root = solution.build(treeList);

        // travel(new LinkedList<>(Arrays.asList(root)));

        List<Integer> r = solution.rightSideView(root);
        assertEquals(r.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(r.get(i), result.get(i));
        }
    }
}