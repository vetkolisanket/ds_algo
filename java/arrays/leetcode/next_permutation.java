/*
31. Next Permutation

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).

The replacement must be in place and use only constant extra memory.

 

Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]
Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]
Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]
Example 4:

Input: nums = [1]
Output: [1]
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100
*/

class Solution {
    public void nextPermutation(int[] nums) {
        int length = nums.length;
        int i = length - 2;
        for(;i>=0;i--) {
            if (nums[i+1] > nums[i]) {
                break;
            }
        }
        if(i>=0) {
            int j = length-1;
            while (j>=i && nums[i] >= nums[j]) j--;
            swap(nums, i, j);
        }
        reverse(nums, i+1, length-1);
    }
    
    private void swap(int[] nums, int i, int j) {
        nums[i] += nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }
    
    private void reverse(int[] nums, int i, int j) {
        while (i<j) {
            swap(nums, i++, j--);
        }
    }
    
}
