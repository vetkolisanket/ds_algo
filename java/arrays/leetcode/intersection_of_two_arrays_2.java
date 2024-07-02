/*
350. Intersection of Two Arrays II

Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.

 

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.
 

Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000
 

Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
*/

//A different approach
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap();
        for (int num: nums1) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        int k=0;
        for (int num: nums2) {
            if (map.containsKey(num)) {
                int count = map.get(num);
                if (count > 0) {
                    nums1[k++] = num;
                }
                map.put(num, count-1);
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }
}

//My soln
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i=0,j=0;
        List<Integer> list = new ArrayList();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums2[j] < nums1[i]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] res = new int[list.size()];
        for (i=0;i<res.length;i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}

//Soln from another attempt TC O(M+N) SC O(M)
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int num: nums2) {
            if (map.containsKey(num)) {
                list.add(num);
                map.put(num, map.get(num)-1);
                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }
        int[] arr = new int[list.size()];
        for (int i=0;i<arr.length;i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
