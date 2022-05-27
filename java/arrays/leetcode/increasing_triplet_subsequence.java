/*
334. Increasing Triplet Subsequence

Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

 

Example 1:

Input: nums = [1,2,3,4,5]
Output: true
Explanation: Any triplet where i < j < k is valid.
Example 2:

Input: nums = [5,4,3,2,1]
Output: false
Explanation: No triplet exists.
Example 3:

Input: nums = [2,1,5,0,4,6]
Output: true
Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 

Constraints:

1 <= nums.length <= 5 * 10^5
-2^31 <= nums[i] <= 2^31 - 1
 

Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
*/

//O(n) time and O(1) space soln
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int num1 = Integer.MAX_VALUE, num2 = Integer.MAX_VALUE;
        for (int num: nums) {
            if (num <= num1) {
                num1 = num;
            } else if (num <= num2) {
                num2 = num;
            } else return true;
        }
        return false;
    }
}

//My soln O(nlog(n)) soln
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int[] arr = new int[3];
        int index = 0;
        for (int num: nums) {
            int i = Arrays.binarySearch(arr, 0, index, num);
            if (i < 0) {
                i = -(i+1);
            }
            arr[i] = num;
            if (i == 2) {
                return true;
            } else if (i == index) {
                index++;
            }
        }
        return false;
    }
}
