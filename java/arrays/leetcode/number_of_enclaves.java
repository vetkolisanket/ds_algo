/*
1020. Number of Enclaves

You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

 

Example 1:


Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
Example 2:


Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation: All 1s are either on the boundary or can reach the boundary.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid[i][j] is either 0 or 1.

*/

//Soln using BFS TC O(M*N) SC O(M*N)
class Solution {

    private int grid[][], m, n;

    public int numEnclaves(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        for (int i=0;i<n;i++) {
            if (grid[0][i] == 1) sinkIsland(0, i);
            if (grid[m-1][i] == 1) sinkIsland(m-1, i);
        }
        for (int i=0;i<m;i++) {
            if (grid[i][0] == 1) sinkIsland(i, 0);
            if (grid[i][n-1] == 1) sinkIsland(i, n-1);
        }
        int ans = 0;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (grid[i][j] == 1) ans++;
            }
        }
        return ans;
    }

    private void sinkIsland(int i, int j) {
        grid[i][j] = 0;
        if (i > 0 && grid[i-1][j] == 1) sinkIsland(i-1, j);
        if (i < m-1 && grid[i+1][j] == 1) sinkIsland(i+1, j);
        if (j > 0 && grid[i][j-1] == 1) sinkIsland(i, j-1);
        if (j < n-1 && grid[i][j+1] == 1) sinkIsland(i, j+1);
    }
}
