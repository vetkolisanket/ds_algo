/*
1915. Number of Wonderful Substrings

A wonderful string is a string where at most one letter appears an odd number of times.

For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return the number of wonderful non-empty substrings in word. If the same substring appears multiple times in word, then count each occurrence separately.

A substring is a contiguous sequence of characters in a string.

 

Example 1:

Input: word = "aba"
Output: 4
Explanation: The four wonderful substrings are underlined below:
- "aba" -> "a"
- "aba" -> "b"
- "aba" -> "a"
- "aba" -> "aba"
Example 2:

Input: word = "aabb"
Output: 9
Explanation: The nine wonderful substrings are underlined below:
- "aabb" -> "a"
- "aabb" -> "aa"
- "aabb" -> "aab"
- "aabb" -> "aabb"
- "aabb" -> "a"
- "aabb" -> "abb"
- "aabb" -> "b"
- "aabb" -> "bb"
- "aabb" -> "b"
Example 3:

Input: word = "he"
Output: 2
Explanation: The two wonderful substrings are underlined below:
- "he" -> "h"
- "he" -> "e"
 

Constraints:

1 <= word.length <= 10^5
word consists of lowercase English letters from 'a' to 'j'.

*/

//Soln using bit manipulation counting parity TC O(NA) SC O(N) where N is the length of the word and A is the no. of unique characters possible, which in this case is 10
class Solution {
    public long wonderfulSubstrings(String word) {
        Map<Integer, Integer> freqMap = new HashMap();
        freqMap.put(0, 1);
        long ans = 0;
        int mask = 0;
        for (char c: word.toCharArray()) {
            int idx = c - 'a';
            mask ^= (1 << idx);
            ans += freqMap.getOrDefault(mask, 0);
            freqMap.put(mask, freqMap.getOrDefault(mask, 0) + 1);

            for (int i=0;i<10;i++) {
                ans += freqMap.getOrDefault(mask ^ (1 << i), 0);
            }
        }
        return ans;
    }
}
