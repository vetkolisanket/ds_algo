/*
934. Shortest Bridge

You are given an n x n binary matrix grid where 1 represents land and 0 represents water.

An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.

You may change 0's to 1's to connect the two islands to form one island.

Return the smallest number of 0's you must flip to connect the two islands.

 

Example 1:

Input: grid = [[0,1],[1,0]]
Output: 1
Example 2:

Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
Example 3:

Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1
 

Constraints:

n == grid.length == grid[i].length
2 <= n <= 100
grid[i][j] is either 0 or 1.
There are exactly two islands in grid.
*/

//Soln using DFS + BFS TC O(N^2) SC O(N^2)
class Solution {

    private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int n;

    public int shortestBridge(int[][] grid) {
        n = grid.length;
        boolean isIslandMarked = false;
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (grid[i][j] == 1) {
                    markIsland(grid, i, j);
                    isIslandMarked = true;
                    break;
                }
            }
            if (isIslandMarked) break;
        }
        Queue<int[]> queue = getIsland(grid);
        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Queue<int[]> newQueue = new ArrayDeque();
            while (size-- > 0) {
                int[] cell = queue.poll();
                for (int[] dir: dirs) {
                    int newRow = cell[0] + dir[0];
                    int newCol = cell[1] + dir[1];
                    if (newRow == -1 || newRow == n || newCol == -1 || newCol == n || grid[newRow][newCol] == 2 || grid[newRow][newCol] == -1) {
                        continue;
                    } else if (grid[newRow][newCol] == 1) {
                        return dist;
                    } else {
                        grid[newRow][newCol] = -1;
                        newQueue.offer(new int[]{newRow, newCol});
                    }
                }
            }
            dist++;
            queue = newQueue;
        }
        return -1;
    }

    private void markIsland(int[][] grid, int row, int col) {
        grid[row][col] = 2;
        for (int[] dir: dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow == -1 || newRow == n || newCol == -1 || newCol == n || grid[newRow][newCol] == 0 || grid[newRow][newCol] == 2) {
                continue;
            }
            markIsland(grid, newRow, newCol);
        }
    }

    private Queue<int[]> getIsland(int[][] grid) {
        Queue<int[]> queue = new ArrayDeque();
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        return queue;
    }
}
