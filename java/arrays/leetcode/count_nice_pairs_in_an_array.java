/*
1814. Count Nice Pairs in an Array

You are given an array nums that consists of non-negative integers. Let us define rev(x) as the reverse of the non-negative integer x. For example, rev(123) = 321, and rev(120) = 21. A pair of indices (i, j) is nice if it satisfies all of the following conditions:

0 <= i < j < nums.length
nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
Return the number of nice pairs of indices. Since that number can be too large, return it modulo 109 + 7.

 

Example 1:

Input: nums = [42,11,1,97]
Output: 2
Explanation: The two pairs are:
 - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
 - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.
Example 2:

Input: nums = [13,10,35,24,76]
Output: 4
 

Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 10^9
*/

//Soln TC O(N) SC O(N)
class Solution {
    public int countNicePairs(int[] nums) {
        int ans = 0, mod = (int)(1e9 + 7);
        Map<Integer, Integer> map = new HashMap();
        for (int i=0;i<nums.length;i++) {
            int val = nums[i] - rev(nums[i]);
            ans = (ans + map.getOrDefault(val, 0)) % mod;
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return ans;
    }

    private int rev(int num) {
        int ans = 0;
        while (num > 0) {
            ans *= 10;
            ans += num%10;
            num /= 10;
        }
        return ans;
    }
}
