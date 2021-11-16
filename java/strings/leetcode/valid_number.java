/*
65. Valid Number

A valid number can be split up into these components (in order):

A decimal number or an integer.
(Optional) An 'e' or 'E', followed by an integer.
A decimal number can be split up into these components (in order):

(Optional) A sign character (either '+' or '-').
One of the following formats:
One or more digits, followed by a dot '.'.
One or more digits, followed by a dot '.', followed by one or more digits.
A dot '.', followed by one or more digits.
An integer can be split up into these components (in order):

(Optional) A sign character (either '+' or '-').
One or more digits.
For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"], while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].

Given a string s, return true if s is a valid number.

 

Example 1:

Input: s = "0"
Output: true
Example 2:

Input: s = "e"
Output: false
Example 3:

Input: s = "."
Output: false
Example 4:

Input: s = ".1"
Output: true
 

Constraints:

1 <= s.length <= 20
s consists of only English letters (both uppercase and lowercase), digits (0-9), plus '+', minus '-', or dot '.'.
*/

//More readable soln
class Solution {
    public boolean isNumber(String s) {
        int length = s.length();
        boolean isESeen = false;
        boolean isDotSeen = false;
        boolean isNumberSeen = false;
        for (int i=0; i<length; i++) {
            char c = s.charAt(i);
            if ('0' <= c && c <= '9') isNumberSeen = true;
            else if (c == '.') {
                if (isESeen || isDotSeen) return false;
                isDotSeen = true;
            } else if (Character.toLowerCase(c) == 'e') {
                if (isESeen || !isNumberSeen) return false;
                isNumberSeen = false;
                isESeen = true;
            } else if (c == '+' || c == '-') {
                if (i != 0 && Character.toLowerCase(s.charAt(i-1)) != 'e') return false;
            } else return false;
        }
        return isNumberSeen;
    }
}

//My soln
class Solution {
    public boolean isNumber(String s) {
        int length = s.length();
        boolean isEFound = false;
        boolean isDotFound = false;
        boolean hasHadDigit = false;
        for (int i=0; i<length; i++) {
            char c = s.charAt(i);
            if (isDigit(c)) hasHadDigit = true;
            else if (c == '+' || c == '-') {
                if ((i == 0 || s.charAt(i-1) == 'e' || s.charAt(i-1) == 'E') && i < length-1) {
                    if (isDigit(s.charAt(i+1))) continue;
                    else if (i < length-2 && s.charAt(i+1) == '.' && isDigit(s.charAt(i+2))) continue;
                    else return false;
                }
                else return false;
            } else if (c == 'e' || c == 'E') {
                if (isEFound || i == 0 || i == length-1 || !hasHadDigit) return false;
                else isEFound = true;
            } else if (c == '.') {
                if (isEFound || isDotFound || length == 1) return false;
                else isDotFound = true;
            } else return false;
        }
        return true;
    }
    
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
