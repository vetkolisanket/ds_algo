/*
137. Single Number II

Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.

You must implement a solution with a linear runtime complexity and use only constant extra space.

 

Example 1:

Input: nums = [2,2,3,2]
Output: 3
Example 2:

Input: nums = [0,1,0,1,0,1,99]
Output: 99
 

Constraints:

1 <= nums.length <= 3 * 10^4
-2^31 <= nums[i] <= 2^31 - 1
Each element in nums appears exactly three times except for one element which appears once.
*/

//Easier to understand soln
class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int i=0;i<nums.length;i++) {
            ones = (ones^nums[i]) & ~twos;
            twos = (twos^nums[i]) & ~ones;
        }
        return ones;
    }
}

//A more complicated but efficient soln
class Solution {
    public int singleNumber(int[] nums) {
        int x1 = 0, x2 = 0, mask = 0;
        for (int i=0;i<nums.length;i++) {
            x2 ^= x1 & nums[i];
            x1 ^= nums[i];
            mask = ~(x1 & x2);
            x1 &= mask;
            x2 &= mask;
        }
        return x1;
    }
}
