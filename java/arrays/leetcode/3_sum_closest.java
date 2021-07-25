/*
16. 3Sum Closest

Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

 

Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 

Constraints:

3 <= nums.length <= 10^3
-10^3 <= nums[i] <= 10^3
-10^4 <= target <= 10^4
*/

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum=0;
        int diff = Integer.MAX_VALUE;
        int length = nums.length;
        for(int i=0;i<length-2;i++){
            int l = i+1;
            int h = length-1;
            while (l<h) {
                int sum = nums[i] + nums[l] + nums[h];
                if (sum == target){
                    return sum;
                } else if (sum < target) l++;
                else h--;
                if(diff>Math.abs(sum-target)) {
                    diff = Math.abs(sum-target);
                    closestSum = sum;
                }
            }
        }
        return closestSum;
    }
}
