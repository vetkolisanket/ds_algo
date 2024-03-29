/*
1544. Make The String Great

Given a string s of lower and upper case English letters.

A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:

0 <= i <= s.length - 2
s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
To make the string good, you can choose two adjacent characters that make the string bad and remove them. You can keep doing this until the string becomes good.

Return the string after making it good. The answer is guaranteed to be unique under the given constraints.

Notice that an empty string is also good.

 

Example 1:

Input: s = "leEeetcode"
Output: "leetcode"
Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".
Example 2:

Input: s = "abBAcC"
Output: ""
Explanation: We have many possible scenarios, and all lead to the same answer. For example:
"abBAcC" --> "aAcC" --> "cC" --> ""
"abBAcC" --> "abBA" --> "aA" --> ""
Example 3:

Input: s = "s"
Output: "s"
 

Constraints:

1 <= s.length <= 100
s contains only lower and upper case English letters.

*/

//Soln using stack TC O(N) SC O(N)
class Solution {
    public String makeGood(String s) {
        Stack<Character> stack = new Stack();
        for (int i=0;i<s.length();++i) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else {
                char c = s.charAt(i);
                if (Math.abs(c - stack.peek()) == 32) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c: stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}

//My soln using recursion TC O(N^2) SC O(N^2)
class Solution {
    public String makeGood(String s) {
        int dist = Math.abs('a' - 'A');
        StringBuilder sb = new StringBuilder();
        int i = 0;
        boolean isBad = false;
        for (;i<s.length()-1;++i) {
            if (Math.abs(s.charAt(i) - s.charAt(i+1)) == dist) {
                i++;
                isBad = true;
            } else {
                sb.append(s.charAt(i));
            }
        }
        if (i == s.length() - 1) {
            sb.append(s.charAt(i));
        }
        if (isBad) return makeGood(sb.toString());
        return sb.toString();
    }
}

//Iterative soln TC O(N^2) SC O(N)
class Solution {
    public String makeGood(String s) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() > 1) {
            boolean isBad = false;
            for (int i=0;i<sb.length()-1;++i) {
                if (Math.abs(sb.charAt(i) - sb.charAt(i+1)) == 32) {
                    isBad = true;
                    sb.deleteCharAt(i);
                    sb.deleteCharAt(i);
                    break;
                }
            }
            if (!isBad) {
                break;
            }
        }
        return sb.toString();
    }
}
