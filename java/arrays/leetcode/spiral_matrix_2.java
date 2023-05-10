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

//Soln from an attempt TC O(N*N) SC O(1)
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int val = 1, left = 0, right = n-1, top = 0, bottom = n-1;
        while (val <= n*n) {
            for (int i=left;i<=right && val <= n*n;i++) {
                ans[top][i] = val++;
            }
            top++;
            for (int i=top;i<=bottom && val <= n*n;i++) {
                ans[i][right] = val++;
            }
            right--;
            for (int i=right;i>=left && val <= n*n;i--) {
                ans[bottom][i] = val++;
            }
            bottom--;
            for (int i=bottom;i>=top && val <= n*n;i--) {
                ans[i][left] = val++;
            }
            left++;
        }
        return ans;
    }
}

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

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];
        int left=0, top=0, right=n-1, bottom=n-1;
        int val = 1;
        int n2 = n*n;
        while  (val <= n2) {
            for (int i=left;i<=right && val <= n2;i++) {
                arr[top][i] = val++;
            }
            for (int i=top+1;i<bottom && val <= n2;i++) {
                arr[i][right] = val++;
            }
            for (int i=right;i>=left && val <= n2;i--) {
                arr[bottom][i] = val++;
            }
            for(int i=bottom-1;i>top && val <= n2;i--) {
                arr[i][left] = val++;
            }
            left++; top++; right--; bottom--;
        }
        return arr;
    }
}
