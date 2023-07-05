/*
1493. Longest Subarray of 1's After Deleting One Element

Given a binary array nums, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.

 

Example 1:

Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
Example 2:

Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
Example 3:

Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.
 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.

*/

//Soln using sliding window TC O(N) SC O(1)
class Solution {
    public int longestSubarray(int[] nums) {
        int ans = 0, zeroCount = 0, start = 0;
        for (int i=0;i<nums.length;i++) {
            zeroCount += nums[i] == 0 ? 1 : 0;
            while (zeroCount > 1) {
                zeroCount -= nums[start++] == 0 ? 1 : 0;
            }
            ans = Math.max(ans, i-start);
        }
        return ans;
    }
}

//My soln using left and right array to store frequency of contiguous ones of the given index TC O(N) SC O(1)
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int[] left = new int[n], right = new int[n];
        for (int i=1;i<n;i++) {
            if (nums[i-1] == 0) continue;
            left[i] = left[i-1] + 1;
        }
        for (int i=n-2;i>=0;i--) {
            if (nums[i+1] == 0) continue;
            right[i] = right[i+1] + 1;
        }
        int ans = 0;
        for (int i=0;i<n;i++) {
            ans = Math.max(ans, left[i] + right[i]);
        }
        return ans;
    }
}
