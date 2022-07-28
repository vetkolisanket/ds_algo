/*
242. Valid Anagram

Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
 

Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.
 

Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?

If input contains Unicode characters we can use hashmap for frequency counting as the no. of unicode characters possible is > 1 million and allocating such a huge array is not a good idea as n (length of string) < 5*10^4
*/

//TC O(n) SC O(1)
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] arr = new int[26];
        for (char c: s.toCharArray()) {
            arr[c-'a']++;
        }
        for (char c: t.toCharArray()) {
            if (arr[c - 'a']-- <= 0) return false;
        }
        for (int i=0;i<26;i++) {
            if (arr[i] != 0) return false;
        }
        return true;
    }
}
