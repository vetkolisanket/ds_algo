/*
205. Isomorphic Strings

Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

 

Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
 

Constraints:

1 <= s.length <= 5 * 10^4
t.length == s.length
s and t consist of any valid ascii character.

*/

//Soln using a map TC O(N) SC O(1), where N is the length of the strings s and t
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] arr1 = new int[200], arr2 = new int[200];
        int len = s.length();
        for (int i=0;i<len;i++) {
            if (arr1[s.charAt(i)] != arr2[t.charAt(i)]) {
                return false;
            }
            arr1[s.charAt(i)] = i+1;
            arr2[t.charAt(i)] = i+1;
        }
        return true;
    }
}
