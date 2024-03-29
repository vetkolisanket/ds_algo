/*
300. Longest Increasing Subsequence

Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

 

Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
 

Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104
 

Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
*/

//Another O(NlogN) soln from another attempt SC O(1)
class Solution {
    public int lengthOfLIS(int[] nums) {
        int j = 0;
        for (int i=0;i<nums.length;i++) {
            int pos = find(nums, 0, j, nums[i]);
            nums[pos] = nums[i];
            if (pos == j+1) j++;
        }
        return j+1;
    }

    private int find(int[] nums, int start, int end, int val) {
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (nums[mid] == val) return mid;
            if (nums[mid] > val) end = mid-1;
            else start = mid+1;
        }
        return start;
    }
}

//O(nlogn) time complexity soln
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num: nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i+1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
}

//Soln from 2nd attempt TC O(nlog(n)) SC O(n)
class Solution {
    
    public int lengthOfLIS(int[] nums) {
        int[] arr = new int[nums.length];
        int max = 0;
        Arrays.fill(arr, Integer.MAX_VALUE);
        for (int num: nums) {
            int pos = Arrays.binarySearch(arr, num);
            if (pos < 0) {
                arr[-pos-1] = num;
                max = Math.max(max, -pos);
            }
        }
        return max;
    }
}
