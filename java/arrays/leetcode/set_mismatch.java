/*
645. Set Mismatch

You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.

You are given an integer array nums representing the data status of this set after the error.

Find the number that occurs twice and the number that is missing and return them in the form of an array.

 

Example 1:

Input: nums = [1,2,2,4]
Output: [2,3]
Example 2:

Input: nums = [1,1]
Output: [1,2]
 

Constraints:

2 <= nums.length <= 10^4
1 <= nums[i] <= 10^4
*/

//A better space soln TC O(N) SC O(1)
class Solution {
    public int[] findErrorNums(int[] nums) {
        int dup = -1, miss = -1;
        for (int i=0;i<nums.length;i++) {
            int index = Math.abs(nums[i])-1;
            if (nums[index] < 0) {
                dup = index+1;
            } else {
                nums[index] *= -1;
            }
        }
        for (int i=0;i<nums.length;i++) {
            if (nums[i] > 0) {
                miss = i+1;
            }
        }
        return new int[]{dup, miss};
    }
}

//My soln TC O(N) SC O(N)
class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];
        Set<Integer> set = new HashSet();
        for (int num: nums) {
            int val = Math.abs(num);
            nums[val-1] *= -1;
            set.add(val);
        }
        for (int i=0; i< nums.length; i++) {
            if (nums[i] > 0) {
                if (set.contains(i+1)) ans[0] = i+1;
                else ans[1] = i+1;
            }
        }
        return ans;
    }
}
