/*
859. Buddy Strings

Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal, otherwise, return false.

Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at s[i] and s[j].

For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 

Example 1:

Input: s = "ab", goal = "ba"
Output: true
Explanation: You can swap s[0] = 'a' and s[1] = 'b' to get "ba", which is equal to goal.
Example 2:

Input: s = "ab", goal = "ab"
Output: false
Explanation: The only letters you can swap are s[0] = 'a' and s[1] = 'b', which results in "ba" != goal.
Example 3:

Input: s = "aa", goal = "aa"
Output: true
Explanation: You can swap s[0] = 'a' and s[1] = 'a' to get "aa", which is equal to goal.
 

Constraints:

1 <= s.length, goal.length <= 2 * 10^4
s and goal consist of lowercase letters.

*/

//Soln TC O(N) SC O(1)
class Solution {
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) return false;
        if (s.equals(goal)) {
            int[] arr = new int[26];
            for (char c: s.toCharArray()) {
                arr[c - 'a']++;
                if (arr[c - 'a'] > 1) return true;
            }
            return false;
        }
        int firstIndex = -1, secondIndex = -1;
        for (int i=0;i<s.length();i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                if (firstIndex == -1) {
                    firstIndex = i;
                } else if (secondIndex == -1) {
                    secondIndex = i;
                } else return false;
            }
        }
        if (secondIndex == -1) return false;
        return s.charAt(firstIndex) == goal.charAt(secondIndex) && s.charAt(secondIndex) == goal.charAt(firstIndex);
    }
}
