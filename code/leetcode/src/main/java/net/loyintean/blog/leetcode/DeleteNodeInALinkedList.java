package net.loyintean.blog.leetcode;

/**
 * 删除链表中的节点
 * <p>
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
 *
 * @author 林俊 <junlin8@creditease.cn>
 * @date 2020 -05-14
 * @see <a href="https://leetcode-cn.com/problems/delete-node-in-a-linked-list/>删除链表中的节点</a>
 */
public class DeleteNodeInALinkedList {

    static class Solution {
        /** 只给出了要删除的节点node。没有给定head */
        public void deleteNode(ListNode node) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    /**
     * Definition for singly-linked list.
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}




