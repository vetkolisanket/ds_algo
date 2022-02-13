/*
215. Kth Largest Element in an Array

Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

 

Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
 

Constraints:

1 <= k <= nums.length <= 10^4
-10^4 <= nums[i] <= 10^4
*/

//My soln using priority queue which is O(n + klgk) time and O(k) space
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue(k, Collections.reverseOrder());
        for (int i=0;i<nums.length;i++) {
            pq.add(nums[i]);
        }
        for(int i=1;i<k;i++) pq.poll();
        return pq.poll();
    }
}

//Another soln which is O(n) avg time using quick select
class Solution {
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        return findKthLargest(nums, 0, nums.length-1, nums.length-k);
    }
    
    private void shuffle(int[] nums) {
        Random random = new Random();
        for (int i=0;i<nums.length;i++) {
            int j = random.nextInt(i+1);
            swap(nums, i, j);
        }
    }
    
    private int findKthLargest(int[] nums, int low, int high, int k) {
        while (low <= high) {
            int i = low;
            for (int j=low+1;j<=high;j++) {
                if (nums[j] < nums[low]) {
                    swap(nums,++i, j);
                }
            }
            swap(nums, low, i);
            
            if (k < i) high = i-1;
            else if (k > i) low = i+1;
            else return nums[i];
        }
        return -1;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
