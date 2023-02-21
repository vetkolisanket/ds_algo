/*
540. Single Element in a Sorted Array

You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.

 

Example 1:

Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: nums = [3,3,7,7,10,11,11]
Output: 10
 

Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 10^5
*/

//Soln using binary search TC O(log(n)) SC O(1)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int start = 0, end = nums.length-1;
        while (start < end) {
            int mid = (start+end) >>> 1;
            if (mid%2 == 0) {
                if (mid < nums.length-1 && nums[mid] == nums[mid+1]) {
                    start = mid+1;
                } else if (mid > 0 && nums[mid] == nums[mid-1]) {
                    end = mid-1;
                } else return nums[mid];
            } else {
                if (mid > 0 && nums[mid] == nums[mid-1]) {
                    start = mid+1;
                } else if (mid < nums.length-1 && nums[mid] == nums[mid+1]) {
                    end = mid-1;
                } else return nums[mid];
            }
        }
        return nums[start];
    }
}

//A slightly simpler soln TC O(log(n)) SC O(1)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int lo = 0, hi = nums.length-1;
        while (lo < hi) {
            int mid = (lo+hi) >>> 1;
            if (mid%2 == 1) mid--;
            if (nums[mid] == nums[mid+1]) {
                lo = mid+2;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }
}
