/*
42. Trapping Rain Water
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:

Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9
 
Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
*/

class Solution {
    public int trap(int[] height) {
        int ans = 0;
        int max_i = 0;
        int len = height.length;
        for (int i=0;i<len;i++) {
            if (height[i]>height[max_i]) max_i = i;
        }
        int left_max = 0;
        for (int i=0;i<max_i;i++) {
            left_max = Math.max(height[i], left_max);
            ans += left_max - height[i];
        }
        int right_max = 0;
        for (int i=len-1;i>max_i;i--) {
            right_max = Math.max(height[i], right_max);
            ans += right_max - height[i];
        }
        return ans;
    }
}