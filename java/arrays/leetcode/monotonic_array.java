/*
896. Monotonic Array

An array is monotonic if it is either monotone increasing or monotone decreasing.

An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j]. An array nums is monotone decreasing if for all i <= j, nums[i] >= nums[j].

Given an integer array nums, return true if the given array is monotonic, or false otherwise.

 

Example 1:

Input: nums = [1,2,2,3]
Output: true
Example 2:

Input: nums = [6,5,4,4]
Output: true
Example 3:

Input: nums = [1,3,2]
Output: false
 

Constraints:

1 <= nums.length <= 10^5
-10^5 <= nums[i] <= 10^5
*/

//Soln using one pass TC O(N) SC O(1)
class Solution {
    public boolean isMonotonic(int[] nums) {
        if (nums.length == 1) return true;
        boolean isIncreasing = nums[1] >= nums[0], isDecreasing = nums[1] <= nums[0];
        for (int i=2;i<nums.length;i++) {
            if (nums[i] > nums[i-1]) {
                isDecreasing = false;
            } else if (nums[i] < nums[i-1]) {
                isIncreasing = false;
            }
        }
        return isIncreasing || isDecreasing;
    }
}
