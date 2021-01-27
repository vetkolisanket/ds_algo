/*
Given a string s, return the longest palindromic substring in s.

 

Example 1:

Input: s = "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
Example 3:

Input: s = "a"
Output: "a"
Example 4:

Input: s = "ac"
Output: "a"
 

Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters (lower-case and/or upper-case),
*/

/*
class Solution {
    public String longestPalindrome(String s) {
        int length = s.length();
        String sub = s.charAt(0) + "";
        for(int i=0;i<length;i++) {
            for(int j=i+1;j<=length;j++) {
                String a = s.substring(i,j);
                StringBuilder sb = new StringBuilder(a);
                if(sb.reverse().toString().equals(a)) {
                    if (a.length() > sub.length()) {
                        sub = a;
                    }
                }
            }
        }
        return sub;
    }
}
*/

/*
class Solution {
    public String longestPalindrome(String s) {
        int length = s.length();
        String sub = s.charAt(0) + "";
        for(int i=0;i<length;i++) {
            for(int j=length;j>i;j--) {
                String a = s.substring(i,j);
                StringBuilder sb = new StringBuilder(a);
                if(sb.reverse().toString().equals(a)) {
                    if (a.length() > sub.length()) {
                        sub = a;
                        break;
                    }
                }
            }
        }
        return sub;
    }
}
*/

/*
class Solution {
    public String longestPalindrome(String s) {
        int length = s.length();
        int start = 0;
        int end = 1;
        int maxLength = 0;
        for(int i=0;i<length;i++) {
            for(int j=length;j>i;j--) {
                int x = i, y = j-1;
                if (j-i < maxLength) {
                    continue;
                }
                while (x <= y) {
                    if (s.charAt(x) == s.charAt(y)) {
                        x++;
                        y--;
                        if (x > y) {
                            if (j-i>maxLength) {
                                maxLength = j-i;
                                start = i;
                                end = j;
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return s.substring(start,end);
    }
}
*/
