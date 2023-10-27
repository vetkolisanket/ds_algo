/*
5. Longest Palindromic Substring

Given a string s, return the longest 
palindromic
 
substring
 in s.

 

Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
 

Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
*/

//Soln using DP TC O(N^2) SC O(N^2)
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int[] ans = new int[]{0, 0};
        boolean dp[][] = new boolean[n][n];
        for (int i=0;i<n;i++) {
            dp[i][i] = true;
        }
        for (int i=0;i<n-1;i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = true;
                ans[0] = i;
                ans[1] = i+1;
            }
        }
        for (int len=2;len<n;len++) {
            for (int i=0;i<n-len;i++) {
                int j = i+len;
                if (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }
        return s.substring(ans[0], ans[1]+1);
    }
}
