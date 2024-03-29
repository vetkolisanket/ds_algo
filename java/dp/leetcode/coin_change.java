/*
322. Coin Change

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

 

Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0
 

Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 2^31 - 1
0 <= amount <= 10^4
*/

//My recursive soln O(mn) time & O(m) space
//m = amount, n = no. of coins
class Solution {
    
    Map<Integer, Integer> map = new HashMap();
    
    public int coinChange(int[] coins, int amount) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        if (map.containsKey(amount)) return map.get(amount);
        int ans = Integer.MAX_VALUE;
        for (int coin : coins) {
            int val = coinChange(coins, amount-coin);
            if (val != -1) {
                ans = Math.min(ans, val);
            }
        }
        ans = ans == Integer.MAX_VALUE ? -1 : ans+1;
        map.put(amount, ans);
        return ans;
    }
}

//Tabulation or iterative approach O(mn) time and O(m) space
class Solution {
    
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i=1;i<=amount;i++) {
            for (int j=0;j<coins.length;j++) {
                if (i-coins[j] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}

//Another interesting tabulation approach
class Solution {
    
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i=coin;i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i-coin]+1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
