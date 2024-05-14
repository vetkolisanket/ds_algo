/*
1219. Path with Maximum Gold

In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.

Return the maximum amount of gold you can collect under the conditions:

Every time you are located in a cell you will collect all the gold in that cell.
From your position, you can walk one step to the left, right, up, or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.
 

Example 1:

Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
Explanation:
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -> 8 -> 7.
Example 2:

Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
Output: 28
Explanation:
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 15
0 <= grid[i][j] <= 100
There are at most 25 cells containing gold.

*/

//Soln using DFS and backtracking TC O(MN4^G) SC O(G) where M is the no. of rows, N is the no. of cols and G is the no. of cells having gold
class Solution {

    private int[][] dirs = new int[][]{{-1,0}, {0,1}, {1,0}, {0,-1}};
    private int m, n, grid[][];

    public int getMaximumGold(int[][] grid) {
        int ans = 0;
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (grid[i][j] == 0) continue;
                ans = Math.max(ans, dfs(i, j, new boolean[m][n]));
            }
        }
        return ans;
    }

    private int dfs(int row, int col, boolean[][] visited) {
        if (row == -1 || row == m || col == -1 || col == n || 
            visited[row][col] || grid[row][col] == 0) {
                return 0;
            }
        visited[row][col] = true;
        int val = 0;
        for (int[] dir: dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            val = Math.max(val, dfs(newRow, newCol, visited));
        }
        visited[row][col] = false;
        return grid[row][col] + val;
    }
}
