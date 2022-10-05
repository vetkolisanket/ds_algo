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

//Another way dp soln TC O(NK) SC O(NK)
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        //If the no. of transactions are more than or equal to half we can take the overall profit as for k transactions we will require atleast 2k days 
        // (1 day to buy and 1 day to sell)
        if (2*k >= n) {
            int res = 0;
            for (int i=1;i<n;i++) {
                res += Math.max(0, prices[i] - prices[i-1]);
            }
            return res;
        }
        // n -> for no. of days, k+1 to account for 0 to k transactions (it is possible max profit is by doing no transactions, prices keep falling everyday)
        // 2 -> 0 indicates stock is not held, 1 is if stock is held
        int[][][] dp = new int[n][k+1][2];
        for (int i=0;i<n;i++) {
            //Initialising all values to a really low value as on a particular day the values can go below 0 by selling or buying a stock
            for (int j=0;j<=k;j++) {
                dp[i][j][0] = -1000000000;
                dp[i][j][1] = -1000000000;
            }
        }
        //Base case: On 0th day, having done 0 transactions while not holding stock the value will be 0
        dp[0][0][0] = 0;
        //Base case: On 0th day, having bought the stock (you can only buy stock on 0th day, can't sell as you have not bought yet), you'd have done
        //1 transaction, while holding the stock, value will be equal to the price of the stock on 0th day. The value will be negative as you'd have 
        //bought the stock so you'd have to spend the money to buy the stock
        dp[0][1][1] = -prices[0];
        //Having considered the base case we are starting the loop from day 2 (array index 1)
        for (int i=1;i<n;i++) {
            //Loop to cover 0 transactions to upto k transactions
            for (int j=0;j<=k;j++) {
                //On ith day, having done j transactions while not holding a stock, max value would either be the value on (i-1)th day, having done j
                //transactions, while not holding any stock or value on (i-1)th day having done j transactions while holding the stock and selling it
                // on ith day at prices[i] value. (Selling doesn't increase the no. of transactions, only buying does, we are adding prices[i] as 
                //selling the stock is adding to our profits)
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                //Since we are performing a transaction, to compute profit we will have to check profits on one transaction before hence the check of j>0
                if (j > 0) {
                    //On ith day, having done j transactions while holding a stock, max value would either be the value on (i-1)th day, having done j
                    //transactions, while holding the stock or value on (i-1)th day having done (j-1) transactions while not holding the stock and buying
                    //the stock on ith day for prices[i]. (Buying on ith day will increase the no. of transactions, we are subtracting prices[i] as 
                    //buying the stock will decrease the profit earned up until now)
                    dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
                }
            }
        }
        
        int res = 0;
        //Iterate throught the profits on nth day for 0 to upto k transactions to compute the max profit earned. We are only check the not holding part 
        //(index 0 of 3rd dimension of the array) as buying and holding will always be less profit than holding, why? as buying (index 1) you have to 
        //spend money, instead not buying (index 0) you'd have more profit
        for (int i=0;i<=k;i++) {
            res = Math.max(res, dp[n-1][i][0]);
        }
        return res;
    }
}
