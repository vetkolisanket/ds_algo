/*
Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

 

Example 1:

Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.
Example 2:

Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.
 

Constraints:

1 <= haystack.length, needle.length <= 104
haystack and needle consist of only lowercase English characters.

*/

//Soln using two pointers TC O(nm) SC O(1)
class Solution {
    public int strStr(String haystack, String needle) {
        int m = haystack.length(), n = needle.length();
        for (int windowStart = 0; windowStart <= m-n; windowStart++) {
            for (int i=0;i<n;i++) {
                if (haystack.charAt(windowStart+i) != needle.charAt(i)) {
                    break;
                }
                if (i == n-1) return windowStart;
            }
        }
        return -1;
    }
}
