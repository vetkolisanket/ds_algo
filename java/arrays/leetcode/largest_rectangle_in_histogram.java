/*
84. Largest Rectangle in Histogram

Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

 

Example 1:


Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
Example 2:


Input: heights = [2,4]
Output: 4
 

Constraints:

1 <= heights.length <= 105
0 <= heights[i] <= 104
*/

//Faster and easier to understand soln

class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int[] lessFromLeft = new int[heights.length];
        int[] lessFromRight = new int[heights.length];
        lessFromRight[heights.length-1] = heights.length;
        lessFromLeft[0] = -1;
        
        for (int i=1; i<heights.length; i++) {
            int p=i-1;
            while(p >= 0 && heights[p] >= heights[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }
        
        for (int i=heights.length-2; i>=0; i--) {
            int p=i+1;
            while (p < heights.length && heights[p] >= heights[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }
        
        int maxArea = 0;
        for (int i=0; i< heights.length; i++) {
            maxArea = Math.max(maxArea, heights[i]*(lessFromRight[i] - lessFromLeft[i] - 1));
        }
        return maxArea;
    }
}

//Stack soln

public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= len;) {
            int h = (i == len ? 0 : heights[i]);
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            }else {
                int curHeight = heights[stack.pop()];
                int rightBoundary = i - 1;
                int leftBoundary = stack.isEmpty() ? 0 : stack.peek() + 1;
                int width = rightBoundary - leftBoundary + 1;
                maxArea = Math.max(maxArea, (curHeight * width));
            }
        }
        return maxArea;
    }
