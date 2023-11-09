/*
1759. Count Number of Homogenous Substrings

Given a string s, return the number of homogenous substrings of s. Since the answer may be too large, return it modulo 109 + 7.

A string is homogenous if all the characters of the string are the same.

A substring is a contiguous sequence of characters within a string.

 

Example 1:

Input: s = "abbcccaa"
Output: 13
Explanation: The homogenous substrings are listed as below:
"a"   appears 3 times.
"aa"  appears 1 time.
"b"   appears 2 times.
"bb"  appears 1 time.
"c"   appears 3 times.
"cc"  appears 2 times.
"ccc" appears 1 time.
3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.
Example 2:

Input: s = "xy"
Output: 2
Explanation: The homogenous substrings are "x" and "y".
Example 3:

Input: s = "zzzzz"
Output: 15
 

Constraints:

1 <= s.length <= 10^5
s consists of lowercase letters.
*/

//My soln TC O(N) SC O(1)
class Solution {
    public int countHomogenous(String s) {
        long ans = 0;
        int start = 0, cur = 0;
        while (cur < s.length()) {
            while (cur < s.length() && s.charAt(start) == s.charAt(cur)) {
                cur++;
            }
            long diff = cur - start;
            ans = (ans + (diff * (diff+1))/2);
            start = cur;
        }
        return (int)(ans % (1e9 + 7));
    }
}

//Another approach with same complexity
class Solution {
    public int countHomogenous(String s) {
        int ans = 0;
        int currStreak = 0;
        int MOD = (int) 1e9 + 7;
        
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || s.charAt(i) == s.charAt(i - 1)) {
                currStreak++;
            } else {
                currStreak = 1;
            }
            
            ans = (ans + currStreak) % MOD;
        }
        
        return ans;
    }
}
