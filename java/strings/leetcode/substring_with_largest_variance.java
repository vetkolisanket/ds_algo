/*
2272. Substring With Largest Variance

The variance of a string is defined as the largest difference between the number of occurrences of any 2 characters present in the string. Note the two characters may or may not be the same.

Given a string s consisting of lowercase English letters only, return the largest variance possible among all substrings of s.

A substring is a contiguous sequence of characters within a string.

 

Example 1:

Input: s = "aababbb"
Output: 3
Explanation:
All possible variances along with their respective substrings are listed below:
- Variance 0 for substrings "a", "aa", "ab", "abab", "aababb", "ba", "b", "bb", and "bbb".
- Variance 1 for substrings "aab", "aba", "abb", "aabab", "ababb", "aababbb", and "bab".
- Variance 2 for substrings "aaba", "ababbb", "abbb", and "babb".
- Variance 3 for substring "babbb".
Since the largest possible variance is 3, we return it.
Example 2:

Input: s = "abcde"
Output: 0
Explanation:
No letter occurs more than once in s, so the variance of every substring is 0.
 

Constraints:

1 <= s.length <= 10^4
s consists of lowercase English letters.
*/

//Soln using modified kadane's algorithm TC O(N*K^2) SC O(1) where N is the length of the string ans K is the no. of unique characters in the string
class Solution {
    public int largestVariance(String s) {
        int[] arr = new int[26];
        for (char c: s.toCharArray()) {
            arr[c - 'a']++;
        }
        int ans = 0;
        for (int i=0;i<26;i++) {
            for (int j=0;j<26;j++) {
                if (i == j || arr[i] == 0 || arr[j] == 0) continue;
                char major = (char)('a' + i), minor = (char)('a' + j);
                int majorCount = 0, minorCount = 0, restMinor = arr[j];
                for (char c: s.toCharArray()) {
                    if (c == major) {
                        majorCount++;
                    } else if (c == minor) {
                        minorCount++;
                        restMinor--;
                    }
                    if (minorCount > 0) {
                        ans = Math.max(ans, majorCount - minorCount);
                    }
                    if (minorCount > majorCount && restMinor > 0) {
                        majorCount = 0;
                        minorCount = 0;
                    }
                }
            }
        }
        return ans;
    }
}
