/*
474. Ones and Zeroes

You are given an array of binary strings strs and two integers m and n.

Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.

A set x is a subset of a set y if all elements of x are also elements of y.

 

Example 1:

Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
Output: 4
Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
{"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
Example 2:

Input: strs = ["10","0","1"], m = 1, n = 1
Output: 2
Explanation: The largest subset is {"0", "1"}, so the answer is 2.
 

Constraints:

1 <= strs.length <= 600
1 <= strs[i].length <= 100
strs[i] consists only of digits '0' and '1'.
1 <= m, n <= 100
*/

//Using tabulation and dp
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for (String s: strs) {
            int[] count = countZeroesAndOnes(s);
            for (int zeroes = m; zeroes >= count[0]; zeroes--) {
                for (int ones = n; ones >= count[1]; ones--) {
                    dp[zeroes][ones] = Math.max(1+dp[zeroes-count[0]][ones-count[1]], dp[zeroes][ones]);
                }
            }
        }
        return dp[m][n];
    }
    
    private int[] countZeroesAndOnes(String s) {
        int[] arr = new int[2];
        for (char c: s.toCharArray()) {
            arr[c-'0']++;
        }
        return arr;
    }
}

//Using recursion and memoization
class Solution {
    
    Integer[][][] memo;
    
    public int findMaxForm(String[] strs, int m, int n) {
        memo = new Integer[strs.length][m+1][n+1];
        return findMaxForm(strs, 0, m, n);
    }
    
    private int findMaxForm(String[] strs, int i, int zeroes, int ones) {
        if (i == strs.length) return 0;
        if (memo[i][zeroes][ones] != null) return memo[i][zeroes][ones];
        int[] count = countZeroesAndOnes(strs[i]);
        int taken = -1;
        if (zeroes >= count[0] && ones >= count[1]) {
            taken = findMaxForm(strs, i+1, zeroes-count[0], ones-count[1])+1;
        }
        int notTaken = findMaxForm(strs, i+1, zeroes, ones);
        memo[i][zeroes][ones] = Math.max(taken, notTaken);
        return memo[i][zeroes][ones];
    }
    
    private int[] countZeroesAndOnes(String s) {
        int[] arr = new int[2];
        for (char c: s.toCharArray()) {
            arr[c-'0']++;
        }
        return arr;
    }
}
