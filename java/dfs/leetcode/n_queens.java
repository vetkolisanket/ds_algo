/*
51. N-Queens

The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

Example 1:


Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
Example 2:

Input: n = 1
Output: [["Q"]]
 

Constraints:

1 <= n <= 9
*/

//An easy to understand soln. O(n!) time complexity O(n^2) space complexity
class Solution {
    
    int size;
    List<List<String>> solution = new ArrayList();
    
    public List<List<String>> solveNQueens(int n) {
        size = n;
        char[][] board = new char[n][n];
        for (char[] row: board) {
            Arrays.fill(row, '.');
        }
        backtrack(0, new HashSet(), new HashSet(), new HashSet(), board);
        return solution;
    }
    
    private void backtrack(int row, Set<Integer> colSet, Set<Integer> diagonalSet, Set<Integer> antiDiagonalSet, char[][] board) {
        if (row == size) {
            solution.add(createBoard(board));
            return;
        }
        for (int col=0;col<size;col++) {
            int diagonal = row-col;
            int antiDiagonal = row+col;
            
            if (colSet.contains(col) || diagonalSet.contains(diagonal) || antiDiagonalSet.contains(antiDiagonal)) continue;
            
            colSet.add(col);
            diagonalSet.add(diagonal);
            antiDiagonalSet.add(antiDiagonal);
            board[row][col] = 'Q';
            
            backtrack(row+1, colSet, diagonalSet, antiDiagonalSet, board);
            
            board[row][col] = '.';
            antiDiagonalSet.remove(antiDiagonal);
            diagonalSet.remove(diagonal);
            colSet.remove(col);
        }
    }
    
    private List<String> createBoard(char[][] board) {
        List<String> list = new ArrayList();
        for (char[] row: board) {
            list.add(new String(row));
        }
        return list;
    }
}

//Another soln
class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        List<List<String>> res = new ArrayList();
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                board[i][j] = '.';
            }
        }
        dfs(board, 0, res);
        return res;
    }
    
    private void dfs(char[][] board, int colIndex, List<List<String>> res) {
        if (colIndex == board.length) {
            res.add(construct(board));
        } else {
            for (int i=0;i<board.length;i++) {
                if (validate(board, i, colIndex)) {
                    board[i][colIndex] = 'Q';
                    dfs(board, colIndex+1, res);
                    board[i][colIndex] = '.';
                }
            }
        }
    }
    
    private boolean validate(char[][] board, int x, int y) {
        for (int i=0;i<board.length;i++) {
            for (int j=0;j<y;j++) {
                if (board[i][j] == 'Q' && (x+j == y+i || x+y == i+j || x == i)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private List<String> construct(char[][] board) {
        List<String> list = new ArrayList();
        for (int i=0;i<board.length;i++) {
            list.add(new String(board[i]));
        }
        return list;
    }
}
