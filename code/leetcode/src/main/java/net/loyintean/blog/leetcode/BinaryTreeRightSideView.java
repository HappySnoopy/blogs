package net.loyintean.blog.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/binary-tree-right-side-view/submissions/
 *
 * @author Snoopy
 * @date 2020 -04-22
 */
class BinaryTreeRightSideView {

    /**
     * The type Solution.
     *
     * @author Snoopy
     * @date 2020 -04-22
     */
    static class Solution {

        /**
         * Build tree node.
         *
         * @param treeList the tree list
         * @return the tree node
         */
        TreeNode build(Integer... treeList) {

            // 准备工作：往数组前面放一个null，作为header，以保证遍历下标从1开始
            Integer[] forLoop = new Integer[treeList.length + 1];
            System.arraycopy(treeList, 0, forLoop, 1, treeList.length);

            TreeNode[] tree = new TreeNode[forLoop.length];
            for (int index = 1; index < forLoop.length && forLoop[index] != null; index++) {
                if (tree[index] == null) {
                    tree[1] = new TreeNode(forLoop[index]);
                }

                // 遍历下标从1开始，则左右子树的下标有这个规律
                int leftIndex = 2 * index;
                int rightIndex = leftIndex + 1;

                // System.out.println(i + ";" + leftIndex + ";" + rightIndex + ";" + forLoop + ";" + Arrays.toString(tree));

                if (leftIndex < forLoop.length && forLoop[leftIndex] != null) {
                    TreeNode left = new TreeNode(forLoop[leftIndex]);
                    tree[index].left = left;
                    tree[leftIndex] = left;
                }

                if (rightIndex < forLoop.length && forLoop[rightIndex] != null) {
                    TreeNode right = new TreeNode(forLoop[rightIndex]);
                    tree[index].right = right;
                    tree[rightIndex] = right;
                }
            }

            return tree[1];
        }

        /**
         * Right side view list.
         *
         * @param root the root
         * @return the list
         */
        List<Integer> rightSideView(TreeNode root) {

            Queue<Queue<TreeNode>> tree = new LinkedList<>();
            Queue<TreeNode> level0 = new LinkedList<>();
            level0.add(root);
            tree.add(level0);

            return travel(tree);
        }


        /**
         * 二维队列。
         * <p>
         * 第一维：树的层级
         * 第二维：每一层上的节点（从右往做计算）
         * <p>
         * 如果要只是用一维队列，需要知道什么时候节点换层了。
         *
         * @param tree 整棵树。初始时只要一个根节点
         */
        private List<Integer> travel(Queue<Queue<TreeNode>> tree) {

            List<Integer> result = new LinkedList<>();

            while (!tree.isEmpty()) {
                // 从上到下遍历树的层级

                // 取出最上面这一层
                Queue<TreeNode> oneLevel = tree.poll();

                if (oneLevel != null) {
                    // 取最右的节点，作为右侧视图
                    if (oneLevel.peek() != null) {
                        result.add(oneLevel.peek().val);
                    }

                    Queue<TreeNode> nextLevel = new LinkedList<>();
                    while (!oneLevel.isEmpty()) {
                        // 从右往左遍历当前这一层
                        TreeNode node = oneLevel.poll();

                        if (node != null) {
                            // 这里先放节点、再放左节点，即可保证遍历顺序是从右到左的。
                            if (node.right != null) {
                                nextLevel.add(node.right);
                            }
                            if (node.left != null) {
                                nextLevel.add(node.left);
                            }
                        }

                    }
                    if (!nextLevel.isEmpty()) {
                        tree.add(nextLevel);
                    }
                }

            }

            return result;
        }
    }

    /**
     * The type Solution1.
     *
     * @author Snoopy
     * @date 2020 -05-13
     */
    static class Solution1 extends Solution {
        /**
         * Right side view list.
         *
         * @param root the root
         * @return the list
         */
        @Override
        List<Integer> rightSideView(TreeNode root) {


            List<Integer> result = new LinkedList<>();

            travel(root, 0, result);

            return result;

        }

        /**
         * 递归获取每层的最右节点
         *
         * @param mostRight the most right
         * @param level     the level
         * @param result    the result
         */
        private void travel(TreeNode mostRight, int level, List<Integer> result) {

            System.out.println(mostRight + ";" + level + ";" + result);

            if (mostRight != null) {
                if (level >= result.size() || result.get(level) == null) {
                    // 当前这层还没有“最右”节点时，当前节点就是“最右”节点
                    result.add(mostRight.val);
                }
                travel(mostRight.right, level + 1, result);

                travel(mostRight.left, level + 1, result);
            }
        }
    }

    /**
     * The type Solution2.
     *
     * @author Snoopy
     * @date 2020 -05-13
     */
    static class Solution2 extends Solution {

        /**
         * Right side view list.
         *
         * @param root the root
         * @return the list
         */
        @Override
        List<Integer> rightSideView(TreeNode root) {


            List<Integer> result = new LinkedList<>();
            Queue<TreeNode> level0 = new LinkedList<>();
            level0.add(root);

            travel(level0, result);

            return result;
        }

        /**
         * 广度优先、从右往左遍历
         *
         * @param oneLevel the one level
         */
        private void travel(Queue<TreeNode> oneLevel, List<Integer> result) {

            // 只拿数据，不remove
            if (oneLevel.peek() != null) {
                result.add(oneLevel.peek().val);
            }

            Queue<TreeNode> nextLevel = new LinkedList<>();

            while (!oneLevel.isEmpty()) {
                // 当本层还有数据时
                TreeNode current = oneLevel.poll();
                if (current != null) {
                    if (current.right != null) {
                        nextLevel.add(current.right);
                    }
                    if (current.left != null) {
                        nextLevel.add(current.left);
                    }
                }
            }
            if (!nextLevel.isEmpty()) {
                travel(nextLevel, result);
            }
        }
    }

    /**
     * The type TreeNode.
     *
     * @author Snoopy
     * @date 2020 -04-22
     */
    static class TreeNode {
        /**
         * The Val.
         */
        int val;
        /**
         * The Left.
         */
        TreeNode left;
        /**
         * The Right.
         */
        TreeNode right;

        /**
         * Instantiates a new Tree node.
         *
         * @param x the x
         */
        TreeNode(int x) {
            val = x;
        }

        /**
         * To string string.
         *
         * @return the string
         */
        @Override
        public String toString() {
            return val + "(" + left + "," + right + ")";
        }
    }

}