/*
 * @lc app=leetcode.cn id=86 lang=java
 *
 * [86] 分隔链表
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        // 题目的解法思路类似，将原有的链表拆分，相当将链表拆分两条然后再合并起来
        // 就是按照target大小拆分链表即可
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        ListNode p1 = dummy1;
        ListNode p2 = dummy2;

        ListNode p = head;
        while (p != null) {
            // 假若比目标值要小，那么就将这个节点添加到dummy上
            if (p.val < x) {
                p1.next = p;
                p1 = p1.next;
            }else{
                // 假若比值要大，那么就将其赋值到p2
                p2.next = p;
                p2 = p2.next;
            }
            // 原有链表继续执行
            // p = p.next;

            // [1,4,3,2,5,2]
            // p1 = [1]
            // p = [4,3,2,5,2] 
            // 但是这样会出现问题，因为原有的1会直接关联到后面的4，但是p1已经是持有了完整链表
            // p1 = [-1,1,4,3,2,5,2]
            // 当使用p = p.next，那么p2就是[-1,4,3,2,5,2]
            // 所以这两条链表就存在相交点，就会在p1和p2形成环
            // [-1,1,2,2,5,3,4]，因为2一直会指向后序的5,而且和4,3,5形成环
            // **涉及到拆分两个链表的，注意环的问题**
            // 所以需要将后序的节点都作为单个存在，断开然后再添加
            // 相当于先将1.next断开了，让1成为单独的节点
            // 断开原链表中的每个节点的 next 指针
            ListNode temp = p.next;
            p.next = null;
            p = temp;
        }

        // 条件执行完毕，说明链表被拆分完毕了
        p1.next = dummy2.next;

        return dummy1.next;
    }
}
// @lc code=end

