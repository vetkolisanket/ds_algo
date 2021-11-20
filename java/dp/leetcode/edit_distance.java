/*
72. Edit Distance

Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character
 

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 

Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
*/

//Top Down Approach
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null) return 0;
        if (word1 == null || word1.length() == 0) return word2.length();
        if (word2 == null || word2.length() == 0) return word1.length();
        
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();
        
        int[][] cache = new int[c1.length][c2.length];
        
        for (int i=0; i<c1.length; i++) {
            for (int j=0; j<c2.length; j++) {
                cache[i][j] = -1;
            }
        }
        
        return minDistance(c1, c2, 0, 0, cache);
    }
    
    private int minDistance(char[] c1, char[] c2, int i, int j, int[][] cache) {
        if (c1.length == i) return c2.length - j;
        if (c2.length == j) return c1.length - i;
        
        if (cache[i][j] != -1) return cache[i][j];
        
        if (c1[i] == c2[j]) {
            cache[i][j] = minDistance(c1, c2, i+1, j+1, cache);
        } else {
            int insert = minDistance(c1, c2, i, j+1, cache);
            int delete = minDistance(c1, c2, i+1, j, cache);
            int replace = minDistance(c1, c2, i+1, j+1, cache);
            cache[i][j] = Math.min(Math.min(insert, delete), replace) + 1;
        }
        return cache[i][j];
    }
}

//Bottom Up Approach
public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return -1;
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();
        
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();
        
        int[][] matched = new int[c1.length + 1][c2.length + 1];
        //matched[length of c1 already been matched][length of c2 already been matched]
        
        for (int i = 0; i <= c1.length; i++) {
            matched[i][0] = i;
        }
        for (int j = 0; j <= c2.length; j++) {
            matched[0][j] = j;
        }
        
        for (int i = 0; i < c1.length; i++) {
            for (int j = 0; j < c2.length; j++) {
                if (c1[i] == c2[j]) {
                    matched[i + 1][j + 1] = matched[i][j];
                } else {
                    matched[i + 1][j + 1] = Math.min(Math.min(matched[i][j + 1], matched[i + 1][j]), matched[i][j]) + 1;
                    //Since it is bottom up, we are considering in the ascending order of indexes.
                    //Insert means plus 1 to j, delete means plus 1 to i, replace means plus 1 to both i and j. 
                    //above sequence is delete, insert and replace. 
                }
            }
        }
        
        return matched[c1.length][c2.length];
    }
