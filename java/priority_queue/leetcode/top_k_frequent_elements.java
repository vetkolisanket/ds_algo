/*
347. Top K Frequent Elements

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

 

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.
 

Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/

//Soln using map to collect frequencies and then priority queue to pick the most frequent k elements TC O(NlogK) SC O(N+K)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<Pair<Integer, Integer>>((a, b) -> {
            return a.getValue() - b.getValue();
        });
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            pq.offer(new Pair(entry.getKey(), entry.getValue()));
            if (pq.size() > k) pq.poll();
        }
        int[] ans = new int[k];
        for (int i=0;i<k;i++) {
            ans[i] = pq.poll().getKey();
        }
        return ans;
    }
}
