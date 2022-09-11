/*
188. Best Time to Buy and Sell Stock IV

You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 

Constraints:

0 <= k <= 100
0 <= prices.length <= 1000
0 <= prices[i] <= 1000
*/

//TC O(nk) SC O(nk)
class Solution {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len/2) return quickSolve(prices);
        
        int[][] dp = new int[k+1][len];
        for (int i=1;i<=k;i++) {
            int localMax = -prices[0];
            for (int j=1;j<len;j++) {
                dp[i][j] = Math.max(dp[i][j-1], prices[j]+localMax);
                localMax = Math.max(localMax, dp[i-1][j-1]-prices[j]);
            }
        }
        return dp[k][len-1];
    }
    
    private int quickSolve(int[] prices) {
        int profit = 0;
        for (int i=1;i<prices.length;i++) {
            if (prices[i] > prices[i-1]) profit += prices[i] - prices[i-1];
        }
        return profit;
    }
}
