/*
714. Best Time to Buy and Sell Stock with Transaction Fee

You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6
 

Constraints:

1 <= prices.length <= 5 * 10^4
1 <= prices[i] < 5 * 10^4
0 <= fee < 5 * 10^4
*/

//Soln using DP TC O(N) SC O(N)
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int free[] = new int[n], hold[] = new int[n];
        hold[0] = -prices[0];
        for (int i=1;i<n;i++) {
            hold[i] = Math.max(hold[i-1], free[i-1] - prices[i]);
            free[i] = Math.max(free[i-1], hold[i-1] + prices[i] - fee);
        }
        return free[n-1];
    }
}

//Space optimised soln TC O(N) SC O(1)
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int free = 0, hold = -prices[0];
        for (int i=1;i<prices.length;i++) {
            int tmp = hold;
            hold = Math.max(hold, free - prices[i]);
            free = Math.max(free, tmp + prices[i] - fee);
        }
        return free;
    }
}

