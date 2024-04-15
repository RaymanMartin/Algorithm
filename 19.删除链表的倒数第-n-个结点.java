/*
 * @lc app=leetcode.cn id=19 lang=java
 *
 * [19] 删除链表的倒数第 N 个结点
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 先找到倒数n的节点，因为dummy本身就有一个 -1的节点
        ListNode res = findFromEnd(dummy, n + 1);
        // 删除n节点，直接将next 指向next next即可
        res.next = res.next.next;
        return dummy.next;
    }

    private ListNode findFromEnd(ListNode head, int n) {
        ListNode p1 = head;
        // 先走n步
        for (int i = 0; i < n; i++) {
            p1 = p1.next;
        }

        ListNode p2 = head;
        // 此时p1再走下去，就是n-k了
        while (p1 != null) { 
            p2 = p2.next;
            p1 = p1.next;
        }
        
        return p2;
    }
}
// @lc code=end

