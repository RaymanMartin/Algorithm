/*
 * @lc app=leetcode.cn id=26 lang=java
 *
 * [26] 删除有序数组中的重复项
 */

// @lc code=start
class Solution {
    public int removeDuplicates(int[] nums) {
        // 涉及到原地操作数组、链表直接用快慢指针处理
        // 因为数组本身有序，所以只需要快指针与慢指针
        // 整体逻辑是这样的，快慢指针一开始都是指向0的位置
        // 快指针每次先去前进一步，然后判断值是否和slow的相等，相等就相当于重复值
        // 假若不相等，则说明需要将fast的值放到slow，slow也需要自增
        if (nums.length == 0) {
            return nums.length;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                // 假若不相等，说明需要将值替换
                slow++;
                nums[slow] = nums[fast];
            }
            // 每次fast都固定先
            fast++;
        }
        // 上述执行完毕后，因为需要返回的是去重后的长度，
        // 而slow又是刚刚好最后一个不为重复的index，所以返回slow+1
        return slow + 1;
        
    }
}
// @lc code=end

