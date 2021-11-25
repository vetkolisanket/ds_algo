/*
81. Search in Rotated Sorted Array II

There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).

Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.

You must decrease the overall operation steps as much as possible.

 

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
 

Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
nums is guaranteed to be rotated at some pivot.
-104 <= target <= 104
 

Follow up: This problem is similar to Search in Rotated Sorted Array, but nums may contain duplicates. Would this affect the runtime complexity? How and why?
*/

//Another approach
class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int start = 0, end = nums.length-1;
        while (start <= end) {
            int mid = start + (end-start)/2;
            if (target == nums[mid]) return true;
            if (nums[start] == nums[mid]) start++;
            else if (nums[start] < nums[mid]) {
                if (target < nums[start] || target > nums[mid]) {
                    start = mid+1;
                } else end = mid-1;
            } else {
                if (target < nums[mid] || target > nums[end]) {
                    end = mid-1;
                } else start = mid+1;
            }
        }
        return false;
    }
}

//My soln
class Solution {
    public boolean search(int[] nums, int target) {
        int[] arr = new int[nums.length*2];
        for (int i=0;i<nums.length;i++) {
            arr[i] = arr[i+nums.length] = nums[i];
        }
        int start = 1;
        while (start < arr.length && arr[start] >= arr[start-1]) start++;
        int end = arr.length-2;
        while (end >= 0 && arr[end] <= arr[end+1]) end--;
        if (start == nums.length) {  //No rotation
            start = 0;
            end = nums.length-1;
        } else if (start == arr.length) return target == nums[0];   //All elements are same
        while (start <= end) {
            int mid = (start+end)/2;
            if (target == arr[mid]) return true;
            else if (target < arr[mid]) end = mid-1;
            else start = mid+1;
        }
        return false;
    }
}
