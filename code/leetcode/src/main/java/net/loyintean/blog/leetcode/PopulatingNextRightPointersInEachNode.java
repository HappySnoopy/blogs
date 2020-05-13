package net.loyintean.blog.leetcode;


import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class PopulatingNextRightPointersInEachNode {

    @Test
    public void test() {

        test(1, 2, 3, 4, 5, 6, 7);
    }

    private void test(Integer... treeList) {

        Node root = build(treeList);

        travel(new ArrayList<>(Arrays.asList(root)));

        Node result = new Solution().connect(root);
        travel(new ArrayList<>(Arrays.asList(result)));


        assertTravel(result, 1, 2, 3, 4, 5, 6, 7);
    }

    /**
     * Travel.
     *
     * @param oneLevel the one level
     */
    private void travel(List<Node> oneLevel) {

        List<Node> next = new LinkedList<>();

        System.out.print(" - ");
        for (Iterator<Node> iterator = oneLevel.iterator(); iterator.hasNext(); iterator.remove()) {
            Node current = iterator.next();
            if (current != null) {
                System.out.print(current.val + " -> " + current.next + ";");
                next.add(current.left);
                next.add(current.right);
            } else {
                System.out.print("null; ");
            }
        }
        System.out.println();
        if (!next.isEmpty()) {
            travel(next);
        }
    }

    private void assertTravel(Node root, Integer... rightLink) {

        List<Integer> link = new LinkedList<>();

        Node current = root;
        Node mostLeft = root;
        while (current != null) {

            link.add(current.val);

            if (current.next != null) {
                current = current.next;
            } else {
                current = mostLeft.left;
                mostLeft = current;
            }

        }

        System.out.println(link);

        assertEquals(rightLink.length, link.size());

        for (int i = 0; i < rightLink.length; i++) {
            assertEquals(rightLink[i], link.get(i));
        }
    }


    /**
     * Build tree node.
     *
     * @param treeList the tree list
     * @return the tree node
     */
    private Node build(Integer... treeList) {

        // 准备工作：往数组前面放一个null，作为header，以保证遍历下标从1开始
        Integer[] forLoop = new Integer[treeList.length + 1];
        System.arraycopy(treeList, 0, forLoop, 1, treeList.length);

        Node[] tree = new Node[forLoop.length];
        for (int index = 1; index < forLoop.length && forLoop[index] != null; index++) {
            if (tree[index] == null) {
                tree[1] = new Node(forLoop[index]);
            }

            // 遍历下标从1开始，则左右子树的下标有这个规律
            int leftIndex = 2 * index;
            int rightIndex = leftIndex + 1;

            // System.out.println(i + ";" + leftIndex + ";" + rightIndex + ";" + forLoop + ";" + Arrays.toString(tree));

            if (leftIndex < forLoop.length && forLoop[leftIndex] != null) {
                Node left = new Node(forLoop[leftIndex]);
                tree[index].left = left;
                tree[leftIndex] = left;
            }

            if (rightIndex < forLoop.length && forLoop[rightIndex] != null) {
                Node right = new Node(forLoop[rightIndex]);
                tree[index].right = right;
                tree[rightIndex] = right;
            }
        }

        Node root = tree[1];
        return root;
    }

    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        @Override
        public String toString() {
            return Integer.toString(val);
        }
    }

    private class Solution {
        public Node connect(Node root) {

            // connect(root, null);
            connect1(root);
            return root;
        }

        /**
         * 这里一定要注意顺序：必须保证从左往右去串联左右子树
         *
         * @param left
         * @param right
         */
        public void connect(Node left, Node right) {

            if (left != null) {
                left.next = right;
                connect(left.left, left.right);
            }


            if (left != null && right != null) {
                connect(left.right, right.left);
            }

            if (right != null) {

                connect(right.left, right.right);

                connect(right.right, null);
            }

        }

        public void connect1(Node root) {
            if (root == null || root.left == null) {
                return;
            }


            root.left.next = root.right;

            if (root.next != null) {
                // 可以用next直接找到自己的兄弟
                root.right.next = root.next.left;
            }
            connect1(root.left);
            connect1(root.right);
        }
    }
}