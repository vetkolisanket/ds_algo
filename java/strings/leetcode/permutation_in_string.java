/*
567. Permutation in String

Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

 

Example 1:

Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input: s1 = "ab", s2 = "eidboaoo"
Output: false
 

Constraints:

1 <= s1.length, s2.length <= 104
s1 and s2 consist of lowercase English letters.
*/

//Soln using sliding windown TC O(m+n) SC O(1)
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] arr = new int[26];
        for (char c: s1.toCharArray()) {
            arr[c-'a']++;
        }
        int n = s1.length();
        for (int i=0;i<n;i++) {
            arr[s2.charAt(i) - 'a']--;
        }
        if (isEmpty(arr)) return true;
        for (int i=n;i<s2.length();i++) {
            arr[s2.charAt(i-n) - 'a']++;
            arr[s2.charAt(i) - 'a']--;
            if (isEmpty(arr)) return true;
        }
        return false;
    }

    private boolean isEmpty(int[] arr) {
        for (int i=0;i<arr.length;i++) {
            if (arr[i] != 0) return false;
        }
        return true;
    }
}
