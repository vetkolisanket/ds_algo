/*
2328. Number of Increasing Paths in a Grid

You are given an m x n integer matrix grid, where you can move from a cell to any adjacent cell in all 4 directions.

Return the number of strictly increasing paths in the grid such that you can start from any cell and end at any cell. Since the answer may be very large, return it modulo 109 + 7.

Two paths are considered different if they do not have exactly the same sequence of visited cells.

 

Example 1:


Input: grid = [[1,1],[3,4]]
Output: 8
Explanation: The strictly increasing paths are:
- Paths with length 1: [1], [1], [3], [4].
- Paths with length 2: [1 -> 3], [1 -> 4], [3 -> 4].
- Paths with length 3: [1 -> 3 -> 4].
The total number of paths is 4 + 3 + 1 = 8.
Example 2:

Input: grid = [[1],[2]]
Output: 3
Explanation: The strictly increasing paths are:
- Paths with length 1: [1], [2].
- Paths with length 2: [1 -> 2].
The total number of paths is 2 + 1 = 3.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 1000
1 <= m * n <= 10^5
1 <= grid[i][j] <= 10^5
*/

class Solution {
    
    private final int MOD = 1000000007;
    
    public int countPaths(int[][] grid) {
        Long dp[][] = new Long[grid.length][grid[0].length], count = 0L;
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                count = (count + countPaths(i, j, grid, dp)) % MOD;
            }
        }
        return count.intValue();
    }
    
    private long countPaths(int i, int j, int[][] grid, Long[][] dp) {
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        long left = 0L, right = 0L, top = 0L, bottom = 0L;
        if (i > 0 && grid[i-1][j] > grid[i][j]) {
            top = countPaths(i-1, j, grid, dp);
        }
        if (j > 0 && grid[i][j-1] > grid[i][j]) {
            left = countPaths(i, j-1, grid, dp);
        }
        if (i < grid.length-1 && grid[i+1][j] > grid[i][j]) {
            bottom = countPaths(i+1, j, grid, dp);
        }
        if (j < grid[0].length-1 && grid[i][j+1] > grid[i][j]) {
            right = countPaths(i, j+1, grid, dp);
        }
        dp[i][j] = (1+left+right+top+bottom)%MOD;
        return dp[i][j];
    }
    
}
