/*
394. Decode String

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.

 

Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
 

Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].

*/

//My soln using stack TC O((maxK^countK)*N) where maxK is the max value of K in the string and countK is the no. of nested K's in the string and N is the maximum length of the encoded string SC O(sum((maxK^countK)*N))
class Solution {
    public String decodeString(String s) {
        int n = s.length();
        Stack<Character> stack = new Stack();
        for (int i=0;i<n;i++) {
            char c = s.charAt(i);
            if (c != ']') {
                stack.push(c);
            } else {
                StringBuilder sb = new StringBuilder();
                while (stack.peek() != '[') {
                    sb.append(stack.pop());
                }
                sb.reverse();
                stack.pop();
                int val = getVal(stack);
                String str = getString(sb.toString(), val);
                for (int j=0;j<str.length();j++) {
                    stack.push(str.charAt(j));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    private int getVal(Stack<Character> stack) {
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty() && stack.peek() >= '0' && stack.peek() <= '9') {
            sb.append(stack.pop());
        }
        return Integer.parseInt(sb.reverse().toString());
    }

    private String getString(String s, int val) {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<val;i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}
