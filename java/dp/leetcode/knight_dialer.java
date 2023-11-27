/*
935. Knight Dialer

The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of chess knight are shown in this diagaram:

A chess knight can move as indicated in the chess diagram below:


We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).


Given an integer n, return how many distinct phone numbers of length n we can dial.

You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.

As the answer may be very large, return the answer modulo 109 + 7.

 

Example 1:

Input: n = 1
Output: 10
Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.
Example 2:

Input: n = 2
Output: 20
Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
Example 3:

Input: n = 3131
Output: 136006598
Explanation: Please take care of the mod.
 

Constraints:

1 <= n <= 5000
*/

//Soln using DP TC O(N) SC O(N)
class Solution {

    private final int mod = (int) (1e9 + 7);

    public int knightDialer(int n) {
        Map<Integer, List<Integer>> map = new HashMap();
        map.put(0, List.of(4, 6));
        map.put(1, List.of(6, 8));
        map.put(2, List.of(7, 9));
        map.put(3, List.of(4, 8));
        map.put(4, List.of(0, 3, 9));
        map.put(5, new ArrayList());
        map.put(6, List.of(0, 1, 7));
        map.put(7, List.of(2, 6));
        map.put(8, List.of(1, 3));
        map.put(9, List.of(2, 4));
        int ans = 0;
        int[][] memo = new int[10][n+1];
        for (int[] arr: memo) {
            Arrays.fill(arr, -1);
        }
        for (int i=0;i<10;i++) {
            ans = (ans + helper(i, n-1, map, memo)) % mod;
        }
        return ans;
    }

    private int helper(int val, int count, Map<Integer, List<Integer>> map, int[][] memo) {
        if (count == 0) return 1;
        if (val == 5) return 0;
        if (memo[val][count] != -1) return memo[val][count];
        int ans = 0;
        for (int nextVal: map.get(val)) {
            ans = (ans + helper(nextVal, count-1, map, memo)) % mod;
        }
        return memo[val][count] = ans;
    }
}
