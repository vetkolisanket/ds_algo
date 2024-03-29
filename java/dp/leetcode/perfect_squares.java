/*
279. Perfect Squares

Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

 

Example 1:

Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 

Constraints:

1 <= n <= 10^4
*/

//My soln using DP TC O(n^3/2) SC O(n)
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, n+1);
        double x = Math.sqrt(n);
        dp[0] = 0;
        for (int i=1;i<=x;i++) {
            int sqr = i*i;
            int sum = sqr;
            int cnt = 1;
            while (sum <= n) {
                dp[sum] = Math.min(dp[sum], dp[sum-sqr]+cnt);
                sum++;
            }
        }
        return dp[n];
    }
}

//Another soln via DP TC O(N^(3/2)) SC O(N)
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        int maxSquareIdx = (int) Math.sqrt(n) + 1;
        int[] squareNums = new int[maxSquareIdx];
        for (int i=1;i<maxSquareIdx;i++) {
            squareNums[i] = i*i;
        }

        for (int i=1;i<=n;i++) {
            for (int s=1;s<maxSquareIdx;s++) {
                if (i < squareNums[s])
                    break;
                dp[i] = Math.min(dp[i], dp[i - squareNums[s]] + 1);
            }
        }
        return dp[n];
    }
}
