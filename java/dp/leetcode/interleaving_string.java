/*
97. Interleaving String

Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where s and t are divided into n and m 
substrings
 respectively, such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.

 

Example 1:


Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Explanation: One way to obtain s3 is:
Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
Since s3 can be obtained by interleaving s1 and s2, we return true.
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
Example 3:

Input: s1 = "", s2 = "", s3 = ""
Output: true
 

Constraints:

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1, s2, and s3 consist of lowercase English letters.
 

Follow up: Could you solve it using only O(s2.length) additional memory space?


*/

//Soln using recursion with memoization TC O(M*N) SC O(M*N)
class Solution {

    Boolean memo[][];

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        memo = new Boolean[s1.length()+1][s2.length()+1];
        return isPossible(s1, s2, s3, 0, 0, 0);
    }

    private boolean isPossible(String s1, String s2, String s3, int i, int j, int k) {
        if (i == s1.length() && j == s2.length() && k == s3.length()) return true;
        if (memo[i][j] != null) return memo[i][j];
        boolean isPossible = false;
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            isPossible |= isPossible(s1, s2, s3, i+1, j, k+1);
        }
        if (isPossible) return memo[i][j] = isPossible;
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            isPossible |= isPossible(s1, s2, s3, i, j+1, k+1);
        }
        return memo[i][j] = isPossible;
    }
}
