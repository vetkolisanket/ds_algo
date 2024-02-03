/*
1043. Partition Array for Maximum Sum

Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.

 

Example 1:

Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]
Example 2:

Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83
Example 3:

Input: arr = [1], k = 1
Output: 1
 

Constraints:

1 <= arr.length <= 500
0 <= arr[i] <= 10^9
1 <= k <= arr.length
*/

//Soln using DP TC O(NK) SC O(N)
class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        return maxSum(arr, k, dp, 0);
    }

    private int maxSum(int[] arr, int k, int[] dp, int start) {
        int n = arr.length;
        if (start == n) return 0;
        if (dp[start] != -1) return dp[start];

        int curMax = 0, ans = 0;
        int end = Math.min(n, start + k);
        for (int i=start;i<end;i++) {
            curMax = Math.max(curMax, arr[i]);
            ans = Math.max(ans, curMax * (i - start + 1) + maxSum(arr, k, dp, i+1));
        }
        return dp[start] = ans;
    }
}
