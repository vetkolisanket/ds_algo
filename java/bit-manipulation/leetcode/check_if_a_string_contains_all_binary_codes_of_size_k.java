/*
1461. Check If a String Contains All Binary Codes of Size K

Given a binary string s and an integer k, return true if every binary code of length k is a substring of s. Otherwise, return false.

 

Example 1:

Input: s = "00110110", k = 2
Output: true
Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indices 0, 1, 3 and 2 respectively.
Example 2:

Input: s = "0110", k = 1
Output: true
Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring. 
Example 3:

Input: s = "0110", k = 2
Output: false
Explanation: The binary code "00" is of length 2 and does not exist in the array.
 

Constraints:

1 <= s.length <= 5 * 10^5
s[i] is either '0' or '1'.
1 <= k <= 20
*/

//O(N) time and O(2^k) space complexity
class Solution {
    public boolean hasAllCodes(String s, int k) {
        int need = 1 << k;
        boolean[] got = new boolean[need];
        int allOne = need-1;
        int hashVal = 0;
        
        for (int i=0;i<s.length();i++) {
            hashVal = ((hashVal << 1) & allOne) | (s.charAt(i) - '0');
            if (i >= k-1 && !got[hashVal]) {
                got[hashVal] = true;
                need--;
                if (need == 0) {
                    return true;
                }
            }           
        }
        return false;
    }
}

//O(NK) time and space complexity
class Solution {
    public boolean hasAllCodes(String s, int k) {
        int need = (1 << k);
        int len = s.length();
        Set<String> set = new HashSet();
        for (int i=k;i<=len;i++) {
            if (set.add(s.substring(i-k,i))) {
                need--;
                if (need == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
