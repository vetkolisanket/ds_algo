/*
1420. Build Array Where You Can Find The Maximum Exactly K Comparisons

You are given three integers n, m and k. Consider the following algorithm to find the maximum element of an array of positive integers:


You should build the array arr which has the following properties:

arr has exactly n integers.
1 <= arr[i] <= m where (0 <= i < n).
After applying the mentioned algorithm to arr, the value search_cost is equal to k.
Return the number of ways to build the array arr under the mentioned conditions. As the answer may grow large, the answer must be computed modulo 109 + 7.

 

Example 1:

Input: n = 2, m = 3, k = 1
Output: 6
Explanation: The possible arrays are [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]
Example 2:

Input: n = 5, m = 2, k = 3
Output: 0
Explanation: There are no possible arrays that satisify the mentioned conditions.
Example 3:

Input: n = 9, m = 1, k = 1
Output: 1
Explanation: The only possible array is [1, 1, 1, 1, 1, 1, 1, 1, 1]
 

Constraints:

1 <= n <= 50
1 <= m <= 100
0 <= k <= n

*/

//Soln using top down dp TC O(N*(M^2)*K) SC O(NMK)
class Solution {

    private final int MOD = 1_000_000_007;
    private int memo[][][], m, n;

    public int numOfArrays(int n, int m, int k) {
        this.m = m;
        this.n = n;
        memo = new int[n][m+1][k+1];
        for (int i=0;i<n;i++) {
            for (int j=0;j<=m;j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return dp(0, 0, k);
    }

    private int dp(int i, int maxSoFar, int remain) {
        if (i == n) {
            if (remain == 0) return 1;
            return 0;
        }
        if (remain < 0) return 0;
        if (memo[i][maxSoFar][remain] != -1) return memo[i][maxSoFar][remain];
        int ans = 0;
        for (int j=1;j<=maxSoFar;j++) {
            ans = (ans + dp(i+1, maxSoFar, remain)) % MOD;
        }
        for (int j=maxSoFar+1;j<=m;j++) {
            ans = (ans + dp(i+1, j, remain-1)) % MOD;
        }
        return memo[i][maxSoFar][remain] = ans;
    }
}


