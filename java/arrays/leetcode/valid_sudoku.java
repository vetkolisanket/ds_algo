/*
36. Valid Sudoku

Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
 

Example 1:


Input: board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true
Example 2:

Input: board = 
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 

Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.
*/

//Faster soln
class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    if (!isValidSudoku(board, i, j, board[i][j])) return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValidSudoku(char[][] board, int i, int j, char c) {
        for (int row = i+1; row < 9; row++) {
            if (board[row][j] == c) return false;
        }
        
        for (int col = j+1; col < 9; col++) {
            if (board[i][col] == c) return false;
        }
        
        int row = i - i%3;
        int col = j - j%3;
        for (int x = row; x < row+3; x++) {
            for (int y = col; y < col+3; y++) {
                if (board[x][y] == c && x != i && y != j) return false;
            }
        }
        return true;
    }
}


//Much readable soln
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set set = new HashSet();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    if (!set.add(c + " in row " + i) ||
                       !set.add(c + " in column " + j) ||
                       !set.add(c + " in grid " + i/3 + "-" + j/3)) return false;
                }
            }
        }
        return true;
    }
}


//My soln
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, List<Character>> rowMap = new HashMap();
        Map<Integer, List<Character>> colMap = new HashMap();
        Map<Integer, List<Character>> gridMap = new HashMap();
        for (int i = 0; i < 9; i++) {
            rowMap.put(i, new ArrayList());
            colMap.put(i, new ArrayList());
            gridMap.put(i, new ArrayList());
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    if (rowMap.get(i).contains(c)) return false;
                    else rowMap.get(i).add(c);
                    if (colMap.get(j).contains(c)) return false;
                    else colMap.get(j).add(c);
                    int gridNo = getGridNo(i, j);
                    if (gridMap.get(gridNo).contains(c)) return false;
                    else gridMap.get(gridNo).add(c);
                }
            }
        }
        return true;
    }
    
    private int getGridNo(int i, int j) {
        return i/3 + (j/3)*3;
    }
}
