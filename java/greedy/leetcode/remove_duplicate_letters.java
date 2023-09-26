/*
316. Remove Duplicate Letters

Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is 
the smallest in lexicographical order
 among all possible results.

 

Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"
 

Constraints:

1 <= s.length <= 10^4
s consists of lowercase English letters.
 

Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
*/

//Soln using greedy recursion TC O(N) SC O(N)

class Solution {
    public String removeDuplicateLetters(String s) {
        int[] freq = new int[26];

        for (char c: s.toCharArray()) {
            freq[c - 'a']++;
        }
        int pos = 0;
        for (int i=0;i<s.length();i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            if (--freq[s.charAt(i) - 'a'] == 0) break;
        }
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos+1).replaceAll("" + s.charAt(pos), ""))
    }
}

//Soln using stack TC O(N) SC O(1)
class Solution {
    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> lastOccurence = new HashMap();
        Set<Character> seen = new HashSet();
        Stack<Character> stack = new Stack();
        for (int i=0;i<s.length();i++) {
            lastOccurence.put(s.charAt(i), i);
        }
        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (seen.contains(c)) continue;
            while (!stack.isEmpty() && c < stack.peek() && lastOccurence.get(stack.peek()) > i) {
                seen.remove(stack.pop());
            }
            seen.add(c);
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c: stack) sb.append(c);
        return sb.toString();
    }
}
