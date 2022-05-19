/*
329. Longest Increasing Path in a Matrix

Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

 

Example 1:


Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:


Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
Example 3:

Input: matrix = [[1]]
Output: 1
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
0 <= matrix[i][j] <= 2^31 - 1
*/

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] paths = new int[m][n];
        for (int[] arr: paths) {
            Arrays.fill(arr, -1);
        }
        int[][] dirs = new int[][]{{0,-1}, {-1,0}, {0,1}, {1,0}};
        int max = 1;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                paths[i][j] = findLongestIncreasingPath(matrix, paths, dirs, i, j);
                max = Math.max(max, paths[i][j]);
            }
        }
        return max;
    }
    
    private int findLongestIncreasingPath(int[][] matrix, int[][] paths, int[][] dirs, int i, int j) {
        if (paths[i][j] > 0) return paths[i][j];
        int max = 1;
        for (int[] dir: dirs) {
            int row = i+dir[0];
            int col = j+dir[1];
            if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length && matrix[i][j] < matrix[row][col]) {
                paths[row][col] = findLongestIncreasingPath(matrix, paths, dirs, row, col);
                max = Math.max(max, 1+paths[row][col]);
            }
        }
        return max;
    }
}
