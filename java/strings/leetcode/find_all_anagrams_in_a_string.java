/*
438. Find All Anagrams in a String

Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 

Constraints:

1 <= s.length, p.length <= 3 * 104
s and p consist of lowercase English letters.

*/

//Soln using sliding window TC O(m+n) SC O(1) where m and n are length of string s and p
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList();
        if (s.length() < p.length()) return list;
        int[] arr = new int[26];
        for (char c: p.toCharArray()) {
            arr[c - 'a']++;
        }
        int n = p.length();
        for (int i=0;i<n;i++) {
            arr[s.charAt(i) - 'a']--;
        }
        if (isEmpty(arr)) list.add(0);
        for (int i=n;i<s.length();i++) {
            arr[s.charAt(i-n) - 'a']++;
            arr[s.charAt(i) - 'a']--;
            if (isEmpty(arr)) list.add(i-n+1);
        }
        return list;
    }

    private boolean isEmpty(int[] arr) {
        for (int i=0;i<26;i++) {
            if (arr[i] != 0) return false;
        }
        return true;
    }
}
