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

//A simpler soln TC O(M+N) SC O(M+N)
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1+str2).equals(str2+str1)) return "";
        int gcd = gcd(str1.length(), str2.length());
        return str1.substring(0, gcd);
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a%b);
    }
}

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

//Soln using gcd TC O(M+N) SC O(M+N) where M and N are the lengths of strings str1 and str2 respectively
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        int gcd = gcd(len1, len2);
        if (concatenates(str1, len1, str2, len2, gcd)) return str1.substring(0, gcd);
        return "";
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a%b);
    }

    private boolean concatenates(String str1, int len1, String str2, int len2, int len) {
        int val1 = len1/len, val2 = len2/len;
        StringBuilder sb = new StringBuilder();
        String s = str1.substring(0, len);
        for (int i=0;i<val1;i++) {
            sb.append(s);
        }
        if (!str1.equals(sb.toString())) return false;
        sb.setLength(0);
        for (int i=0;i<val2;i++) {
            sb.append(s);
        }
        return str2.equals(sb.toString());
    }
}

