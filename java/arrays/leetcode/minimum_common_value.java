/*
2540. Minimum Common Value

Given two integer arrays nums1 and nums2, sorted in non-decreasing order, return the minimum integer common to both arrays. If there is no common integer amongst nums1 and nums2, return -1.

Note that an integer is said to be common to nums1 and nums2 if both arrays have at least one occurrence of that integer.

 

Example 1:

Input: nums1 = [1,2,3], nums2 = [2,4]
Output: 2
Explanation: The smallest element common to both arrays is 2, so we return 2.
Example 2:

Input: nums1 = [1,2,3,6], nums2 = [2,3,4,5]
Output: 2
Explanation: There are two common elements in the array 2 and 3 out of which 2 is the smallest, so 2 is returned.
 

Constraints:

1 <= nums1.length, nums2.length <= 10^5
1 <= nums1[i], nums2[j] <= 10^9
Both nums1 and nums2 are sorted in non-decreasing order.

*/

//Soln using linear search TC O(N+M) SC O(1)
class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int idx1 = 0, idx2 = 0;
        while (idx1 < nums1.length && idx2 < nums2.length) {
            if (nums1[idx1] < nums2[idx2]) {
                idx1++;
            } else if (nums1[idx1] > nums2[idx2]) {
                idx2++;
            } else return nums1[idx1];
        }
        return -1;
    }
}

//Soln using binary search TC O(NlogM) SC O(1)
class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return getCommon(nums2, nums1);
        for (int num: nums1) {
            if (binarySearch(num, nums2)) {
                return num;
            }
        }
        return -1;
    }

    private boolean binarySearch(int num, int[] nums) {
        int start = 0, end = nums.length-1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (nums[mid] == num) return true;
            else if (nums[mid] > num) end = mid-1;
            else start = mid+1;
        }
        return false;
    }
}
