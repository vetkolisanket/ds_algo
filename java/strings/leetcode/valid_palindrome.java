/*
125. Valid Palindrome

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

 

Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
 

Constraints:

1 <= s.length <= 2 * 10^5
s consists only of printable ASCII characters.
*/

class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        int size = s.length();
        for (int i = 0; i < size; i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                sb.append(c);
            }
            if (c >= 'A' && c <= 'Z') sb.append(Character.toLowerCase(c));
        }
        return sb.toString().equals(sb.reverse().toString());
    }
}

//2 pointer technique, won't require extra space
class Solution {
    public boolean isPalindrome(String s) {
        int head = 0, tail = s.length() - 1;
        while (head < tail) {
            while (head < tail && !Character.isLetterOrDigit(s.charAt(head))) head++;
            while (head < tail && !Character.isLetterOrDigit(s.charAt(tail))) tail--;
            if (head < tail && Character.toLowerCase(s.charAt(head)) != Character.toLowerCase(s.charAt(tail))) return false;
            else {
                head++;
                tail--;
            }
        }
        return true;
    }
}

//3 line soln, a bit slower
class Solution {
    public boolean isPalindrome(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String reverse = new StringBuilder(actual).reverse().toString();
        return actual.equals(reverse);
    }
}
