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

class Solution {
    
    private static final int[][] directions = new int[][]{{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1 || grid[grid.length-1][grid.length-1] == 1) {
            return -1;
        }
        
        if (grid.length == 1) return 1;
        
        Queue<int[]> q = new ArrayDeque();
        q.offer(new int[]{0,0});
        grid[0][0] = 1;
        while (!q.isEmpty()) {
            int[] arr = q.poll();
            int distance = grid[arr[0]][arr[1]];
            for (int[] neighbor: findNeighbors(arr, grid)) {
                if (neighbor[0] == grid.length-1 && neighbor[1] == grid.length-1) {
                    return distance+1;
                } else {
                    grid[neighbor[0]][neighbor[1]] = distance+1;
                    q.offer(neighbor);
                }
            }
        }
        return -1;
    }
    
    private List<int[]> findNeighbors(int[] arr, int[][] grid) {
        List<int[]> list = new ArrayList();
        for (int[] dir: directions) {
            int i = arr[0] + dir[0];
            int j = arr[1] + dir[1];
            if (i >= 0 && j >= 0 && i < grid.length && j < grid.length && grid[i][j] == 0) {
                list.add(new int[]{i,j});
            }
        }
        return list;
    }
}
