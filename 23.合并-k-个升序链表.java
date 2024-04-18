/*
 * @lc app=leetcode.cn id=23 lang=java
 *
 * [23] 合并 K 个升序链表
 */

// @lc code=start

import java.util.Arrays;
import java.util.PriorityQueue;

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

    public ListNode mergeKLists(ListNode[] lists) {
        // 可以用双链表合并的方式处理
        //return mergeKListsByDoubleLinks(lists);

        // 使用数据结构处理，用到上层数据结构PriorityQueue实现，这个数据结构结合使用的场景很多
        // 也是经常使用的一个办法
        return mergeKListsByQueue(lists);

        // 还有因为题目没限制保持原有链表，可以直接新建链表，然后将其添加到数组
        // 再用数组排序，然后再转回链表
        // return mergeKListsByArray(lists);
    }

    // 解法一：基于之前的有序双链表合并的逻辑处理
    public ListNode mergeKListsByDoubleLinks(ListNode[] lists) {
        // 序列的合并有点像合并两个有序链表，只是他传递的参数是个链表数组
        // 不过好像不确定需要定义多少个虚拟头节点。之前是两个合并成一个，所以定义成一个即可
        // 现在是n多个合并成一个，应该也是定义一个就好了
        ListNode p = null;

        // 感觉还是相当于n个链表合并，每次将合并完新的链表再次添加多一次
        for (int i = 0; i < lists.length; i++) {
            p = mergeDoubleList(p, lists[i]);
        }
        return p;
    }

    // 使用之前的合并两个有序链表的逻辑
    public ListNode mergeDoubleList(ListNode list1,ListNode list2){
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode p1 = list1;
        ListNode p2 = list2;

        // 判断p1 p2是否为空，为空则返回另一个不空的链表即可
        if (p1 == null || p2 == null) {
            return p1 == null ? p2 : p1;
        }

        // 开始遍历p1和p2
        while (p1 != null && p2 != null) {
            // 假若p1比p2小，那么将p1添加到dummy上
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            // 处理p节点自增
            p = p.next;
        }
        if (p1 != null) {
            p.next = p1;
        }

        if (p2 != null) {
            p.next = p2;
        }
        return dummy.next;
    }

    // 解法二：基于PriorityQueue
    public ListNode mergeKListsByQueue(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        // 虚拟头结点
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;

        // PriorityQueue实际上就是最小堆，
        // 就是你将n个链表放到队列内，自动将链表的头结点按照大小顺序排列了
        // [1,3,4]
        // [1,4,5]
        // [4,6]
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            lists.length, (a, b)->(a.val - b.val));
        // 将头节点放入堆
        for(ListNode head : lists) {
            if (head != null) {
                pq.offer(head);
            }
        }

        // 堆的重建
        while (!pq.isEmpty()) {
            // 已经是最小的节点在头部了
            ListNode node = pq.poll();
            p.next = node;

            // 然后将他下一个的节点继续放入最小堆去排序
            if (node.next != null) {
                pq.add(node.next);
            }

            // p指针自增
            p = p.next;
        }
        return dummy.next;
    }

    // 解法三：数组重建
    public ListNode mergeKListsByArray(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 先计算出大小
        int maxSize = 0;
        for (ListNode lists2 : lists) {
            while (lists2 != null) {
                maxSize++;
                lists2 = lists2.next;
            }
        }
        
        // 假若链表都为空，直接返回
        if (maxSize == 0) {
            return null;
        }

        // 数组组建
        int[] array = new int[maxSize];
        int index = 0;
        for (ListNode p : lists) {
            while (p != null) {
                array[index] = p.val;
                p = p.next;
                index++;
            }
        }

        // 执行后数组建立了，直接排序
        Arrays.sort(array);

        // 重组为链表
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode init = new ListNode(array[0]);
        p.next = init;
        for (int i = 1; i < maxSize; i++) {
            init.next = new ListNode(array[i]);
            init = init.next;
        }
        return dummy.next;
    }
}
// @lc code=end

