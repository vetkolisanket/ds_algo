/*
2009. Minimum Number of Operations to Make Array Continuous

You are given an integer array nums. In one operation, you can replace any element in nums with any integer.

nums is considered continuous if both of the following conditions are fulfilled:

All elements in nums are unique.
The difference between the maximum element and the minimum element in nums equals nums.length - 1.
For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.

Return the minimum number of operations to make nums continuous.

 

Example 1:

Input: nums = [4,2,5,3]
Output: 0
Explanation: nums is already continuous.
Example 2:

Input: nums = [1,2,3,5,6]
Output: 1
Explanation: One possible solution is to change the last element to 4.
The resulting array is [1,2,3,5,4], which is continuous.
Example 3:

Input: nums = [1,10,100,1000]
Output: 3
Explanation: One possible solution is to:
- Change the second element to 2.
- Change the third element to 3.
- Change the fourth element to 4.
The resulting array is [1,2,3,4], which is continuous.
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9

*/

//Soln using sliding window TC O(NlogN) SC O(N)
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length, ans = n;
        Set<Integer> set = new HashSet();
        for (int num: nums) set.add(num);
        int[] newNums = new int[set.size()];
        int i=0;
        for (int num: set) {
            newNums[i++] = num;
        }
        Arrays.sort(newNums);
        int j=0;
        for (i=0;i<newNums.length;i++) {
            while (j < newNums.length && newNums[j] <= newNums[i]+n-1) {
                j++;
            }
            int count = j-i;
            ans = Math.min(ans, n-count);
        }
        return ans;
    }
}
