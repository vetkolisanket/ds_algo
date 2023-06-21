/*
2448. Minimum Cost to Make Array Equal

You are given two 0-indexed arrays nums and cost consisting each of n positive integers.

You can do the following operation any number of times:

Increase or decrease any element of the array nums by 1.
The cost of doing one operation on the ith element is cost[i].

Return the minimum total cost such that all the elements of the array nums become equal.

 

Example 1:

Input: nums = [1,3,5,2], cost = [2,3,1,14]
Output: 8
Explanation: We can make all the elements equal to 2 in the following way:
- Increase the 0th element one time. The cost is 2.
- Decrease the 1st element one time. The cost is 3.
- Decrease the 2nd element three times. The cost is 1 + 1 + 1 = 3.
The total cost is 2 + 3 + 3 = 8.
It can be shown that we cannot make the array equal with a smaller cost.
Example 2:

Input: nums = [2,2,2,2,2], cost = [4,2,8,1,3]
Output: 0
Explanation: All the elements are already equal, so no operations are needed.
 

Constraints:

n == nums.length == cost.length
1 <= n <= 10^5
1 <= nums[i], cost[i] <= 10^6
*/

//Soln using binary search TC O(NlogK) SC O(1) where N is the no. of elements in the array and K is the difference between the max and the min value of the elements in the array
class Solution {
    public long minCost(int[] nums, int[] cost) {
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        for (int num: nums) {
            left = Math.min(left, num);
            right = Math.max(right, num);
        }
        long answer = 0;
        while (left < right) {
            int mid = (left+right) >> 1;
            long cost1 = getCost(nums, cost, mid);
            long cost2 = getCost(nums, cost, mid+1);
            answer = Math.min(cost1, cost2);
            if (cost1 < cost2) {
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return answer;
    }

    private long getCost(int[] nums, int[] cost, int val) {
        long sum = 0;
        for (int i=0;i<nums.length;i++) {
            sum += 1L * Math.abs(nums[i] - val) * cost[i];
        }
        return sum;
    }
}
