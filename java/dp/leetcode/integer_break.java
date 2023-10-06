/*
343. Integer Break

Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.

Return the maximum product you can get.

 

Example 1:

Input: n = 2
Output: 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.
Example 2:

Input: n = 10
Output: 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 

Constraints:

2 <= n <= 58

*/

//Soln using top down dp TC O(N^2) SC O(N)
class Solution {

    private int[] memo;

    public int integerBreak(int n) {
        if (n <= 3) return n-1;

        memo = new int[n+1];
        return dp(n);
    }

    private int dp(int num) {
        if (num <= 3) return num;

        if (memo[num] != 0) return memo[num];

        int ans = num;
        for (int i=2;i<num;i++) {
            ans = Math.max(ans, i*dp(num-i));
        }
        return memo[num] = ans;
    }
}

//Soln using maths TC O(N) SC O(1)
class Solution {
    public int integerBreak(int n) {
        if (n <= 3) return n-1;

        int ans = 1;
        while (n > 4) {
            ans *= 3;
            n -= 3;
        }
        return ans * n;
    }
}

//Even faster soln TC O(logN) SC O(1)
class Solution {
    public int integerBreak(int n) {
        if (n <= 3) return n-1;
        if (n%3 == 0) return (int)Math.pow(3, n/3);
        if (n%3 == 1) return (int)(Math.pow(3, (n/3)-1)*4);
        return (int)(Math.pow(3, n/3)*2);
    }
}
