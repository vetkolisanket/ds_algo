/*
1695. Maximum Erasure Value

You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.

Return the maximum score you can get by erasing exactly one subarray.

An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).

 

Example 1:

Input: nums = [4,2,4,5,6]
Output: 17
Explanation: The optimal subarray here is [2,4,5,6].
Example 2:

Input: nums = [5,2,1,2,5,2,1,2,5]
Output: 8
Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^4
*/

//My soln
class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i=1;i<nums.length;i++) {
            prefixSum[i] = nums[i] + prefixSum[i-1];
        }
        Map<Integer, Integer> map = new HashMap();
        int[] arr = new int[nums.length];
        map.put(nums[0], 0);
        for (int i=1;i<nums.length;i++) {
            if (map.containsKey(nums[i])) {
                arr[i] = Math.max(arr[i-1], map.get(nums[i])+1);
            } else {
                arr[i] = arr[i-1];
            }
            map.put(nums[i], i);
        }
        int ans = nums[0];
        for (int i=1;i<nums.length;i++) {
            int val = arr[i];
            if (val == 0) {
                ans = Math.max(ans, prefixSum[i]);
            } else {
                ans = Math.max(ans, prefixSum[i] - prefixSum[val-1]);
            }
        }
        return ans;
    }
}
