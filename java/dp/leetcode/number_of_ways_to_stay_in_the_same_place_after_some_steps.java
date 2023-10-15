/*
1269. Number of Ways to Stay in the Same Place After Some Steps

You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left, 1 position to the right in the array, or stay in the same place (The pointer should not be placed outside the array at any time).

Given two integers steps and arrLen, return the number of ways such that your pointer is still at index 0 after exactly steps steps. Since the answer may be too large, return it modulo 109 + 7.

 

Example 1:

Input: steps = 3, arrLen = 2
Output: 4
Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
Right, Left, Stay
Stay, Right, Left
Right, Stay, Left
Stay, Stay, Stay
Example 2:

Input: steps = 2, arrLen = 4
Output: 2
Explanation: There are 2 differents ways to stay at index 0 after 2 steps
Right, Left
Stay, Stay
Example 3:

Input: steps = 4, arrLen = 2
Output: 8
 

Constraints:

1 <= steps <= 500
1 <= arrLen <= 10^6
*/

//Soln using top down dp TC O(M*min(M,N)) SC O(M*min(M,N)) where M is the no. of steps and N is the length of the array
class Solution {

    private final int MOD = (int) (1e9 + 7);
    private int memo[][];

    public int numWays(int steps, int arrLen) {
        int len = Math.min(steps, arrLen);
        memo = new int[steps+1][len];
        for (int[] arr: memo) {
            Arrays.fill(arr, -1);
        }
        return numWays(steps, len, 0);
    }

    private int numWays(int steps, int len, int idx) {
        if (steps == 0) {
            return idx == 0 ? 1 : 0;
        }
        if (memo[steps][idx] != -1) return memo[steps][idx];
        int numWays = numWays(steps-1, len, idx);
        if (idx > 0) {
            numWays = (numWays + numWays(steps-1, len, idx-1)) % MOD;
        }
        if (idx < len-1) {
            numWays = (numWays + numWays(steps-1, len, idx+1)) % MOD;
        }
        return memo[steps][idx] = numWays;
    }
}
