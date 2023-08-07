/*
74. Search a 2D Matrix

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
 

Example 1:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
Example 2:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 10^4
*/

//Soln using advanced binary search TC O(logMN) SC O(1)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int l = 0, r = m*n-1;
        while (l != r) {
            int mid = (l+r-1) >> 1;
            if (matrix[mid/n][mid%n] < target) l = mid+1;
            else r = mid;
        }
        return matrix[l/n][l%n] == target;
    }
}
