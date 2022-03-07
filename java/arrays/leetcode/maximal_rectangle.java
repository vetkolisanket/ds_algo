/*
85. Maximal Rectangle

Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 

Example 1:


Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.
Example 2:

Input: matrix = []
Output: 0
Example 3:

Input: matrix = [["0"]]
Output: 0
Example 4:

Input: matrix = [["1"]]
Output: 1
Example 5:

Input: matrix = [["0","0"]]
Output: 0
 

Constraints:

rows == matrix.length
cols == matrix[i].length
0 <= row, cols <= 200
matrix[i][j] is '0' or '1'.
*/

class Solution {
    public int maxArea(int M[][], int n, int m) {
        // add code here.
        //Array to track height so far at each index of the row, height[i] == j
        //implies j contiguous rows have value 1 at index j
        int[] height = new int[m];
        //Array to track the starting index for each height index, left[i] == j
        //implies starting index of height[i] is j
        int[] left = new int[m];
        //Array to track the ending position (index+1) of each height index,
        //right[i] == j implies ending position of height[i] is j
        int[] right = new int[m];
        //Populating the right array with m, this is to imply the ending index of
        //any height is m
        Arrays.fill(right, m);
        int maxArea = 0;
        for (int i=0;i<n;i++) {
            //Variable to store the starting index of current height
            int curLeft = 0 
            //Variable to store the ending index of current height
            int curRight = m;
            for (int j=0;j<m;j++) {
                //If current cell is 1, increment the height
                if (M[i][j] == 1) height[j]++;
                //Else reset the height to 0
                else height[j] = 0;
            }
            
            for (int j=0;j<m;j++) {
                //If current cell is 1, starting index for height[j] will be 
                //max of previous rows starting index for column j or current
                //rows starting index for height[j]
                if (M[i][j] == 1) left[j] = Math.max(left[j], curLeft);
                else {
                    //else resetting left[j] to 0 for next rows computation
                    left[j] = 0;
                    //current cell was 0, so maybe the next column could contain
                    //1 hence updating curLeft to next column i.e. j+1
                    curLeft = j+1;
                }
            }
            
            for (int j=m-1;j>=0;j--) {
                //If current cell is 1, ending index for height[j] will be min of
                //previous rows ending index for column j or current rows ending
                //index for height[j]
                if (M[i][j] == 1) right[j] = Math.min(right[j], curRight);
                else {
                    //else resetting right[j] to m for next rows computation
                    right[j] = m;
                    //current cell was 0, so maybe the next position(index+1)
                    //could contain 1 hence updating curRight to j
                    curRight = j;
                }
            }
            
            for (int j=0;j<m;j++) {
                //right[j]-left[j] gives the no. of cols having height[j] so far
                maxArea = Math.max(maxArea, (right[j] - left[j])*height[j]);
            }
        }
        return maxArea;
    }
}

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] height = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        int maxArea = 0;
        Arrays.fill(right, n);
        for (int i=0;i<m;i++) {
            int curLeft = 0;
            int curRight = n;
            
            for(int j=0;j<n;j++) {
                if (matrix[i][j]=='1') {
                    height[j]++;
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    height[j] = 0;
                    left[j] = 0;
                    curLeft = j+1;
                }
            }
            
            for (int j=n-1;j>=0;j--) {
                if (matrix[i][j]=='1') right[j] = Math.min(right[j], curRight);
                else {
                    right[j] = n;
                    curRight = j;
                }
            }
            
            for (int j=0;j<n;j++) {
                maxArea = Math.max(maxArea, height[j]*(right[j]-left[j]));
            }
        }
    
        return maxArea;
    }
}
