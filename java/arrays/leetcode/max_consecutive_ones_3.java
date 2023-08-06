/*
1004. Max Consecutive Ones III

Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

 

Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
Example 2:

Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 

Constraints:

1 <= nums.length <= 10^5
nums[i] is either 0 or 1.
0 <= k <= nums.length

*/

//Soln using sliding window TC O(N) SC O(1)
class Solution {
    public int longestOnes(int[] nums, int k) {
        int numZeroes = 0, start = 0, end = 0, ans = 0;
        while (end < nums.length) {
            if (nums[end] == 0) numZeroes++;
            while (numZeroes > k) {
                if (nums[start] == 0) numZeroes--;
                start++;
            }
            ans = Math.max(ans, end-start+1);
            end++;
        }
        return ans;
    }
}
