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

class Solution
{
    //Function to find minimum time required to rot all oranges. 
    public int orangesRotting(int[][] grid)
    {
        // Code here
        int minTime = 0;
        Queue<int[]> queue = new ArrayDeque();
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (grid[i][j] == 2) queue.offer(new int[]{i, j});
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int[] pos = queue.poll();
                for (int[] step: dir) {
                    int di = pos[0] + step[0];
                    int dj = pos[1] + step[1];
                    if (di >= 0 && di < n && dj >= 0 && dj < m 
                    && grid[di][dj] == 1) {
                        grid[di][dj] = 2;
                        queue.offer(new int[]{di, dj});
                    }
                }
                size--;
            }
            if (!queue.isEmpty()) minTime++;
        }
        return areAllOrangesRotten(grid, n, m) ? minTime : -1;
    }
    
    private boolean areAllOrangesRotten(int[][] grid, int n, int m) {
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (grid[i][j] == 1) return false;
            }
        } 
        return true;
    }
}
