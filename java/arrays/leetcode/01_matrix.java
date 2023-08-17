/*
542. 01 Matrix

Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

 

Example 1:


Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
Example 2:


Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.

*/

//Soln using BFS TC O(MN) SC O(MN)
class State {
    int row, col, steps;

    public State(int row, int col, int steps) {
        this.row = row;
        this.col = col;
        this.steps = steps;
    }
}

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] ans = new int[m][n];
        boolean[][] seen = new boolean[m][n];
        Queue<State> q = new ArrayDeque();
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (mat[i][j] == 0) {
                    seen[i][j] = true;
                    q.offer(new State(i, j, 0));
                }
            }
        }
        int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        while (!q.isEmpty()) {
            State state = q.poll();
            for (int[] dir: dirs) {
                int newRow = state.row + dir[0];
                int newCol = state.col + dir[1];
                int newSteps = state.steps + 1;
                if (newRow == -1 || newRow == m || newCol == -1 || newCol == n || seen[newRow][newCol]) continue;
                seen[newRow][newCol] = true;
                q.offer(new State(newRow, newCol, newSteps));
                ans[newRow][newCol] = newSteps;
            }
        }
        return ans;
    }
}
