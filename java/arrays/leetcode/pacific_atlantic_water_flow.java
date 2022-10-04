/*
417. Pacific Atlantic Water Flow

There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.

 

Example 1:


Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
[0,4]: [0,4] -> Pacific Ocean 
       [0,4] -> Atlantic Ocean
[1,3]: [1,3] -> [0,3] -> Pacific Ocean 
       [1,3] -> [1,4] -> Atlantic Ocean
[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean 
       [1,4] -> Atlantic Ocean
[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean 
       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
[3,0]: [3,0] -> Pacific Ocean 
       [3,0] -> [4,0] -> Atlantic Ocean
[3,1]: [3,1] -> [3,0] -> Pacific Ocean 
       [3,1] -> [4,1] -> Atlantic Ocean
[4,0]: [4,0] -> Pacific Ocean 
       [4,0] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
Example 2:

Input: heights = [[1]]
Output: [[0,0]]
Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
 

Constraints:

m == heights.length
n == heights[r].length
1 <= m, n <= 200
0 <= heights[r][c] <= 10^5
*/

//Using BFS TC O(m*n) SC O(m*n) where m,n are no. of rows, cols in the heights matrix
class Solution {
    
    private final int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private int numRows;
    private int numCols;
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        numRows = heights.length;
        numCols = heights[0].length;
        Queue<int[]> pacificQueue = new ArrayDeque();
        Queue<int[]> atlanticQueue = new ArrayDeque();
        for (int i=0;i<numRows;i++) {
            pacificQueue.offer(new int[]{i, 0});
            atlanticQueue.offer(new int[]{i, numCols-1});
        }
        for (int i=0;i<numCols;i++) {
            pacificQueue.offer(new int[]{0, i});
            atlanticQueue.offer(new int[]{numRows-1, i});
        }
        boolean[][] pacificReachable = bfs(pacificQueue, heights);
        boolean[][] atlanticReachable = bfs(atlanticQueue, heights);
        List<List<Integer>> list = new ArrayList();
        for (int i=0;i<numRows;i++) {
            for (int j=0;j<numCols;j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    list.add(List.of(i, j));
                }
            }
        }
        return list;
    }
    
    private boolean[][] bfs(Queue<int[]> queue, int[][] heights) {
        boolean[][] reachable = new boolean[numRows][numCols];
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            reachable[cell[0]][cell[1]] = true;
            for (int[] dir: dirs) {
                int newRow = cell[0] - dir[0];
                int newCol = cell[1] - dir[1];
                if (newRow < 0 || newRow == numRows || newCol < 0 || newCol == numCols) {
                    continue;
                }
                if (reachable[newRow][newCol]) continue;
                if (heights[newRow][newCol] < heights[cell[0]][cell[1]]) {
                    continue;
                }
                queue.offer(new int[]{newRow, newCol});
            }
        }
        return reachable;
    }
}

//My soln using DFS on second attempt TC O(m*n) SC O(m*n)
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        Set<Pair<Integer, Integer>> atlantic = new HashSet();
        Set<Pair<Integer, Integer>> pacific = new HashSet();
        int m = heights.length;
        int n = heights[0].length;
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        boolean[][] visited = new boolean[m][n];
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (i == 0 || j == 0) {
                    pacific.add(new Pair(i, j));
                    dfs(i, j, pacific, dirs, heights, visited);
                }
                if (i == m-1 || j == n-1) {
                    atlantic.add(new Pair(i, j));
                    dfs(i, j, atlantic, dirs, heights, visited);
                }
            }
        }
        List<List<Integer>> list = new ArrayList();
        for (Pair<Integer, Integer> pair: pacific) {
            if (atlantic.contains(pair)) {
                List<Integer> temp = new ArrayList();
                temp.add(pair.getKey());
                temp.add(pair.getValue());
                list.add(temp);
            }
        }
        return list;
    }
    
    private void dfs(int row, int col, Set<Pair<Integer, Integer>> set, int[][] dirs, int[][] heights, boolean[][] visited) {
        visited[row][col] = true;
        for (int[] dir: dirs) {
            int newRow = row+dir[0];
            int newCol = col+dir[1];
            if (newRow >= 0 && newRow < heights.length && newCol >= 0 && newCol < heights[0].length && !visited[newRow][newCol] && heights[newRow][newCol] >= heights[row][col]) {
                if (set.add(new Pair(newRow, newCol))) {
                    dfs(newRow, newCol, set, dirs, heights, visited);
                }
            }
        }
        visited[row][col] = false;
    }
}

//Slightly cleaner DFS same complexity as above TC O(m*n) SC O(m*n)
class Solution {
    
    private static final int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private int rows, cols;
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        rows = heights.length;
        cols = heights[0].length;
        boolean[][] pacificReachable = new boolean[rows][cols];
        boolean[][] atlanticReachable = new boolean[rows][cols];
        for (int i=0;i<rows;i++) {
            dfs(i, 0, pacificReachable, heights);
            dfs(i, cols-1, atlanticReachable, heights);
        }
        for (int i=0;i<cols;i++) {
            dfs(0, i, pacificReachable, heights);
            dfs(rows-1, i, atlanticReachable, heights);
        }
        List<List<Integer>> list = new ArrayList();
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    list.add(List.of(i, j));
                }
            }
        }
        return list;
    }
    
    private void dfs(int row, int col, boolean[][] reachable, int[][] heights) {
        reachable[row][col] = true;
        for (int[] dir: dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            if (newRow == -1 || newRow == rows || newCol == -1 || newCol == cols) {
                continue;
            }
            if (reachable[newRow][newCol]) continue;
            if (heights[newRow][newCol] < heights[row][col]) continue;
            dfs(newRow, newCol, reachable, heights);
        }
    }
}
