/*
79. Word Search

Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

 

Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false
 

Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
 

Follow up: Could you use search pruning to make your solution faster with a larger board?
*/

//Soln using backtracking TC O(N*3^L) SC O(L) where N is the no. of cells in the board, L is the length of the word. It is 3^L instead of 4^L because 
//after the first cell, all cells will have 3 options to travel to as they would have come to the current cell via some parent cell
class Solution {
    public boolean exist(char[][] board, String word) {
        if (board.length == 0) return false;
        for (int i=0;i<board.length;i++) {
            for (int j=0;j<board[0].length;j++) {
                if (exist(board, word, i, j, 0)) return true;
            }
        }
        return false;
    }
    
    private boolean exist(char[][] board, String word, int i, int j, int pos) {
        if (pos == word.length()) return true;
        if (i<0 || j<0 || i==board.length || j==board[0].length || board[i][j] != word.charAt(pos)) return false;
        
        board[i][j] = '*';
        boolean exist = exist(board, word, i+1, j, pos+1) ||
            exist(board, word, i-1, j, pos+1) ||
            exist(board, word, i, j+1, pos+1) ||
            exist(board, word, i, j-1, pos+1);
        board[i][j] = word.charAt(pos);
        return exist;
    }
}
