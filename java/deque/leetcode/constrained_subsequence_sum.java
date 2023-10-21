/*
1425. Constrained Subsequence Sum

Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.

A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.

 

Example 1:

Input: nums = [10,2,-10,5,20], k = 2
Output: 37
Explanation: The subsequence is [10, 2, 5, 20].
Example 2:

Input: nums = [-1,-2,-3], k = 1
Output: -1
Explanation: The subsequence must be non-empty, so we choose the largest number.
Example 3:

Input: nums = [10,-2,-10,-5,20], k = 2
Output: 23
Explanation: The subsequence is [10, -2, -5, 20].
 

Constraints:

1 <= k <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4

*/

//Soln using deque TC O(N) SC O(N)
class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque();
        int[] dp = new int[nums.length];
        for (int i=0;i<nums.length;i++) {
            while (!dq.isEmpty() && i - dq.peekFirst() > k) {
                dq.pollFirst();
            }
            dp[i] = (dq.isEmpty() ? 0 : dp[dq.peekFirst()]) + nums[i];
            while (!dq.isEmpty() && dp[dq.peekLast()] < dp[i]) {
                dq.pollLast();
            }
            if (dp[i] > 0) {
                dq.offerLast(i);
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i=0;i<nums.length;i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
