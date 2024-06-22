/*
1248. Count Number of Nice Subarrays

Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.

 

Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There are no odd numbers in the array.
Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16
 

Constraints:

1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length
*/

//Soln using map and sliding window TC O(N) SC O(N)
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        map.put(0, 1);
        int maxOdd = 0;
        for (int num: nums) {
            if (num%2 == 1) {
                maxOdd++;
            }
            map.put(maxOdd, map.getOrDefault(maxOdd, 0) + 1);
        }
        int ans = 0;
        for (int i=0;i<=maxOdd-k;i++) {
            ans += map.get(i) * map.get(i+k);
        }
        return ans;
    }
}
