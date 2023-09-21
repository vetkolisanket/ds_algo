/*
4. Median of Two Sorted Arrays

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

 

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 

Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-10^6 <= nums1[i], nums2[i] <= 10^6

*/

//Soln using linear search TC O(M+N) SC O(M+N)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int[] arr = new int[len];
        for (int i=0,j=0,k=0;i<len;i++) {
            if (j < nums1.length && k < nums2.length) {
                if (nums1[j] < nums2[k]) {
                    arr[i] = nums1[j++];
                } else {
                    arr[i] = nums2[k++];
                }
            } else if (j < nums1.length) {
                arr[i] = nums1[j++];
            } else arr[i] = nums2[k++];
        }
        if (len%2 == 0) {
            return (arr[(len-1)/2] + arr[len/2])/((double)2);
        }
        return arr[len/2];
    }
}
