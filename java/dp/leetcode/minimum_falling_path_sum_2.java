/*
1289. Minimum Falling Path Sum II

Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.

A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two elements chosen in adjacent rows are in the same column.

 

Example 1:


Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
Output: 13
Explanation: 
The possible falling paths are:
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
The falling path with the smallest sum is [1,5,7], so the answer is 13.
Example 2:

Input: grid = [[7]]
Output: 7
 

Constraints:

n == grid.length == grid[i].length
1 <= n <= 200
-99 <= grid[i][j] <= 99

*/

//Soln using top down dp TC O(N^3) SC O(N^2)
class Solution {

    Integer[][] memo;

    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        memo = new Integer[n][n];
        int ans = Integer.MAX_VALUE;
        for (int i=0;i<n;i++) {
            ans = Math.min(ans, optimize(grid, 0, i));
        }
        return ans;
    }

    private int optimize(int[][] grid, int row, int col) {
        if (row == grid.length-1) return grid[row][col];
        if (memo[row][col] != null) return memo[row][col];
        int nextMin = Integer.MAX_VALUE;
        for (int i=0;i<grid.length;i++) {
            if (i == col) continue;
            nextMin = Math.min(nextMin, optimize(grid, row+1, i));
        }
        return memo[row][col] = grid[row][col] + nextMin;
    }
}

//Bottom up dp soln with space optimisation TC O(N^2) SC O(1)
class Solution {
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length, nextMin1 = -1, nextMin1c = -1, nextMin2 = -1,
            nextMin2c = -1;
        for (int i=0;i<n;i++) {
            if (nextMin1 == -1 || grid[n-1][i] <= nextMin1) {
                nextMin2 = nextMin1;
                nextMin2c = nextMin1c;
                nextMin1 = grid[n-1][i];
                nextMin1c = i;
            } else if (nextMin2 == -1 || grid[n-1][i] <= nextMin2) {
                nextMin2 = grid[n-1][i];
                nextMin2c = i;
            }
        }

        for (int row = n-2;row>=0;row--) {
            int min1 = -1, min1c = -1, min2 = -1, min2c = -1;
            for (int col=0;col<n;col++) {
                int val;
                if (col == nextMin1c) {
                    val = grid[row][col] + nextMin2;
                } else {
                    val = grid[row][col] + nextMin1;
                }

                if (min1 == -1 || val <= min1) {
                    min2 = min1;
                    min2c = min1c;
                    min1 = val;
                    min1c = col;
                } else if (min2 == -1 || val <= min2) {
                    min2 = val;
                    min2c = col;
                }
            }

            nextMin1 = min1;
            nextMin1c = min1c;
            nextMin2 = min2;
            nextMin2c = min2c;
        }
        return nextMin1;
    }
}
