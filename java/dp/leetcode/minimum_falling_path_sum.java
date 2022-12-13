/*
931. Minimum Falling Path Sum

Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

 

Example 1:


Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
Output: 13
Explanation: There are two falling paths with a minimum sum as shown.
Example 2:


Input: matrix = [[-19,57],[-40,-5]]
Output: -59
Explanation: The falling path with a minimum sum is shown.
 

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 100
-100 <= matrix[i][j] <= 100

*/

//My soln using bottom up dp TC O(N^2) SC O(N^2)
class Solution {

    int m, n;

    public int minFallingPathSum(int[][] matrix) {
        m = matrix.length; n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i=0;i<n;i++) {
            dp[m-1][i] = matrix[m-1][i];
        }
        for (int i=m-2;i>=0;i--) {
            for (int j=0;j<n;j++) {
                dp[i][j] = dp[i+1][j] + matrix[i][j];
                if (j > 0) {
                    dp[i][j] = Math.min(dp[i][j], matrix[i][j] + dp[i+1][j-1]);
                }
                if (j < n-1) {
                    dp[i][j] = Math.min(dp[i][j], matrix[i][j] + dp[i+1][j+1]);
                }
            }
        }
        int minSum = dp[0][0];
        for (int i=1;i<n;i++) {
            minSum = Math.min(minSum, dp[0][i]);
        }
        return minSum;
    }
}

//A more space efficient soln TC O(N^2) SC O(N)
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[] dp = matrix[n-1].clone();
        for (int i=n-2;i>=0;i--) {
            int[] cur = new int[n];
            for (int j=0;j<n;j++) {
                cur[j] = dp[j];
                if (j > 0) {
                    cur[j] = Math.min(cur[j], dp[j-1]);
                }
                if (j < n-1) {
                    cur[j] = Math.min(cur[j], dp[j+1]);
                }
                cur[j] += matrix[i][j];
            }
            dp = cur;
        }
        int minSum = Integer.MAX_VALUE;
        for (int i=0;i<n;i++) {
            minSum = Math.min(minSum, dp[i]);
        }
        return minSum;
    }
}
