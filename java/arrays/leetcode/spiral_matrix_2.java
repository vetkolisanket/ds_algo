/*
59. Spiral Matrix II

Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

 

Example 1:


Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
Example 2:

Input: n = 1
Output: [[1]]
 

Constraints:

1 <= n <= 20
*/

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int x = 1;
        int up = 0, left = 0, right = n-1, down = n-1;
        while (up <= down && left <= right) {
            for(int i = left; i <= right; i++)
                res[up][i] = x++;
            up++;
            
            for (int i = up; i <= down; i++)
                res[i][right] = x++;
            right--;
            
            for (int i = right; i >= left; i--)
                res[down][i] = x++;
            down--;
            
            for (int i = down; i >= up; i--)
                res[i][left] = x++;
            left++;
        }
        return res;
    }
}
