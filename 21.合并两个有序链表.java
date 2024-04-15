/*
 * @lc app=leetcode.cn id=21 lang=java
 *
 * [21] 合并两个有序链表
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 合并有序链表的解法，用dummy虚拟指针
        // 当你需要创造一条新链表的时候，可以使用虚拟头结点简化边界情况的处理。
        // 一条链表分解成两条链表，是不是也在创造新链表？这些情况都可以使用虚拟头结点简化边界情况的处理。
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode p1 = list1;
        ListNode p2 = list2;

        // 因为要合并有序的链表，所以要把两个原有的单链表进行遍历，然后按照大小顺序处理
        while (p1 != null && p2 != null) {
            // 因为是升序处理，所以小的拼接前面
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }

        // [1,2,3,4,5,6]
        // [1,1,2]
        // [3,4,5,6]
        // [1,1,1,2,2]
        // 当上面执行完毕，说明list1或者list2已经到头了，说明剩下的链表比另外一个链表要长
        // 而且在遍历短链表的时候，已经按照大小的顺序处理好了，所以
        // 将剩下不为空的链表直接添加上去即可，
        if (p1 != null){
            p.next = p1;
        }

        if (p2 != null) {
            p.next = p2;
        }

        // 因为p和dummy是绑定的，而dummy就是-1，所以要他next才是真实数据
        return dummy.next;
    }
}
// @lc code=end

