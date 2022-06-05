/*
52. N-Queens II

The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

 

Example 1:


Input: n = 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
Example 2:

Input: n = 1
Output: 1
 

Constraints:

1 <= n <= 9
*/

//Faster soln
class Solution {
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] d1 = new boolean[2*n-1];
        boolean[] d2 = new boolean[2*n-1];
        int soln = dfs(cols, d1, d2, 0, 0);
        return soln;
    }
    
    private int dfs(boolean[] cols, boolean[] d1, boolean[] d2, int row, int soln) {
        int n = cols.length;
        if (row == n) {
            return soln + 1;
        }
        for (int i=0;i<n;i++) {
            int id1 = row - i + n - 1;
            int id2 = row + i;
            if (cols[i] || d1[id1] || d2[id2]) continue;
            cols[i] = true;
            d1[id1] = true;
            d2[id2] = true;
            soln = dfs(cols, d1, d2, row+1, soln);
            cols[i] = false;
            d1[id1] = false;
            d2[id2] = false;
        }
        return soln;
    }
}

//Another soln, easy to understand and implement O(n!)
class Solution {
    
    int size;
    int count;
    
    public int totalNQueens(int n) {
        size = n;
        count = 0;
        backtrack(0, new HashSet(), new HashSet(), new HashSet());
        return count;
    }
    
    private void backtrack(int row, Set<Integer> colSet, Set<Integer> diagonalSet, Set<Integer> antiDiagonalSet) {
        if (row == size) {
            count++;
            return;
        }
        for (int col = 0; col < size; col++) {
            int diagonal = row-col;
            int antiDiagonal = row+col;
            if (colSet.contains(col) || diagonalSet.contains(diagonal) || antiDiagonalSet.contains(antiDiagonal)) continue;
            
            colSet.add(col);
            diagonalSet.add(diagonal);
            antiDiagonalSet.add(antiDiagonal);
            
            backtrack(row+1, colSet, diagonalSet, antiDiagonalSet);
            
            colSet.remove(col);
            diagonalSet.remove(diagonal);
            antiDiagonalSet.remove(antiDiagonal);
        }
    }
}

//My Soln
class Solution {
    public int totalNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        int soln = dfs(board, 0, 0);
        return soln;
    }
    
    private int dfs(boolean[][] board, int colIndex, int soln) {
        if (colIndex == board.length) {
            return soln + 1;
        }
        for (int i=0;i<board.length;i++) {
            if (validate(board, i, colIndex)) {
                board[i][colIndex] = true;
                soln = dfs(board, colIndex+1, soln);
                board[i][colIndex] = false;
            }
        }
        return soln;
    }
    
    private boolean validate(boolean[][] board, int x, int y) {
        for (int i=0;i<board.length;i++) {
            for (int j=0;j<y;j++) {
                if (board[i][j] && (x==i || x+j==y+i || x+y==i+j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
