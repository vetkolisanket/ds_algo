/*
912. Sort an Array

Given an array of integers nums, sort the array in ascending order and return it.

You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.

 

Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).
Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
Explanation: Note that the values of nums are not necessairly unique.
 

Constraints:

1 <= nums.length <= 5 * 10^4
-5 * 104 <= nums[i] <= 5 * 10^4
*/

//Soln using counting sort TC O(n+k) SC O(n) where n is the no. of elements in the array and k is the range of nums from min value to max value in arr
class Solution {
    public int[] sortArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num: nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int i=min, j=0;i<=max;i++) {
            int k = map.getOrDefault(i, 0);
            while (k-- > 0) {
                nums[j++] = i;
            }
        }
        return nums;
    }
}

//Soln using merge sort TC O(nlog(n)) SC O(log(n))
class Solution {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length-1, new int[nums.length]);
        return nums;
    }

    private void mergeSort(int[] nums, int start, int end, int[] target) {
        if (start == end) return;
        int mid = start + (end-start)/2;
        mergeSort(nums, start, mid, target);
        mergeSort(nums, mid+1, end, target);
        merge(nums, start, mid, end, target);
    }

    private void merge(int[] nums, int start, int mid, int end, int[] target) {
        int i = start, j = mid+1, idx = start;
        while (i <= mid && j <= end) {
            if (nums[i] < nums[j]) {
                target[idx++] = nums[i++];
            } else {
                target[idx++] = nums[j++];
            }
        }
        while (i <= mid) target[idx++] = nums[i++];
        while (j <= end) target[idx++] = nums[j++];
        for (i = start;i<=end;i++) nums[i] = target[i];
    }
}
