/*
209. Minimum Size Subarray Sum

Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.

 

Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0
 

Constraints:

1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 105
 

Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
*/

//An O(n) soln
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;
        for (int i=0;i<nums.length;i++) {
            sum += nums[i];
            while (sum >= target) {
                ans = Math.min(ans, i+1-left);
                sum -= nums[left++];
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}

//My soln O(nlog(n)) time and O(n) space complexity
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int[] arr = new int[nums.length];
        TreeMap<Integer, Integer> map = new TreeMap();
        map.put(0, 0);
        arr[0] = nums[0];
        map.put(nums[0], 1);
        for (int i=1;i<nums.length;i++) {
            arr[i] = arr[i-1]+nums[i];
            map.put(arr[i], i+1);
        }
        for (int i=nums.length-1;i>=0;i--) {
            int val = arr[i];
            Map.Entry<Integer, Integer> entry = map.floorEntry(val-target);
            if (entry != null) {
                ans = Math.min(ans, i-entry.getValue()+1);
            } else break;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
