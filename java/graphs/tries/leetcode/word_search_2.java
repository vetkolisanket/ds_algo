/*
212. Word Search II

Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example 1:


Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
Example 2:


Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []
 

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.
*/

//Soln using trie and backtracking TC O(M*4*3^(L-1)) SC O(N) where M is the no. of cells in the board, L is the max length of words, N is the no. of letters in the dictionary
class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap();
    String word = null;
}

class Solution {
    
    char[][] _board = null;
    ArrayList<String> _result = new ArrayList();
    
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (String word: words) {
            TrieNode node = root;
            
            for (Character letter: word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;
        }
        this._board = board;
        
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }
        
        return this._result;
    }
    
    private void backtracking(int row, int col, TrieNode parent) {
        Character letter = this._board[row][col];
        TrieNode currNode = parent.children.get(letter);
        
        if (currNode.word != null) {
            this._result.add(currNode.word);
            currNode.word = null;
        }
        
        this._board[row][col] = '#';
        
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i=0;i<4;++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= _board.length || newCol < 0 || newCol >= _board[0].length) {
                continue;
            }
            if (currNode.children.containsKey(_board[newRow][newCol])) {
                backtracking(newRow, newCol, currNode);
            }
        }
        
        _board[row][col] = letter;
        
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }
}
