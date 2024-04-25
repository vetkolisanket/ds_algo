/*
2370. Longest Ideal Subsequence

You are given a string s consisting of lowercase letters and an integer k. We call a string t ideal if the following conditions are satisfied:

t is a subsequence of the string s.
The absolute difference in the alphabet order of every two adjacent letters in t is less than or equal to k.
Return the length of the longest ideal string.

A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.

Note that the alphabet order is not cyclic. For example, the absolute difference in the alphabet order of 'a' and 'z' is 25, not 1.

 

Example 1:

Input: s = "acfgbd", k = 2
Output: 4
Explanation: The longest ideal string is "acbd". The length of this string is 4, so 4 is returned.
Note that "acfgbd" is not ideal because 'c' and 'f' have a difference of 3 in alphabet order.
Example 2:

Input: s = "abcd", k = 3
Output: 4
Explanation: The longest ideal string is "abcd". The length of this string is 4, so 4 is returned.
 

Constraints:

1 <= s.length <= 10^5
0 <= k <= 25
s consists of lowercase English letters.
*/

//TC O(n) SC O(1) where n is the length of s
class Solution {
    public int longestIdealString(String s, int k) {
        int arr[] = new int[26], max = 0;
        for (char c: s.toCharArray()) {
            for (int i = Math.max('a', c-k) - 'a'; i <= Math.min('z', c+k) - 'a'; i++) {
                arr[c - 'a'] = Math.max(arr[c - 'a'], arr[i]);
            }
            max = Math.max(max, ++arr[c - 'a']);
        }
        return max;
    }
}

//Soln from another attempt TC O(NK) SC O(1) where N is the length of the string and K is the input value k
class Solution {
    public int longestIdealString(String s, int k) {
        int[] arr = new int[26];
        int ans = 0;
        for (char c: s.toCharArray()) {
            int idx = c - 'a';
            int val = 0;
            for (int i=Math.max(0, idx-k);i<=Math.min(25, idx+k);i++) {
                val = Math.max(val, arr[i]);
            }
            arr[idx] = val+1;
            ans = Math.max(ans, arr[idx]);
        }
        return ans;
    }
}
