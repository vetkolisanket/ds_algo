/*
518. Coin Change II

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.

 

Example 1:

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10]
Output: 1
 

Constraints:

1 <= coins.length <= 300
1 <= coins[i] <= 5000
All the values of coins are unique.
0 <= amount <= 5000
*/

//Soln using top down DP TC O(N*amount) SC O(N*amount)
class Solution {
    public int change(int amount, int[] coins) {
        int[][] memo = new int[coins.length][amount+1];
        for (int[] arr: memo) Arrays.fill(arr, -1);
        return numWays(amount, coins, memo, 0);
    }

    private int numWays(int amount, int[] coins, int[][] memo, int idx) {
        if (amount == 0) return 1;
        if (idx == coins.length) return 0;
        if (memo[idx][amount] != -1) return memo[idx][amount];

        if (coins[idx] > amount) {
            return memo[idx][amount] = numWays(amount, coins, memo, idx+1);
        }
        memo[idx][amount] = numWays(amount, coins, memo, idx+1) + numWays(amount - coins[idx], coins, memo, idx);
        return memo[idx][amount];
    }
}
