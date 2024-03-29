/*
35. Search Insert Position

Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [1,3,5,6], target = 5
Output: 2
Example 2:

Input: nums = [1,3,5,6], target = 2
Output: 1
Example 3:

Input: nums = [1,3,5,6], target = 7
Output: 4
 

Constraints:

1 <= nums.length <= 10^4
-10^4 <= nums[i] <= 10^4
nums contains distinct values sorted in ascending order.
-10^4 <= target <= 10^4
*/

//Soln using binary search TC O(log(n)) SC O(1)
class Solution {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int start = 0, end = n-1;
        while (start <= end) {
            int mid = start + (end-start)/2;
            if (mid == 0 && nums[mid] >= target) return 0;
            if (nums[mid] < target) start = mid+1;
            else if (nums[mid-1] < target) return mid;
            else end = mid;
        }
        return start;
    }
}

//A slightly simpler soln having similar complexity
class Solution {
    public int searchInsert(int[] nums, int target) {
        int start = 0, end = nums.length-1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) start = mid+1;
            else end = mid-1;
        }
        return start;
    }
}
