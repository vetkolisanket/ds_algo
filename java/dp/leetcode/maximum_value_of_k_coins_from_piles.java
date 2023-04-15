/*
2218. Maximum Value of K Coins From Piles

There are n piles of coins on a table. Each pile consists of a positive number of coins of assorted denominations.

In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet.

Given a list piles, where piles[i] is a list of integers denoting the composition of the ith pile from top to bottom, and a positive integer k, return the maximum total value of coins you can have in your wallet if you choose exactly k coins optimally.

 

Example 1:


Input: piles = [[1,100,3],[7,8,9]], k = 2
Output: 101
Explanation:
The above diagram shows the different ways we can choose k coins.
The maximum total we can obtain is 101.
Example 2:

Input: piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
Output: 706
Explanation:
The maximum total can be obtained if we choose all coins from the last pile.
 

Constraints:

n == piles.length
1 <= n <= 1000
1 <= piles[i][j] <= 10^5
1 <= k <= sum(piles[i].length) <= 2000
*/

//Soln using DP TC O(N*S) SC O(N*K) where N is the no. of piles, S is the total no. of coins in all piles and K is the no. of coins required
class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();
        int[][] dp = new int[n+1][k+1];
        for (int i=1;i<=n;i++) {
            for (int coin=0;coin<=k;coin++) {
                int currentSum = 0;
                for (int currentCoin=0;currentCoin<=Math.min(piles.get(i-1).size(), coin);currentCoin++) {
                    if (currentCoin > 0) {
                        currentSum += piles.get(i-1).get(currentCoin-1);
                    }
                    dp[i][coin] = Math.max(dp[i][coin], dp[i-1][coin-currentCoin] + currentSum);
                }
            }
        }
        return dp[n][k];
    }
}
