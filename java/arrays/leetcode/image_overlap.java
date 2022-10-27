/*
835. Image Overlap

You are given two images, img1 and img2, represented as binary, square matrices of size n x n. A binary matrix has only 0s and 1s as values.

We translate one image however we choose by sliding all the 1 bits left, right, up, and/or down any number of units. We then place it on top of the other image. We can then calculate the overlap by counting the number of positions that have a 1 in both images.

Note also that a translation does not include any kind of rotation. Any 1 bits that are translated outside of the matrix borders are erased.

Return the largest possible overlap.

 

Example 1:


Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
Output: 3
Explanation: We translate img1 to right by 1 unit and down by 1 unit.

The number of positions that have a 1 in both images is 3 (shown in red).

Example 2:

Input: img1 = [[1]], img2 = [[1]]
Output: 1
Example 3:

Input: img1 = [[0]], img2 = [[0]]
Output: 0
 

Constraints:

n == img1.length == img1[i].length
n == img2.length == img2[i].length
1 <= n <= 30
img1[i][j] is either 0 or 1.
img2[i][j] is either 0 or 1.

*/

//Soln TC O(N^4) SC O(1)
class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int maxOverlaps = 0, n = img1.length;
        
        for (int yShift = 0; yShift < n; ++yShift) {
            for (int xShift = 0; xShift < n; ++xShift) {
                maxOverlaps = Math.max(maxOverlaps, check(xShift, yShift, img1, img2));
                maxOverlaps = Math.max(maxOverlaps, check(xShift, yShift, img2, img1));
            }
        }
        return maxOverlaps;
    }
    
    private int check(int xShift, int yShift, int[][] img1, int[][] img2) {
        int leftShiftCount = 0, rightShiftCount = 0, n = img1.length;
        int row1 = 0;
        for (int row2 = yShift; row2 < n; ++row2) {
            int col1 = 0;
            for(int col2 = xShift; col2 < n; ++col2) {
                if (img1[row1][col1] == 1 && img2[row2][col2] == 1) {
                    leftShiftCount++;
                }
                if (img1[row1][col2] == 1 && img2[row2][col1] == 1) {
                    rightShiftCount++;
                }
                col1++;
            }
            row1++;
        }
        return Math.max(leftShiftCount, rightShiftCount);
    }
}
