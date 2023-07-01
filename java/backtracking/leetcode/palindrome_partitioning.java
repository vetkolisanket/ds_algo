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
        List<List<String>> result = new ArrayList();
        boolean[][] dp = new boolean[length][length];
        
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j+1][i-1])) {
                    dp[j][i] = true;
                }
            }
        }
        
        partition(s, result, dp, new ArrayList(), 0);
        return result;
    }
    
    private void partition(String s, List<List<String>> res, boolean[][] dp, List<String> tempList, int start) {
        int length = s.length();
        if (start == length) {
            res.add(new ArrayList(tempList));
        } else {
            for (int i = start; i < length; i++) {
                if (dp[start][i]) {
                    tempList.add(s.substring(start, i+1));
                    partition(s, res, dp, tempList, i+1);
                    tempList.remove(tempList.size()-1);
                }
            }
        }
    }
}
