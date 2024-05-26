/*
552. Student Attendance Record II

An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:

'A': Absent.
'L': Late.
'P': Present.
Any student is eligible for an attendance award if they meet both of the following criteria:

The student was absent ('A') for strictly fewer than 2 days total.
The student was never late ('L') for 3 or more consecutive days.
Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.

 

Example 1:

Input: n = 2
Output: 8
Explanation: There are 8 records with length 2 that are eligible for an award:
"PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
Example 2:

Input: n = 1
Output: 3
Example 3:

Input: n = 10101
Output: 183236316
 

Constraints:

1 <= n <= 10^5

*/

//Soln using top down dp TC O(N) SC O(N)
class Solution {

    private final int MOD = 1_000_000_007;
    private int[][][] memo;

    public int checkRecord(int n) {
        memo = new int[n+1][2][3];
        for (int i=0;i<=n;i++) {
            for (int j=0;j<2;j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return dp(n, 0, 0);
    }

    private int dp(int n, int absence, int consecutiveLates) {
        if (absence == 2 || consecutiveLates == 3) return 0;
        if (n == 0) return 1;

        if (memo[n][absence][consecutiveLates] != -1) {
            return memo[n][absence][consecutiveLates];
        }

        int count = dp(n-1, absence, 0);
        count = (count + dp(n-1, absence+1, 0)) % MOD;
        count = (count + dp(n-1, absence, consecutiveLates+1)) % MOD;

        return memo[n][absence][consecutiveLates] = count;
    }
}

//Space optimised bottom up soln TC O(N) SC O(1)
class Solution {

    private final int MOD = 1_000_000_007;

    public int checkRecord(int n) {
        int[][] dpCurState = new int[2][3], dpNextState = new int[2][3];
        dpCurState[0][0] = 1;

        for (int i=0;i<n;i++) {
            for (int absence=0;absence<2;absence++) {
                for (int consecutiveLates=0;consecutiveLates<3;
                    consecutiveLates++) {
                    dpNextState[absence][0] = (dpNextState[absence][0] + 
                        dpCurState[absence][consecutiveLates]) % MOD;

                    if (absence < 1) {
                        dpNextState[absence+1][0] = (dpNextState[absence+1][0] + 
                            dpCurState[absence][consecutiveLates]) % MOD;
                    }
                    if (consecutiveLates < 2) {
                        dpNextState[absence][consecutiveLates+1] = 
                            (dpNextState[absence][consecutiveLates+1] + 
                            dpCurState[absence][consecutiveLates]) % MOD;
                    }
                }
            }
            System.arraycopy(dpNextState, 0, dpCurState, 0, dpCurState.length);
            dpNextState = new int[2][3];
        }

        int count = 0;
        for (int absence=0;absence<2;absence++) {
            for (int consecutiveLates=0;consecutiveLates<3;consecutiveLates++) {
                count = (count + dpCurState[absence][consecutiveLates]) % MOD;
            }
        }
        return count;
    }
}
