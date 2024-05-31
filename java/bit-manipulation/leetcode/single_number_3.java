/*
260. Single Number III

Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.

You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.

 

Example 1:

Input: nums = [1,2,1,3,2,5]
Output: [3,5]
Explanation:  [5, 3] is also a valid answer.
Example 2:

Input: nums = [-1,0]
Output: [-1,0]
Example 3:

Input: nums = [0,1]
Output: [1,0]
 

Constraints:

2 <= nums.length <= 3 * 10^4
-2^31 <= nums[i] <= 2^31 - 1
Each integer in nums will appear twice, only two integers will appear once.

*/

//A more space efficient soln using bit manipulation TC O(N) SC O(1)
class Solution {
    public int[] singleNumber(int[] nums) {
        int bitmask = 0;
        for (int num: nums) {
            bitmask ^= num;
        }
        int val = bitmask & (-bitmask);
        int x = 0;
        for (int num: nums) {
            if ((num & val) != 0) {
                x ^= num;
            }
        }
        return new int[]{x, x^bitmask};
    }
}

//My soln using hash set TC O(N) SC O(N)
class Solution {
    public int[] singleNumber(int[] nums) {
        Set<Integer> set = new HashSet();
        for (int num: nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        int[] ans = new int[2];
        int i = 0;
        for (int num: set) {
            ans[i++] = num;
        }
        return ans;
    }
}
