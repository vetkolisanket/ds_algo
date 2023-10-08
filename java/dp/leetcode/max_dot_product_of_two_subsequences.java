/*
1458. Max Dot Product of Two Subsequences

Given two arrays nums1 and nums2.

Return the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length.

A subsequence of a array is a new array which is formed from the original array by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, [2,3,5] is a subsequence of [1,2,3,4,5] while [1,5,3] is not).

 

Example 1:

Input: nums1 = [2,1,-2,5], nums2 = [3,0,-6]
Output: 18
Explanation: Take subsequence [2,-2] from nums1 and subsequence [3,-6] from nums2.
Their dot product is (2*3 + (-2)*(-6)) = 18.
Example 2:

Input: nums1 = [3,-2], nums2 = [2,-6,7]
Output: 21
Explanation: Take subsequence [3] from nums1 and subsequence [7] from nums2.
Their dot product is (3*7) = 21.
Example 3:

Input: nums1 = [-1,-1], nums2 = [1,1]
Output: -1
Explanation: Take subsequence [-1] from nums1 and subsequence [1] from nums2.
Their dot product is -1.
 

Constraints:

1 <= nums1.length, nums2.length <= 500
-1000 <= nums1[i], nums2[i] <= 1000

*/

//Soln using top down dp TC O(MN) SC O(MN) where M,N is the length of nums1 and nums2 respectively
class Solution {

    int memo[][];

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int firstMax = Integer.MIN_VALUE, secondMax = Integer.MIN_VALUE, firstMin = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;

        for (int num: nums1) {
            firstMin = Math.min(firstMin, num);
            firstMax = Math.max(firstMax, num);
        }
        for (int num: nums2) {
            secondMin = Math.min(secondMin, num);
            secondMax = Math.max(secondMax, num);
        }
        if (firstMax < 0 && secondMin > 0) {
            return firstMax * secondMin;
        }
        if (firstMin > 0 && secondMax < 0) {
            return firstMin * secondMax;
        }
        memo = new int[nums1.length][nums2.length];
        return dp(0, 0, nums1, nums2);
    }

    private int dp(int i, int j, int[] nums1, int[] nums2) {
        if (i == nums1.length || j == nums2.length) return 0;

        if (memo[i][j] != 0) return memo[i][j];
        int val1 = nums1[i]*nums2[j] + dp(i+1, j+1, nums1, nums2);
        int val2 = dp(i+1, j, nums1, nums2);
        int val3 = dp(i, j+1, nums1, nums2);
        return memo[i][j] = Math.max(val1, Math.max(val2, val3));
    }
}

//Soln using bottom up DP TC O(MN) SC O(MN)
class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int firstMax = Integer.MIN_VALUE, secondMax = Integer.MIN_VALUE, firstMin = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;

        for (int num: nums1) {
            firstMin = Math.min(firstMin, num);
            firstMax = Math.max(firstMax, num);
        }
        for (int num: nums2) {
            secondMin = Math.min(secondMin, num);
            secondMax = Math.max(secondMax, num);
        }
        if (firstMin > 0 && secondMax < 0) {
            return firstMin * secondMax;
        }
        if (firstMax < 0 && secondMin > 0) {
            return firstMax * secondMin;
        }
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        for (int i=nums1.length-1;i>=0;i--) {
            for (int j=nums2.length-1;j>=0;j--) {
                int val = nums1[i]*nums2[j] + dp[i+1][j+1];
                dp[i][j] = Math.max(val, Math.max(dp[i+1][j], dp[i][j+1]));
            }
        }
        return dp[0][0];
    }
}

//Space optimised bottom up dp TC O(MN) SC O(M)
class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int firstMax = Integer.MIN_VALUE, secondMax = Integer.MIN_VALUE, firstMin = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;

        for (int num: nums1) {
            firstMin = Math.min(firstMin, num);
            firstMax = Math.max(firstMax, num);
        }
        for (int num: nums2) {
            secondMin = Math.min(secondMin, num);
            secondMax = Math.max(secondMax, num);
        }
        if (firstMin > 0 && secondMax < 0) {
            return firstMin * secondMax;
        }
        if (firstMax < 0 && secondMin > 0) {
            return firstMax * secondMin;
        }
        int[] dp = new int[nums2.length+1], prevDp = new int[nums2.length+1];
        for (int i=nums1.length-1;i>=0;i--) {
            dp = new int[nums2.length+1];
            for (int j=nums2.length-1;j>=0;j--) {
                int val = nums1[i]*nums2[j] + prevDp[j+1];
                dp[j] = Math.max(val, Math.max(prevDp[j], dp[j+1]));
            }
            prevDp = dp;
        }
        return dp[0];
    }
}
