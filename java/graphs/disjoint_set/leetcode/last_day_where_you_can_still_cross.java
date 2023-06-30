/*
1970. Last Day Where You Can Still Cross

There is a 1-based binary matrix where 0 represents land and 1 represents water. You are given integers row and col representing the number of rows and columns in the matrix, respectively.

Initially on day 0, the entire matrix is land. However, each day a new cell becomes flooded with water. You are given a 1-based 2D array cells, where cells[i] = [ri, ci] represents that on the ith day, the cell on the rith row and cith column (1-based coordinates) will be covered with water (i.e., changed to 1).

You want to find the last day that it is possible to walk from the top to the bottom by only walking on land cells. You can start from any cell in the top row and end at any cell in the bottom row. You can only travel in the four cardinal directions (left, right, up, and down).

Return the last day where it is possible to walk from the top to the bottom by only walking on land cells.

 

Example 1:


Input: row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]]
Output: 2
Explanation: The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 2.
Example 2:


Input: row = 2, col = 2, cells = [[1,1],[1,2],[2,1],[2,2]]
Output: 1
Explanation: The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 1.
Example 3:


Input: row = 3, col = 3, cells = [[1,2],[2,1],[3,3],[2,2],[1,1],[1,3],[2,3],[3,2],[3,1]]
Output: 3
Explanation: The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 3.
 

Constraints:

2 <= row, col <= 2 * 10^4
4 <= row * col <= 2 * 10^4
cells.length == row * col
1 <= ri <= row
1 <= ci <= col
All the values of cells are unique.
*/

//Soln using disjoint set TC O(row*col) SC O(row*col)
class Solution {
    public int latestDayToCross(int row, int col, int[][] cells) {
        UnionFind uf = new UnionFind(row * col + 2);
        int[][] grid = new int[row][col];
        int[][] dirs = {{1,0}, {0,1}, {-1,0}, {0,-1}};

        for (int i = cells.length-1; i >= 0; i--) {
            int[] cell = cells[i];
            int r = cell[0]-1, c = cell[1]-1;
            grid[r][c] = 1;
            int index1 = r * col + c + 1;
            for (int[] dir: dirs) {
                int newRow = r + dir[0], newCol = c + dir[1];
                if (newRow == -1 || newRow == row || newCol == -1 || newCol == col || grid[newRow][newCol] == 0) continue;
                int index2 = newRow * col + newCol + 1;
                uf.union(index1, index2);
            }
            if (r == 0) uf.union(0, index1);
            if (r == row-1) uf.union(row * col + 1, index1);
            if (uf.find(0) == uf.find(row * col + 1)) return i;
        }
        return -1;
    }
}

class UnionFind {

    private int parent[], rank[];

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i=0;i<n;i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;
        if (rank[x] > rank[y]) {
            parent[y] = x;
            rank[x]++;
        } else {
            parent[x] = y;
            rank[y]++;
        }
    }

}
