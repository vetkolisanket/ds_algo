/*
647. Palindromic Substrings

Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

 

Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:

Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
*/

//Soln using dp TC O(N^2) SC O(N^2)
class Solution {
    public int countSubstrings(String s) {
        int ans = 0, n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i=0;i<n;i++,ans++) {
            dp[i][i] = true;
        }
        for (int i=0;i<n-1;i++) {
            dp[i][i+1] = s.charAt(i) == s.charAt(i+1);
            ans += dp[i][i+1] ? 1 : 0;
        }
        for (int len=3;len<=n;len++) {
            for (int i=0,j=i+len-1;j<n;i++, j++) {
                dp[i][j] = dp[i+1][j-1] && (s.charAt(i) == s.charAt(j));
                ans += dp[i][j] ? 1 : 0;
            }
        }
        return ans;
    }
}
