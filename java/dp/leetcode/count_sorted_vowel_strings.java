/*
1641. Count Sorted Vowel Strings

Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.

A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.

 

Example 1:

Input: n = 1
Output: 5
Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
Example 2:

Input: n = 2
Output: 15
Explanation: The 15 sorted strings that consist of vowels only are
["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
Example 3:

Input: n = 33
Output: 66045
 

Constraints:

1 <= n <= 50 
*/

//Soln using math
//This is a combination involving repetition problem
//https://en.wikipedia.org/wiki/Combination#Number_of_combinations_with_repetition
class Solution {
    
    public int countVowelStrings(int n) {
        return (n+4)*(n+3)*(n+2)*(n+1)/24;
    }
    
}

//Soln using dp (bottom up tabulation)
class Solution {
    
    public int countVowelStrings(int n) {
        int[][] dp = new int[n+1][6];
        for (int i=1;i<6;i++) {
            dp[1][i] = i;
        }
        for (int i=2;i<=n;i++) {
            dp[i][1] = 1;
        }
        for (int i=2;i<=n;i++) {
            for (int j=2;j<=5;j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[n][5];
    }
    
}

//Soln using dp (top down memoization)
class Solution {
    
    public int countVowelStrings(int n) {
        int[][] memo = new int[n+1][6];
        return countVowelStringsUtil(n, 5, memo);
    }
    
    private int countVowelStringsUtil(int n, int vowels, int[][] memo) {
        if (vowels == 1) return 1;
        if (n == 1) return vowels;
        if (memo[n][vowels] != 0) return memo[n][vowels];
        int res = countVowelStringsUtil(n-1, vowels, memo) + countVowelStringsUtil(n, vowels-1, memo);
        memo[n][vowels] = res;
        return res;
    }
    
}

//My soln
class Solution {
    
    int count = 0;
    
    public int countVowelStrings(int n) {
        char[] arr = new char[]{'a','e','i','o','u'};
        backtrack(arr, n, new StringBuilder(), 0);
        return count;
    }
    
    private void backtrack(char[] arr, int n, StringBuilder sb, int index) {
        if (sb.length() == n) {
            count++;
            return;
        }
        for (int i=index;i<arr.length;i++) {
            sb.append(arr[i]);
            backtrack(arr, n, sb, i);
            sb.setLength(sb.length()-1);
        }
    }
}


