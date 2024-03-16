/*
525. Contiguous Array

Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.

 

Example 1:

Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
Example 2:

Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 

Constraints:

1 <= nums.length <= 10^5
nums[i] is either 0 or 1.

*/

//Soln using hashmap TC O(N) SC O(N)
class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        int ans = 0, count = 0;
        for (int i=0;i<nums.length;i++) {
            if (nums[i] == 0) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                ans = Math.max(ans, i+1);
            } else if (map.containsKey(count)) {
                ans = Math.max(ans, i-map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return ans;
    }
}
