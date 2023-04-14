/*
516. Longest Palindromic Subsequence

Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

 

Example 1:

Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".
Example 2:

Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".
 

Constraints:

1 <= s.length <= 1000
s consists only of lowercase English letters.
*/

//Soln using recursive dp (memoization) TC O(N^2) SC O(N^2)
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        return lps(s, 0, n-1, memo);
    }

    private int lps(String s, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) return memo[i][j];
        if (i > j) return 0;
        if (i == j) return 1;
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = lps(s, i+1, j-1, memo) + 2;
        } else {
            memo[i][j] = Math.max(lps(s, i+1, j, memo), lps(s, i, j-1, memo));
        }
        return memo[i][j];
    }
}
