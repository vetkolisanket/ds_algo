/*
1312. Minimum Insertion Steps to Make a String Palindrome

Given a string s. In one step you can insert any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.

 

Example 1:

Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we do not need any insertions.
Example 2:

Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".
Example 3:

Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
*/

//Soln using DP memoization TC O(N^2) SC O(N^2)
class Solution {

    private int[][] memo = new int[501][501];

    public int minInsertions(String s) {
        int n = s.length();
        for (int i=0;i<=500;i++) Arrays.fill(memo[i], -1);
        return find(0, n-1, s);
    }

    private int find(int start, int end, String s) {
        if (start >= end) return 0;
        if (memo[start][end] != -1) return memo[start][end];
        if (s.charAt(start) == s.charAt(end)) {
            return memo[start][end] = find(start+1, end-1, s);
        }
        return memo[start][end] = 1 + Math.min(find(start+1, end, s), find(start, end-1, s));
    }
}
