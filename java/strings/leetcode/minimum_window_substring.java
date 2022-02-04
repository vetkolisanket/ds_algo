/*
76. Minimum Window Substring

Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.

 

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
 

Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.
 

Follow up: Could you find an algorithm that runs in O(m + n) time?
*/

//Another O(m+n) soln which is faster
class Solution {
    public String minWindow(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        if (sLength < tLength) return "";
        Map<Character, Integer> map = new HashMap();
        for (char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0)+1);
        int left = 0;
        int minLeft = 0;
        int minLength = sLength + 1;
        int count = 0;
        for (int right=0;right < sLength; right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)-1);
                if (map.get(c) >= 0) count++;
            }
            while (count == tLength) {
                if (right-left+1 < minLength) {
                    minLeft = left;
                    minLength = right-left+1;
                }
                char l = s.charAt(left);
                if (map.containsKey(l)) {
                    map.put(l, map.get(l)+1);
                    if (map.get(l) > 0) count--;
                }
                left++;
            }
        }
        return minLength == sLength+1 ? "" : s.substring(minLeft, minLeft + minLength);
    }
}

//O(m+n) soln
class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) return "";
        int left = 0;
        int right = s.length()-1;
        int minLen = s.length();
        int i = 0, j = 0;
        boolean flag = false;
        int count = t.length();
        Map<Character, Integer> map = new HashMap();
        for (char c: t.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
        while (j < s.length()) {
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)-1);
                if (map.get(c) >= 0) count--;
            }
            
            while (count == 0 && i <= j) {
                flag = true;
                int len = j-i+1;
                if (len < minLen) {
                    left = i;
                    right = j;
                    minLen = len;
                }
                c = s.charAt(i);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c)+1);
                    if (map.get(c) > 0) count++;
                }
                i++;
            }
            j++;
        }
        return flag ? s.substring(left, right+1) : "";
    }
}
