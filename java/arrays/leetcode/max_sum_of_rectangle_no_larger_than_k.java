/*
363. Max Sum of Rectangle No Larger Than K

Given an m x n matrix matrix and an integer k, return the max sum of a rectangle in the matrix such that its sum is no larger than k.

It is guaranteed that there will be a rectangle with a sum no larger than k.

 

Example 1:


Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2
Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).
Example 2:

Input: matrix = [[2,2,-1]], k = 3
Output: 3
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-100 <= matrix[i][j] <= 100
-10^5 <= k <= 10^5
 

Follow up: What if the number of rows is much larger than the number of columns?
*/

//Soln TC O(m^2nlog(n)) SC O(n) where m, n are no. of rows, cols respectively int the matrix
class Solution {
    
    int result = Integer.MIN_VALUE;
    
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int[] rowSum = new int[matrix[0].length];
        for (int i=0;i<matrix.length;i++) {
            Arrays.fill(rowSum, 0);
            for (int row=i;row<matrix.length;row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    rowSum[col] += matrix[row][col];
                }
                updateResult(rowSum, k);
                if (result == k) {
                    return result;
                }
            }
        }
        return result;
    }
    
    private void updateResult(int[] nums, int k) {
        int sum = 0;
        TreeSet<Integer> sortedSum = new TreeSet();
        sortedSum.add(0);
        for (int num: nums) {
            sum += num;
            Integer x = sortedSum.ceiling(sum - k);
            if (x != null) {
                result = Math.max(result, sum - x);
            }
            sortedSum.add(sum);
        }
    }
}
