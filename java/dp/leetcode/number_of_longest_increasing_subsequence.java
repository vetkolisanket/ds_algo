/*
673. Number of Longest Increasing Subsequence

Given an integer array nums, return the number of longest increasing subsequences.

Notice that the sequence has to be strictly increasing.

 

Example 1:

Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:

Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.
 

Constraints:

1 <= nums.length <= 2000
-10^6 <= nums[i] <= 10^6
*/

//Soln using DP TC O(N^2) SC O(N)
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int len = 1;
        int[][] dp = new int[nums.length][2];
        for (int i=0;i<nums.length;i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
            for (int j=i-1;j>=0;j--) {
                if (nums[i] > nums[j]) {
                    if (dp[i][0] <= dp[j][0]) {
                        dp[i][0] = dp[j][0]+1;
                        dp[i][1] = dp[j][1];
                        len = Math.max(len, dp[i][0]);
                    } else if (dp[i][0] == dp[j][0]+1) {
                        dp[i][1] += dp[j][1];
                    } 
                }
            }
        }
        int ans = 0;
        for (int i=0;i<nums.length;i++) {
            if (dp[i][0] == len) ans += dp[i][1];
        }
        return ans;
    }
}
