/*
58. Length of Last Word

Given a string s consisting of some words separated by some number of spaces, return the length of the last word in the string.

A word is a maximal substring consisting of non-space characters only.

 

Example 1:

Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.
Example 2:

Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4.
Example 3:

Input: s = "luffy is still joyboy"
Output: 6
Explanation: The last word is "joyboy" with length 6.
 

Constraints:

1 <= s.length <= 10^4
s consists of only English letters and spaces ' '.
There will be at least one word in s.
*/

//My soln TC O(N) SC O(1)
class Solution {
    public int lengthOfLastWord(String s) {
        int len = s.length();
        int i = len-1;
        while (i >=0 && s.charAt(i) == ' ') {
            i--;
        }
        int end = i;
        while (i >= 0 && s.charAt(i) != ' ') {
            i--;
        }
        return end - i;
    }
}

class Solution {
    public int lengthOfLastWord(String s) {
        int len = s.length();
        int res = 0;
        int i = len-1;
        while (s.charAt(i) == ' ') i--;
        while (i >= 0 && s.charAt(i) != ' ') {
            res++;
            i--;
        }
        return res;
    }
}
