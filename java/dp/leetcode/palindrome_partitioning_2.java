/*
132. Palindrome Partitioning II

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

 

Example 1:

Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
Example 2:

Input: s = "a"
Output: 0
Example 3:

Input: s = "ab"
Output: 1
 

Constraints:

1 <= s.length <= 2000
s consists of lower-case English letters only.
*/

class Solution {
    public int minCut(String s) {
        int length = s.length();
        int[] cut = new int[length+1];
        for (int i=0;i<=length;i++) cut[i] = i-1;
        for (int i=0;i<length;i++) {
            for (int j=0; i-j >= 0 && i+j < length && s.charAt(i-j) == s.charAt(i+j); j++)
                cut[i+j+1] = Math.min(cut[i+j+1], cut[i-j]+1);
            
            for (int j=1; i-j+1 >= 0 && i+j < length && s.charAt(i-j+1) == s.charAt(i+j); j++)
                cut[i+j+1] = Math.min(cut[i+j+1], cut[i-j+1]+1);
        }
        return cut[length];
    }
}
