/*
1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit

Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.

 

Example 1:

Input: nums = [8,2,4,7], limit = 4
Output: 2 
Explanation: All subarrays are: 
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4. 
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4. 
Therefore, the size of the longest subarray is 2.
Example 2:

Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4 
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
Example 3:

Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= limit <= 10^9

*/

//Soln using tree map and sliding window TC O(NlogN) SC O(N)
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int start = 0, ans = 0;
        TreeMap<Integer, Integer> map = new TreeMap();
        for (int end = 0; end < nums.length; end++) {
            map.put(nums[end], map.getOrDefault(nums[end], 0) + 1);
            while(map.lastKey() - map.firstKey() > limit) {
                map.put(nums[start], map.get(nums[start])-1);
                if (map.get(nums[start]) == 0) {
                    map.remove(nums[start]);
                }
                start++;
            }
            ans = Math.max(ans, end-start+1);
        }
        return ans;
    }
}

//A more time efficient soln using deques TC O(N) SC O(N)
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDq = new ArrayDeque<>(), minDq = new ArrayDeque();
        int ans = 0;
        for (int start = 0, end = 0; end < nums.length; end++) {
            while (!maxDq.isEmpty() && maxDq.peekLast() < nums[end]) {
                maxDq.pollLast();
            }
            maxDq.offerLast(nums[end]);
            while (!minDq.isEmpty() && minDq.peekLast() > nums[end]) {
                minDq.pollLast();
            }
            minDq.offerLast(nums[end]);
            while (maxDq.peekFirst() - minDq.peekFirst() > limit) {
                if (maxDq.peekFirst() == nums[start]) {
                    maxDq.pollFirst();
                }
                if (minDq.peekFirst() == nums[start]) {
                    minDq.pollFirst();
                }
                start++;
            }
            ans = Math.max(ans, end - start + 1);
        }
        return ans;
    }
}
