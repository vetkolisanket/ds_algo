/*
647. Palindromic Substrings

Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

 

Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:

Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
*/

//O(n^2) time expand around possible centers O(1) time
class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        int n = s.length();
        for (int i=0;i<n;i++) {
            count += countPalindromes(s, i, i);
            count += countPalindromes(s, i, i+1);
        }
        return count;
    }
    
    private int countPalindromes(String s, int low, int high) {
        int count = 0;
        while (low >= 0 && high < s.length()) {
            if (s.charAt(low) != s.charAt(high)) {
                break;
            }
            low--;
            high++;
            count++;
        }
        return count;
    }
}

//O(n^2) soln using dp O(n^2) space
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int ans = 0;
        for (int i=0;i<n;i++, ans++) {
            dp[i][i] = true;
        }
        for (int i=1;i<n;i++) {
            dp[i-1][i] = (s.charAt(i-1) == s.charAt(i));
            if (dp[i-1][i]) ans++;
        }
        for (int len=3;len<=n;len++) {
            for (int i=0,j=i+len-1;j<n;i++,j++) {
                dp[i][j] = dp[i+1][j-1] && (s.charAt(i) == s.charAt(j));
                if (dp[i][j]) ans++;
            }
        }
        return ans;
    }
}

//Both of the below soln are O(n^3) complexity
//Another soln which doesn't create additional substrings O(n^3) time and O(1) space
class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        int n = s.length();
        for (int i=0;i<n;i++) {
            for (int j=i;j<n;j++) {
                if (isPalindrome(s, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }
    
    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}

//My soln O(n^3) time 
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        int count = 0;
        for (int i=0;i<n;i++) {
            for (int j=i+1;j<=n;j++) {
                if (j-i == 1) count++;
                else if (isPalindrome(s.substring(i, j))) {
                    count++;
                }
            }
        }
        return count;
    }
    
    private boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString().equals(s);
    }
}
