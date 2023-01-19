/*
974. Subarray Sums Divisible by K

Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.

A subarray is a contiguous part of an array.

 

Example 1:

Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
Example 2:

Input: nums = [5], k = 9
Output: 0
 

Constraints:

1 <= nums.length <= 3 * 10^4
-10^4 <= nums[i] <= 10^4
2 <= k <= 10^4
*/

//Soln using prefix sum and mod array TC O(n) SC O(k) where n is the length of the array
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length, prefixMod = 0, ans = 0;
        int[] modArr = new int[k];
        modArr[0] = 1;
        for (int num: nums) {
            prefixMod = (((prefixMod + num) % k) + k) % k;
            ans += modArr[prefixMod];
            modArr[prefixMod]++;
        }
        return ans;
    }
}
