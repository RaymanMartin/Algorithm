/*
 * @lc app=leetcode.cn id=83 lang=java
 *
 * [83] 删除排序链表中的重复元素
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
    public ListNode deleteDuplicates(ListNode head) {
        // 重复元素链表的方式也是和重复数组的方式一致。
        // 原地删除都用快慢指针。
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        // 开始遍历
        while (fast != null) {
            if (slow.val != fast.val) {
                // 两者数值不相等，更换指针
                // 不需要主动将一些不关联的next指向为null，因为JVM会清除
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        // 断开之后的全部节点
        slow.next = null;
        // 返回head节点
        return head;
    }
}
// @lc code=end

