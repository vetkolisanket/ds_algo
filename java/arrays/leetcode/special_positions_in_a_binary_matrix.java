/*
1582. Special Positions in a Binary Matrix

Given an m x n binary matrix mat, return the number of special positions in mat.

A position (i, j) is called special if mat[i][j] == 1 and all other elements in row i and column j are 0 (rows and columns are 0-indexed).

 

Example 1:


Input: mat = [[1,0,0],[0,0,1],[1,0,0]]
Output: 1
Explanation: (1, 2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.
Example 2:


Input: mat = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3
Explanation: (0, 0), (1, 1) and (2, 2) are special positions.
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 100
mat[i][j] is either 0 or 1.
*/

//My soln TC O(MN) SC O(M+N)
class Solution {
    public int numSpecial(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] rowSum = new int[m], colSum = new int[n];
        for (int i=0;i<m;i++) {
            int sum = 0;
            for (int j=0;j<n;j++) {
                sum += mat[i][j];
            }
            rowSum[i] = sum;
        }
        for (int i=0;i<n;i++) {
            int sum = 0;
            for (int j=0;j<m;j++) {
                sum += mat[j][i];
            }
            colSum[i] = sum;
        }
        int ans = 0;
        for (int i=0;i<m;i++) {
            if (rowSum[i] != 1) continue;
            for (int j=0;j<n;j++) {
                if (colSum[j] == 1 && mat[i][j] == 1) ans++;
            }
        }
        return ans;
    }
}

//A slightly more optimised soln TC O(MN) SC O(M+N)
class Solution {
    public int numSpecial(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] rowSum = new int[m], colSum = new int[n];
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (mat[i][j] == 1) {
                    rowSum[i]++;
                    colSum[j]++;
                }
            }
        }
        int ans = 0;
        for (int i=0;i<m;i++) {
            if (rowSum[i] != 1) continue;
            for (int j=0;j<n;j++) {
                if (colSum[j] == 1 && mat[i][j] == 1) ans++;
            }
        }
        return ans;
    }
}
