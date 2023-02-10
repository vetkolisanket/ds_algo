/*
1162. As Far from Land as Possible

Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

 

Example 1:


Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
Example 2:


Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1

*/

//Soln using BFS TC O(n^2) SC O(1)
class Solution {
    public int maxDistance(int[][] grid) {
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = grid.length, n = grid[0].length;
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque();
        int dist = -1;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new Pair(i, j));
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Pair<Integer, Integer> p = queue.poll();
                for (int[] dir: dirs) {
                    int row = p.getKey() + dir[0];
                    int col = p.getValue() + dir[1];
                    if (row < 0 || row == m || col < 0 || col == n || grid[row][col] == 1) continue;
                    grid[row][col] = 1;
                    queue.offer(new Pair(row, col));
                }
            }
            dist++;
        }
        return dist == 0 ? -1 : dist;
    }
}
