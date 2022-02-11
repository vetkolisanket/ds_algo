/*
844. Backspace String Compare

Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.

 

Example 1:

Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".
Example 2:

Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".
Example 3:

Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".
 

Constraints:

1 <= s.length, t.length <= 200
s and t only contain lowercase letters and '#' characters.
 

Follow up: Can you solve it in O(n) time and O(1) space?
*/

//O(n+m) time and O(1) space soln
class Solution {
    public boolean backspaceCompare(String s, String t) {
        int i = s.length()-1;
        int j = t.length()-1;
        int sCount = 0;
        int tCount = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0 && (s.charAt(i) == '#' || sCount > 0)) {
                if (s.charAt(i) == '#') sCount++;
                else sCount--;
                i--;
            }
            char left = i < 0 ? '@' : s.charAt(i);
            while (j >= 0 && (t.charAt(j) == '#' || tCount > 0)) {
                if (t.charAt(j) == '#') tCount++;
                else tCount--;
                j--;
            }
            char right = j < 0 ? '@' : t.charAt(j);
            if (left != right) return false;
            i--;
            j--;
        }
        return true;
    }
}

//My O(n+m) time and space soln
class Solution {
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> sStack = new Stack();
        Stack<Character> tStack = new Stack();
        int sLen = s.length();
        int tLen = t.length();
        for (int i=0;i<sLen;i++) {
            char c = s.charAt(i);
            if (c == '#') {
                if (!sStack.isEmpty()) sStack.pop();
            } else sStack.push(c);
        }
        for (int i=0;i<tLen;i++) {
            char c = t.charAt(i);
            if (c == '#') {
                if (!tStack.isEmpty()) tStack.pop();
            } else tStack.push(c);
        }
        while (!sStack.isEmpty()) {
            if (tStack.isEmpty()) return false;
            if (sStack.pop() != tStack.pop()) return false;
        }
        return tStack.isEmpty();
    }
}
