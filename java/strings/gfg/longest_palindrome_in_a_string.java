/*
Longest Palindrome in a String

Given a string S, find the longest palindromic substring in S. Substring of string S: S[ i . . . . j ] where 0 ≤ i ≤ j < len(S). Palindrome string: A string which reads the same backwards. More formally, S is palindrome if reverse(S) = S. Incase of conflict, return the substring which occurs first ( with the least starting index).


Example 1:

Input:
S = "aaaabbaa"
Output: aabbaa
Explanation: The longest Palindromic
substring is "aabbaa".
Example 2:

Input: 
S = "abc"
Output: a
Explanation: "a", "b" and "c" are the 
longest palindromes with same length.
The result is the one with the least
starting index.

Your Task:
You don't need to read input or print anything. Your task is to complete the function longestPalin() which takes the string S as input and returns the longest palindromic substring of S.


Expected Time Complexity: O(|S|2).
Expected Auxiliary Space: O(1).


Constraints:
1 ≤ |S| ≤ 10^3
*/

//My soln
class Solution{
    static String longestPalin(String S){
        // code here
        String res = String.valueOf(S.charAt(0));
        int length = S.length();
        for (int i=0;i<length;i++) {
            for (int j=i+1;j<=length;j++) {
                String ss = S.substring(i, j);
                if (ss.length() > res.length() && isPalindrome(ss)) {
                    res = ss;
                }
            }
        }
        return res;
    }
    
    private static boolean isPalindrome(String s) {
        int i = 0, j = s.length()-1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
}
