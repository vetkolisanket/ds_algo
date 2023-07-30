/*
664. Strange Printer

There is a strange printer with the following two special properties:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
Given a string s, return the minimum number of turns the printer needed to print it.

 

Example 1:

Input: s = "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".
Example 2:

Input: s = "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 

Constraints:

1 <= s.length <= 100
s consists of lowercase English letters.
*/

//Soln using bottom up DP TC O(N^3) SC O(N^2)
class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        int dp[][] = new int[n][n];
        for (int length = 1; length <= n; length++) {
            for (int left = 0;left <= n - length; left++) {
                int right = left + length - 1;
                int j = -1;
                dp[left][right] = n;
                for (int i=left;i<right;i++) {
                    if (s.charAt(i) != s.charAt(right) && j == -1) {
                        j = i;
                    }
                    if (j != -1) {
                        dp[left][right] = Math.min(dp[left][right], 1 + dp[j][i] + dp[i+1][right]);
                    }
                }

                if (j == -1) {
                    dp[left][right] = 0;
                }
            }
        }
        return dp[0][n-1] + 1;
    }
}
