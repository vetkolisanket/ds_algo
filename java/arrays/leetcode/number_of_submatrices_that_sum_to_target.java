/*
1074. Number of Submatrices That Sum to Target

Given a matrix and a target, return the number of non-empty submatrices that sum to target.

A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.

Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.

 

Example 1:


Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
Explanation: The four 1x1 submatrices that only contain 0.
Example 2:

Input: matrix = [[1,-1],[-1,1]], target = 0
Output: 5
Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
Example 3:

Input: matrix = [[904]], target = 0
Output: 0
 

Constraints:

1 <= matrix.length <= 100
1 <= matrix[0].length <= 100
-1000 <= matrix[i] <= 1000
-10^8 <= target <= 10^8

*/


//TC O(R^2C) SC O(RC)
class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int r = matrix.length, c = matrix[0].length;
        int[][] ps = new int[r+1][c+1];
        for (int i=1;i<=r;i++) {
            for (int j=1;j<=c;j++) {
                ps[i][j] = ps[i-1][j] + ps[i][j-1] - ps[i-1][j-1] + matrix[i-1][j-1];
            }
        }
        int count = 0;
        Map<Integer, Integer> map = new HashMap();
        for (int r1=1;r1<=r;r1++) {
            for (int r2=r1;r2<=r;r2++) {
                map.clear();
                map.put(0, 1);
                for (int c1=1;c1<=c;c1++) {
                    int curSum = ps[r2][c1] - ps[r1-1][c1];
                    count += map.getOrDefault(curSum-target, 0);
                    map.put(curSum, map.getOrDefault(curSum, 0) + 1);
                }
            }
        }
        return count;
    }
}
