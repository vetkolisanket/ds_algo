/*
1509. Minimum Difference Between Largest and Smallest Value in Three Moves

You are given an integer array nums.

In one move, you can choose one element of nums and change it to any value.

Return the minimum difference between the largest and smallest value of nums after performing at most three moves.

 

Example 1:

Input: nums = [5,3,2,4]
Output: 0
Explanation: We can make at most 3 moves.
In the first move, change 2 to 3. nums becomes [5,3,3,4].
In the second move, change 4 to 3. nums becomes [5,3,3,3].
In the third move, change 5 to 3. nums becomes [3,3,3,3].
After performing 3 moves, the difference between the minimum and maximum is 3 - 3 = 0.
Example 2:

Input: nums = [1,5,0,10,14]
Output: 1
Explanation: We can make at most 3 moves.
In the first move, change 5 to 0. nums becomes [1,0,0,10,14].
In the second move, change 10 to 0. nums becomes [1,0,0,0,14].
In the third move, change 14 to 1. nums becomes [1,0,0,0,1].
After performing 3 moves, the difference between the minimum and maximum is 1 - 0 = 1.
It can be shown that there is no way to make the difference 0 in 3 moves.
Example 3:

Input: nums = [3,100,20]
Output: 0
Explanation: We can make at most 3 moves.
In the first move, change 100 to 7. nums becomes [3,7,20].
In the second move, change 20 to 7. nums becomes [3,7,7].
In the third move, change 3 to 7. nums becomes [7,7,7].
After performing 3 moves, the difference between the minimum and maximum is 7 - 7 = 0.
 

Constraints:

1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9

*/

//Soln using min and max heaps TC O(N) SC O(1)
class Solution {
    public int minDifference(int[] nums) {
        if (nums.length <= 4) return 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num: nums) {
            minHeap.offer(num);
            maxHeap.offer(num);
            if (minHeap.size() > 4) {
                minHeap.poll();
            }
            if (maxHeap.size() > 4) {
                maxHeap.poll();
            }
        }
        int[] maxElements = new int[4], minElements = new int[4];
        for (int i=0;i<4;i++) {
            maxElements[i] = minHeap.poll();
            minElements[3-i] = maxHeap.poll();
        }
        int minDiff = Integer.MAX_VALUE;
        for (int i=0;i<4;i++) {
            minDiff = Math.min(minDiff, maxElements[i] - minElements[i]);
        }
        return minDiff;
    }
}

//Soln using greedy and sorting TC O(NlogN) SC O(logN)
class Solution {
    public int minDifference(int[] nums) {
        if (nums.length <= 4) return 0;
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        for (int left = 0, right = nums.length-4;left<4;left++, right++) {
            minDiff = Math.min(minDiff, nums[right] - nums[left]);
        }
        return minDiff;
    }
}
