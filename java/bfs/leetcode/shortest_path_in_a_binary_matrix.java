/*
1091. Shortest Path in Binary Matrix

Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

 

Example 1:


Input: grid = [[0,1],[1,0]]
Output: 2
Example 2:


Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1

*/

//Soln using binary search TC O(N) SC O(N^2)
class Solution {

    private int dp[][];
    private final int dirs[][] = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;
        dp = new int[n][n];
        for (int i=0;i<n;i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[n-1][n-1] = 1;
        Queue<int[]> queue = new ArrayDeque();
        queue.offer(new int[]{n-1, n-1});
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0], col = cell[1], dist = dp[row][col];
            if (row == 0 && col == 0) return dist;
            for (int[] dir: dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (newRow < 0 || newRow == n || newCol < 0 || newCol == n || grid[newRow][newCol] == 1 || dp[newRow][newCol] != Integer.MAX_VALUE) continue;
                queue.offer(new int[]{newRow, newCol});
                dp[newRow][newCol] = dist+1;
            }
        }
        return -1;
    }
}
