/*
1696. Jump Game VI

You are given a 0-indexed integer array nums and an integer k.

You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.

You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.

Return the maximum score you can get.

 

Example 1:

Input: nums = [1,-1,-2,4,-7,3], k = 2
Output: 7
Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
Example 2:

Input: nums = [10,-5,-2,4,0,3], k = 3
Output: 17
Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
Example 3:

Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
Output: 0
 

Constraints:

1 <= nums.length, k <= 10^5
-10^4 <= nums[i] <= 10^4

*/

//Using monotonic queue and dp TC O(n) SC O(n)
class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] score = new int[n];
        score[0] = nums[0];
        Deque<Integer> queue = new ArrayDeque();
        queue.offerLast(0);
        for (int i=1;i<n;i++) {
            while (queue.peekFirst() != null && queue.peekFirst() < i-k) {
                queue.pollFirst();
            }
            score[i] = score[queue.peekFirst()] + nums[i];
            while (queue.peekLast() != null && score[queue.peekLast()] <= score[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        return score[n-1];
    }
}
