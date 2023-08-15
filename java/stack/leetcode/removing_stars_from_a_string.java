/*
2390. Removing Stars From a String

You are given a string s, which contains stars *.

In one operation, you can:

Choose a star in s.
Remove the closest non-star character to its left, as well as remove the star itself.
Return the string after all stars have been removed.

Note:

The input will be generated such that the operation is always possible.
It can be shown that the resulting string will always be unique.
 

Example 1:

Input: s = "leet**cod*e"
Output: "lecoe"
Explanation: Performing the removals from left to right:
- The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
- The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
- The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
There are no more stars, so we return "lecoe".
Example 2:

Input: s = "erase*****"
Output: ""
Explanation: The entire string is removed, so we return an empty string.
 

Constraints:

1 <= s.length <= 10^5
s consists of lowercase English letters and stars *.
The operation above can be performed on s.
*/

//Better soln without the need of stack TC O(N) SC O(N)
class Solution {
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c: s.toCharArray()) {
            if (c == '*') sb.deleteCharAt(sb.length()-1);
            else sb.append(c);
        }
        return sb.toString();
    }
}

//Soln using stack TC O(N) SC O(N)
class Solution {
    public String removeStars(String s) {
        Stack<Character> stack = new Stack();
        for (char c: s.toCharArray()) {
            if (c == '*') stack.pop();
            else stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}

//Soln using character array TC O(N) SC O(N)
class Solution {
    public String removeStars(String s) {
        int n = s.length();
        char[] arr = new char[n];
        int j = 0;
        for (char c: s.toCharArray()) {
            if (c == '*') j--;
            else arr[j++] = c;
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<j;i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}
