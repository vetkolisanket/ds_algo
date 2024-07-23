/*
1636. Sort Array by Increasing Frequency

Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.

Return the sorted array.

 

Example 1:

Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
Example 2:

Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
Example 3:

Input: nums = [-1,1,-6,4,5,-6,1,4,1]
Output: [5,-1,4,4,-6,-6,1,1,1]
 

Constraints:

1 <= nums.length <= 100
-100 <= nums[i] <= 100

*/

//Soln using a custom comparator TC O(N + MlogM) SC O(M) where N is the no. of elements in num and M is the no. of unique elements
class Solution {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            pq.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        int[] ans = new int[nums.length];
        for (int i=0, k=0;i<map.size();i++) {
            int[] arr = pq.poll();
            for (int j=0;j<arr[1];j++) {
                ans[k++] = arr[0];
            }
        }
        return ans;
    }
}
