/*
32. Longest Valid Parentheses

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

 

Example 1:

Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".
Example 2:

Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".
Example 3:

Input: s = ""
Output: 0
 

Constraints:

0 <= s.length <= 3 * 104
s[i] is '(', or ')'.
*/

//Soln which doesn't use extra space
class Solution {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0;
        int max = 0;
        int length = s.length();
        for (int i=0;i<length;i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2*left);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i=length-1;i>=0;i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2*left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return max;
    }
}

//Soln from discussions
class Solution {
    public int longestValidParentheses(String s) {
        if (s==null||s.length()==0) return 0;
        int length = s.length();
        boolean[] bools = new boolean[length];
        Stack<Integer> stack = new Stack();
        for(int i=0;i<length;i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            } else {
                if(!stack.isEmpty()) {
                    bools[i] = true;
                    bools[stack.pop()] = true;
                }
            }
        }
        return getMaxLength(bools);
    }
    
    private int getMaxLength(boolean[] bools){
        int max = 0;
        int n = 0;
        int length = bools.length;
        for(int i=0;i<length;i++){
            max = Math.max(max, n = bools[i] ? n+1 : 0);
        }
        return max;
    }
    
}
