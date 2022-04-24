/*
349. Intersection of Two Arrays

Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.

 

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.
 

Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000
*/

//My soln
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet();
        for (int num: nums1) {
            set1.add(num);
        }
        Set<Integer> set2 = new HashSet();
        for (int num: nums2) {
            if (set1.contains(num)) {
                set2.add(num);
            }
        }
        int size = set2.size();
        int[] res = new int[size];
        int i=0;
        for (int num: set2) {
            res[i++] = num;
        }
        return res;
    }
}
