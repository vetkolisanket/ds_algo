/*
976. Largest Perimeter Triangle

Given an integer array nums, return the largest perimeter of a triangle with a non-zero area, formed from three of these lengths. If it is impossible to form any triangle of a non-zero area, return 0.

 

Example 1:

Input: nums = [2,1,2]
Output: 5
Example 2:

Input: nums = [1,2,1]
Output: 0
 

Constraints:

3 <= nums.length <= 10^4
1 <= nums[i] <= 10^6
*/

//My soln TC O(nlog(n)) SC O(1)
class Solution {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length-1;i>=2;i--) {
            int a = nums[i];
            int b = nums[i-1];
            int c = nums[i-2];
            if (a < b+c) return a+b+c;
        }
        return 0;
    }
}
