/*
1359. Count All Valid Pickup and Delivery Options

Given n orders, each order consist in pickup and delivery services. 

Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i). 

Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:

Input: n = 1
Output: 1
Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.
Example 2:

Input: n = 2
Output: 6
Explanation: All possible orders: 
(P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.
Example 3:

Input: n = 3
Output: 90
 

Constraints:

1 <= n <= 500
*/

//Soln using probability TC O(N) SC O(1)
class Solution {
    public int countOrders(int n) {
        long ans = 1;
        int mod = 1_000_000_007;
        for (int i=1;i<=2*n;i++) {
            ans *= i;
            if (i%2 == 0) {
                ans /= 2;
            }
            ans %= mod;
        }
        return (int)ans;
    }
}

//Soln using memoization TC O(N^2) SC O(N^2)
class Solution {

    private final int MOD = 1_000_000_007;
    private long[][] memo;

    public int countOrders(int n) {
        memo = new long[n+1][n+1];
        return (int)numWays(n, n);
    }

    private long numWays(int unpicked, int undelivered) {
        if (unpicked == 0 && undelivered == 0) return 1;
        if (unpicked < 0 || undelivered < 0 || unpicked > undelivered) return 0;
        if (memo[unpicked][undelivered] != 0) return memo[unpicked][undelivered];
        long ans = unpicked * numWays(unpicked-1, undelivered);
        ans %= MOD;
        ans += (undelivered-unpicked) * numWays(unpicked, undelivered-1);
        ans %= MOD;
        return memo[unpicked][undelivered] = ans;
    }
}
