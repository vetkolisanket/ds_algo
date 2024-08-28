/*
1905. Count Sub Islands

You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

Return the number of islands in grid2 that are considered sub-islands.

 

Example 1:


Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
Example 2:


Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2 
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
 

Constraints:

m == grid1.length == grid2.length
n == grid1[i].length == grid2[i].length
1 <= m, n <= 500
grid1[i][j] and grid2[i][j] are either 0 or 1.

*/

//Soln using DFS TC O(M*N) SC O(M*N)
class Solution {

    private int m, n;
    private int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        m = grid1.length;
        n = grid1[0].length;
        int val = 2;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (grid2[i][j] == 1) {
                    mark(grid2, i, j, val++);

                }
            }
        }
        int ans = 0;
        Set<Integer> set = new HashSet();
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (grid2[i][j] != 0 && grid1[i][j] == 0) {
                    set.add(grid2[i][j]);
                }
            }
        }
        for (int i=2;i<val;i++) {
            if (!set.contains(i)) {
                ans++;
            }
        }
        return ans;
    }

    private void mark(int[][] grid, int row, int col, int val) {
        if (row == -1 || row == m || col == -1 || col == n || grid[row][col] != 1) {
            return;
        }
        grid[row][col] = val;
        for (int[] dir: dirs) {
            mark(grid, row + dir[0], col + dir[1], val);
        }
    }
}
