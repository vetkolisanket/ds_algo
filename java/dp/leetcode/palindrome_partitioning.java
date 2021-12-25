/*
131. Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

A palindrome string is a string that reads the same backward as forward.

 

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]
 

Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
*/

class Solution {
    public List<List<String>> partition(String s) {
        int length = s.length();
        List<List<String>> ans = new ArrayList();
        boolean[][] dp = new boolean[length][length];
        
        for (int i=0;i<length;i++) {
            for (int j=0;j<=i;j++) {
                if ((s.charAt(i) == s.charAt(j)) && (i - j <= 2 || dp[j+1][i-1])) {
                    dp[j][i] = true;
                }
            }
        }
        
        partition(s, ans, new ArrayList(), dp, 0);
        return ans;
    }
    
    private void partition(String s, List<List<String>> ans, List<String> temp, boolean[][] dp, int i) {
        int size = s.length();
        if (i == size) {
            ans.add(new ArrayList(temp));
        } else {
            for (int j=i;j<size;j++) {
                if (dp[i][j]) {
                    temp.add(s.substring(i, j+1));
                    partition(s, ans, temp, dp, j+1);
                    temp.remove(temp.size()-1);
                }
            }
        }
    }
}
