/*
11. Container With Most Water

Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.

Notice that you may not slant the container. 

Example 1:

Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
Example 2:

Input: height = [1,1]
Output: 1
Example 3:

Input: height = [4,3,2,1,4]
Output: 16
Example 4:

Input: height = [1,2,1]
Output: 2
 

Constraints:

n == height.length
2 <= n <= 105
0 <= height[i] <= 104

*/

class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0;
        int j = height.length-1;
        while (i != j){
            int min = height[i] < height[j] ? height[i] : height[j];
            int area = min*(j-i);
            if (area > maxArea) maxArea = area;
            if (min == height[i]) i++;
            else j--;
        }
        return maxArea;
    }
}

/*
The O(n) solution with proof by contradiction doesn't look intuitive enough to me. Before moving on, read any example of the algorithm first if you don't know it yet.

Here's another way to see what happens in a matrix representation:

Draw a matrix where rows correspond to the position of the left line, and columns corresponds to the position of the right line.

For example, say n=6. Element at (2,4) would corresponds to the case where the left line is at position 2 and the right line is at position 4. The value of the element is the volume for the case.

In the figures below, x means we don't need to compute the volume for that case, because:

on the diagonal, the two lines are overlapped;
the lower left triangle area of the matrix, the two lines are switched and the case is symmetric to the upper right area.
We start by computing the volume at (1,6), denoted by o. Now if the left line is shorter than the right line, then moving the right line towards left would only decrease the volume, so all the elements left to (1,6) on the first row have smaller volume. Therefore, we don't need to compute those cases (crossed by ---).

  1 2 3 4 5 6
1 x ------- o
2 x x
3 x x x 
4 x x x x
5 x x x x x
6 x x x x x x
So we can only move the left line towards right to 2 and compute (2,6). Now if the right line is shorter, all cases below (2,6) are eliminated.

  1 2 3 4 5 6
1 x ------- o
2 x x       o
3 x x x     |
4 x x x x   |
5 x x x x x |
6 x x x x x x
And no matter how this o path goes, we end up only need to find the max value on this path, which contains n-1 cases.

  1 2 3 4 5 6
1 x ------- o
2 x x - o o o
3 x x x o | |
4 x x x x | |
5 x x x x x |
6 x x x x x x
Hope this helps. I feel more comfortable seeing things this way.
*/
