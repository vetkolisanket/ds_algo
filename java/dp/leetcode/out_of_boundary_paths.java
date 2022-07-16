/*
576. Out of Boundary Paths

There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.

Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.

 

Example 1:


Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
Output: 6
Example 2:


Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
Output: 12
 

Constraints:

1 <= m, n <= 50
0 <= maxMove <= 50
0 <= startRow < m
0 <= startColumn < n

*/

//TC O(mnN) SC(mnN) where m = no. of rows, n = no. of cols and N = no. of moves
class Solution {
    
    private final int MOD = 1000000007;
    Integer[][][] memo;
    
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        memo = new Integer[m][n][maxMove+1];
        return backtrack(m, n, maxMove, startRow, startColumn);
    }
    
    private int backtrack(int m, int n, int maxMove, int startRow, int startColumn) {
        if (maxMove < 0) return 0;
        if (startRow < 0 || startRow == m || startColumn < 0 || startColumn == n) {
            return 1;
        }
        if (memo[startRow][startColumn][maxMove] != null) {
            return memo[startRow][startColumn][maxMove];
        }
        int ans = 0;
        ans = ((backtrack(m, n, maxMove-1, startRow-1, startColumn) + backtrack(m, n, maxMove-1, startRow+1, startColumn)) % MOD + (backtrack(m, n, maxMove-1, startRow, startColumn-1) + backtrack(m, n, maxMove-1, startRow, startColumn+1)) % MOD) % MOD;
        return memo[startRow][startColumn][maxMove] = ans;
    }
}
