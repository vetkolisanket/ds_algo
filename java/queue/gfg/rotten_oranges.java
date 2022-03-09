/*
Rotten Oranges

Given a grid of dimension nxm where each cell in the grid can have values 0, 1 or 2 which has the following meaning:
0 : Empty cell
1 : Cells have fresh oranges
2 : Cells have rotten oranges

We have to determine what is the minimum time required to rot all oranges. A rotten orange at index [i,j] can rot other fresh orange at indexes [i-1,j], [i+1,j], [i,j-1], [i,j+1] (up, down, left and right) in unit time. 
 

Example 1:

Input: grid = {{0,1,2},{0,1,2},{2,1,1}}
Output: 1
Explanation: The grid is-
0 1 2
0 1 2
2 1 1
Oranges at positions (0,2), (1,2), (2,0)
will rot oranges at (0,1), (1,1), (2,2) and 
(2,1) in unit time.
Example 2:

Input: grid = {{2,2,0,1}}
Output: -1
Explanation: The grid is-
2 2 0 1
Oranges at (0,0) and (0,1) can't rot orange at
(0,3).
 

Your Task:
You don't need to read or print anything, Your task is to complete the function orangesRotting() which takes grid as input parameter and returns the minimum time to rot all the fresh oranges. If not possible returns -1.
 

Expected Time Complexity: O(n*m)
Expected Auxiliary Space: O(n)
 

Constraints:
1 ≤ n, m ≤ 500
*/

//Soln using queue and bfs
class Solution
{
    //Function to find minimum time required to rot all oranges. 
    public int orangesRotting(int[][] grid)
    {
        // Code here
        int total = 0, cnt = 0, minTime = 0;
        Queue<int[]> q = new ArrayDeque();
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                }
                if (grid[i][j] != 0) total++;
            }
        }
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        while (!q.isEmpty()) {
            int size = q.size();
            cnt += size;
            while (size-- > 0) {
                int[] pos = q.poll();
                for (int i=0;i<4;i++) {
                    int x = pos[0] + dx[i];
                    int y = pos[1] + dy[i];
                    if (x < 0 || x == grid.length || y < 0 || y == grid[0].length
                    || grid[x][y] != 1) continue;
                    grid[x][y] = 2;
                    q.offer(new int[]{x, y});
                }
            }
            if (!q.isEmpty()) minTime++;
        }
        return total == cnt ? minTime : -1;
    }
}

//My soln
class Solution
{
    //Function to find minimum time required to rot all oranges. 
    public int orangesRotting(int[][] grid)
    {
        // Code here
        if (allRotten(grid)) return 0;
        int minTime = 0;
        while (canRot(grid)) {
            rot(grid);
            minTime++;
        }
        return allRotten(grid) ? minTime : -1;
    }
    
    private void rot(int[][] grid) {
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                if (grid[i][j] == 3) grid[i][j] = 2;
            }
        }
    }
    
    private boolean allRotten(int[][] grid) {
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                if (grid[i][j] == 1) return false;
            }
        }
        return true;
    }
    
    private boolean canRot(int[][] grid) {
        boolean canRot = false;
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                if (grid[i][j] == 2) {
                    if (canRot(grid, i-1, j)) canRot = true;
                    if (canRot(grid, i+1, j)) canRot = true;
                    if (canRot(grid, i, j-1)) canRot = true;
                    if (canRot(grid, i, j+1)) canRot = true;
                }
            }
        }
        return canRot;
    }
    
    private boolean canRot(int[][] grid, int i, int j) {
        if (i<0 || i == grid.length || j < 0 || j == grid[0].length
        || grid[i][j] != 1) return false;
        grid[i][j] = 3;
        return true;
    }
}
