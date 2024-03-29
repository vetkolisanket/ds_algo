/*
37. Sudoku Solver

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

 

Example 1:


Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:


 

Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.
*/

//Faster soln
class Solution {
    public void solveSudoku(char[][] board) {
        solve(board, 0, 0);
    }
    
    private boolean solve(char[][] board, int row, int col) {
        if (col == board[0].length) {
            col = 0;
            row++;
        }
        
        if (row == board.length) return true;
        
        if (board[row][col] != '.') return solve(board, row, col+1);
        for (char num = '1'; num <= '9'; ++num) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;
                    
                if (solve(board, row, col)) return true;
                else board[row][col] = '.';
            }
        }
        return false;
    }
    
    private boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
        }
        
        int boxRow = row/3*3;
        int boxCol = col/3*3;
        for (int i = boxRow; i < boxRow+3; ++i) {
            for (int j = boxCol; j < boxCol+3; ++j) {
                if (board[i][j] == num) return false;
            }
        }
        return true;
    }
    
}

//Original soln
class Solution {
    public void solveSudoku(char[][] board) {
        solve(board, 0, 0);
    }
    
    private boolean solve(char[][] board, int row, int col) {
        for (int i = row; i < 9; i++, col = 0) {
            for (int j = col; j < 9; j++) {
                if ('.' == board[i][j]) {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, i, j, num)) {
                            board[i][j] = num;
                            
                            if (solve(board, i, j+1)) return true;
                            else board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValid(char[][] board, int row, int col, char num) {
        int blkRow = (row/3)*3;
        int blkCol = (col/3)*3;
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num || board[blkRow + i/3][blkCol + i%3] == num) return false;
        }
        return true;
    }
    
}
