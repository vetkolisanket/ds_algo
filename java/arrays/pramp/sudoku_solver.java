/*
Sudoku Solver
Write the function sudokuSolve that checks whether a given sudoku board (i.e. sudoku puzzle) is solvable. If so, the function will returns true. Otherwise (i.e. there is no valid solution to the given sudoku board), returns false.

In sudoku, the objective is to fill a 9x9 board with digits so that each column, each row, and each of the nine 3x3 sub-boards that compose the board contains all of the digits from 1 to 9. The board setter provides a partially completed board, which for a well-posed board has a unique solution. As explained above, for this problem, it suffices to calculate whether a given sudoku board has a solution. No need to return the actual numbers that make up a solution.

A sudoku board is represented as a two-dimensional 9x9 array of the characters ‘1’,‘2’,…,‘9’ and the '.' character, which represents a blank space. The function should fill the blank spaces with characters such that the following rules apply:

In every row of the array, all characters ‘1’,‘2’,…,‘9’ appear exactly once.
In every column of the array, all characters ‘1’,‘2’,…,‘9’ appear exactly once.
In every 3x3 sub-board that is illustrated below, all characters ‘1’,‘2’,…,‘9’ appear exactly once.
A solved sudoku is a board with no blank spaces, i.e. all blank spaces are filled with characters that abide to the constraints above. If the function succeeds in solving the sudoku board, it’ll return true (false, otherwise).

A typical Sudoku board setter

altThe same board with solution numbers marked in red

Write a readable an efficient code, explain how it is built and why you chose to build it that way.
*/

import java.io.*;
import java.util.*;

class Solution {

  static boolean sudokuSolve(char[][] board) {
    // your code goes here
    HashSet<Character>[] rowSets = new HashSet[9];
    HashSet<Character>[] colSets = new HashSet[9];
    HashSet<Character>[] boardSets = new HashSet[9];
    
    for (int i=0;i<9;i++) {
      rowSets[i] = new HashSet<Character>();
      colSets[i] = new HashSet<Character>();
      boardSets[i] = new HashSet<Character>();
    }
    
    for (int i=0;i<9;i++) {
      for (int j=0;j<9;j++) {
        if (board[i][j] != '.') {
          char c = board[i][j];
          int boardIdx = (3*(i/3)) + (j/3);
          if (rowSets[i].contains(c)) return false;
          rowSets[i].add(c);
          if (colSets[j].contains(c)) return false;
          colSets[j].add(c);
          if (boardSets[boardIdx].contains(c)) return false;
          boardSets[boardIdx].add(c);
        }
      }
    }
    
    HashSet<Character> set = new HashSet<Character>();
    for (int i=0;i<9;i++) {
      for (int j=0;j<9;j++) {
        if (board[i][j] == '.') {
          int boardIdx = (3*(i/3)) + (j/3);
          set.clear();
          for (char c : rowSets[i]) {
            set.add(c);
          }
          for (char c : colSets[j]) {
            set.add(c);
          }
          for (char c : boardSets[boardIdx]) {
            set.add(c);
          }
          if (set.size() == 9) return false;
        }
      }
    }
    return true;
    
  }

  public static void main(String[] args) {

  }
  
}
