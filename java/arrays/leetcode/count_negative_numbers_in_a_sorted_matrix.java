/*
1351. Count Negative Numbers in a Sorted Matrix

Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.

 

Example 1:

Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
Output: 8
Explanation: There are 8 negatives number in the matrix.
Example 2:

Input: grid = [[3,2],[1,0]]
Output: 0
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
-100 <= grid[i][j] <= 100
 

Follow up: Could you find an O(n + m) solution?

*/

//Soln using linear traversal TC O(N+M) SC O(1) where M is the no. of rows and N is the no. of columns respectively
class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int curRowNegativeIdx = n-1, ans = 0;
        for (int i=0;i<m;i++) {
            while (curRowNegativeIdx >= 0 && grid[i][curRowNegativeIdx] < 0) {
                curRowNegativeIdx--;
            }
            ans += (n - curRowNegativeIdx - 1);
        }
        return ans;
    }
}

