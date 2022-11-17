/*
223. Rectangle Area

Given the coordinates of two rectilinear rectangles in a 2D plane, return the total area covered by the two rectangles.

The first rectangle is defined by its bottom-left corner (ax1, ay1) and its top-right corner (ax2, ay2).

The second rectangle is defined by its bottom-left corner (bx1, by1) and its top-right corner (bx2, by2).

 

Example 1:

Rectangle Area
Input: ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
Output: 45
Example 2:

Input: ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
Output: 16
 

Constraints:

-10^4 <= ax1 <= ax2 <= 10^4
-10^4 <= ay1 <= ay2 <= 10^4
-10^4 <= bx1 <= bx2 <= 10^4
-10^4 <= by1 <= by2 <= 10^4

*/

//A simpler and more readable soln
class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int areaOfA = (ax2 - ax1) * (ay2 - ay1);
        int areaOfB = (bx2 - bx1) * (by2 - by1);
        
        int overlap = 0;
        
        int left = Math.max(ax1, bx1);
        int right = Math.min(ax2, bx2);
        int length = right - left;
        
        int bottom = Math.max(ay1, by1);
        int top = Math.min(ay2, by2);
        int breadth = top - bottom;
        
        if (length > 0 && breadth > 0) {
            overlap = length * breadth;
        }
        return areaOfA + areaOfB - overlap;
    }
}

//My soln TC O(1) SC O(1)
class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        return getArea(ax1, ay1, ax2, ay2) + getArea(bx1, by1, bx2, by2) - getOverlappingArea(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);
    }
    
    private int getArea(int x1, int y1, int x2, int y2) {
        int length = Math.abs(x2-x1);
        int breadth = Math.abs(y2 - y1);
        return length*breadth;
    }
    
    private int getOverlappingArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        if (((ax1 >= bx1 && ax1 <= bx2) || (ax2 >= bx1 && ax2 <= bx2) || (bx1 >= ax1 && bx1 <= ax2) || (bx2 >= ax1 && bx2 <= ax2)) && ((ay1 >= by1 && ay1 <= by2) || (ay2 >= by1 && ay2 <= by2) || (by1 >= ay1 && by1 <= ay2) || (by2 >= ay1 && by2 <= ay2))) {
            int[] xArr = new int[]{ax1, ax2, bx1, bx2};
            int[] yArr = new int[]{ay1, ay2, by1, by2};
            Arrays.sort(xArr);
            Arrays.sort(yArr);
            return (xArr[2] - xArr[1]) * (yArr[2] - yArr[1]);
        }
        return 0;
    }
}
