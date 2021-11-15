/*
63. Unique Paths II

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and space is marked as 1 and 0 respectively in the grid.

 

Example 1:


Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
Example 2:


Input: obstacleGrid = [[0,1],[0,0]]
Output: 1
 

Constraints:

m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] is 0 or 1.
*/

//Better DP soln
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row: obstacleGrid) {
            for (int j=0; j<width; j++) {
                if (row[j] == 1) dp[j] = 0;
                else if (j > 0) dp[j] += dp[j-1];
            }
        }
        return dp[width-1];
    }
}

//My soln
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] memo = new int[obstacleGrid.length+1][obstacleGrid[0].length+1];
        return computePaths(obstacleGrid, memo, 0, 0);
    }
    
    private int computePaths(int[][] arr, int[][] memo, int i, int j) {
        if (i == arr.length || j == arr[0].length || arr[i][j] == 1) return 0;
        else if (i == arr.length-1 && j == arr[0].length-1) return 1;
        else if (memo[i][j] > 0) return memo[i][j];
        else {
            int down = memo[i+1][j] > 0 ? memo[i+1][j] : computePaths(arr, memo, i+1, j);
            int right = memo[i][j+1] > 0 ? memo[i][j+1] : computePaths(arr, memo, i, j+1);
            memo[i][j] = down + right;
            return memo[i][j];
        } 
    }
}
