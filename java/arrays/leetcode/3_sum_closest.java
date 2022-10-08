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

/*
For an interview, we recommend focusing on the Two Pointers approach above. It's easier to get it right and adapt for other variations of 3Sum. 
Interviewers love asking follow-up problems like 3Sum Smaller!
If an interviewer asks you whether you can achieve O(1) memory complexity, you can use the selection sort instead of a built-in sort in the Two 
Pointers approach. It will make it a bit slower, though the overall time complexity will be still O(n^2).
*/

//TC O(n^2) SC O(log(n)) to O(n) depending on the sorting algo used underneath Arrays.sort() which is usually a variation of quick sort
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
