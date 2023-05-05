/*
1456. Maximum Number of Vowels in a Substring of Given Length

Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.

Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

 

Example 1:

Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.
Example 2:

Input: s = "aeiou", k = 2
Output: 2
Explanation: Any substring of length 2 contains 2 vowels.
Example 3:

Input: s = "leetcode", k = 3
Output: 2
Explanation: "lee", "eet" and "ode" contain 2 vowels.
 

Constraints:

1 <= s.length <= 10^5
s consists of lowercase English letters.
1 <= k <= s.length
*/

//Soln using sliding window TC O(N) SC O(1)
class Solution {
    public int maxVowels(String s, int k) {
        int ans = 0;
        Set<Character> vowels = new HashSet(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        for (int i=0;i<k;i++) {
            if (vowels.contains(s.charAt(i))) ans++;
        }
        int count = ans;
        int i=0, j=k;
        while (j < s.length()) {
            if (vowels.contains(s.charAt(j++))) count++;
            if (vowels.contains(s.charAt(i++))) count--;
            ans = Math.max(ans, count);
        }
        return ans;
    }
}
