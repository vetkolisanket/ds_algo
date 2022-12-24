/*
790. Domino and Tromino Tiling

You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.


Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.

In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.

 

Example 1:


Input: n = 3
Output: 5
Explanation: The five different ways are show above.
Example 2:

Input: n = 1
Output: 1
 

Constraints:

1 <= n <= 1000
*/

//Soln using DP and transition functions
class Solution {

    private final int MOD = 1000000007;
    private Map<Integer, Long> fCache = new HashMap();
    private Map<Integer, Long> pCache = new HashMap();

    public int numTilings(int n) {
        return (int) f(n);
    }

    private long f(int n) {
        if (fCache.containsKey(n)) return fCache.get(n);

        if (n == 1) return 1;
        else if (n == 2) return 2;
        long val = (f(n-1) + f(n-2) + 2*p(n-1)) % MOD;
        fCache.put(n, val);
        return val;
    }

    private long p(int n) {
        if (pCache.containsKey(n)) return pCache.get(n);

        if (n == 2) return 1;
        long val = (f(n-2) + p(n-1)) % MOD;
        pCache.put(n, val);
        return val;
    }
}
