/*
 * @lc app=leetcode.cn id=283 lang=java
 *
 * [283] 移动零
 */

// @lc code=start
class Solution {
    public void moveZeroes(int[] nums) {
        // 移动0的方式和数组原地删除值一样
        int index = removeDuplicates(nums, 0);
        // 将index之后的值都写为0
        for (; index < nums.length; index++) {
            nums[index] = 0;
        }
    }

    private int removeDuplicates(int[] nums, int value){
        int slow = 0,fast = 0;
        while(fast < nums.length) {
            if (nums[fast] != value) {
                // 值不相等，替换，只是和数组替换的不一样，这里是先替换后移动
                // 数组原地替换的是先移动，后替换
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
// @lc code=end

