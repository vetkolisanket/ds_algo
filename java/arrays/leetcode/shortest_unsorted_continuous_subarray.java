/*
581. Shortest Unsorted Continuous Subarray

Medium

5893

217

Add to List

Share
Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.

Return the shortest such subarray and output its length.

 

Example 1:

Input: nums = [2,6,4,8,10,9,15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Example 2:

Input: nums = [1,2,3,4]
Output: 0
Example 3:

Input: nums = [1]
Output: 0
 

Constraints:

1 <= nums.length <= 104
-105 <= nums[i] <= 105
 

Follow up: Can you solve it in O(n) time complexity?
*/

//O(n) soln
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int start=-1, end=-1;
        int left=0,right=nums.length-1;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        while (right >= 0) {
            if (nums[left] < max) {
                end = left;
            } else {
                max = nums[left];
            }
            
            if (nums[right] > min) {
                start = right;
            } else {
                min = nums[right];
            }
            
            left++;
            right--;
        }
        return start == -1 ? 0 : end-start+1;
    }
}

//O(nlgn) soln
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] snums = nums.clone();
        Arrays.sort(snums);
        int start = nums.length, end = 0;
        for (int i=0;i<nums.length;i++) {
            if (snums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return end-start >= 0 ? end-start+1 : 0;
    }
}
