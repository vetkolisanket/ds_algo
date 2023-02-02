/*
1071. Greatest Common Divisor of Strings

For two strings s and t, we say "t divides s" if and only if s = t + ... + t (i.e., t is concatenated with itself one or more times).

Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.

 

Example 1:

Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
Example 2:

Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
Example 3:

Input: str1 = "LEET", str2 = "CODE"
Output: ""
 

Constraints:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of English uppercase letters.
*/

//Soln TC O(min(m,n)(m+n)) SC O(min(m,n))
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        int minLen = Math.min(str1.length(), str2.length());
        for (int i=minLen;i>0;i--) {
            if (minLen%i == 0) {
                if (isPossible(str1, str2, i)) return str1.substring(0, i);
            }
        }
        return "";
    }

    private boolean isPossible(String str1, String str2, int val) {
        int mul1 = str1.length()/val, mul2 = str2.length()/val;
        StringBuilder sb = new StringBuilder();
        String ss = str1.substring(0, val);
        for (int i=0;i<Math.max(mul1, mul2);i++) {
            sb.append(ss);
            if (i == mul1-1 && !sb.toString().equals(str1)) return false;
            if (i == mul2-1 && !sb.toString().equals(str2)) return false;
        }
        return true;
    }
}
