/*
164. Maximum Gap

Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.

You must write an algorithm that runs in linear time and uses linear extra space.

 

Example 1:

Input: nums = [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
Example 2:

Input: nums = [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.
 

Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 10^9
*/

//Bucket sort
class Solution {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        int min = nums[0];
        int max = nums[0];
        for (int i : nums) {
            min = Math.min(i, min);
            max = Math.max(i, max);
        }
        int gap = (int)Math.ceil((double)(max-min)/(nums.length-1));
        int[] bucketMin = new int[nums.length-1];
        int[] bucketMax = new int[nums.length-1];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        for (int i : nums) {
            if (i == min || i == max) continue;
            int idx = (i-min)/gap;
            bucketMin[idx] = Math.min(i, bucketMin[idx]);
            bucketMax[idx] = Math.max(i, bucketMax[idx]);
        }
        int maxGap = Integer.MIN_VALUE;
        int previous = min;
        for (int i=0;i<nums.length-1;i++) {
            if (bucketMin[i] == Integer.MAX_VALUE && bucketMax[i] == Integer.MIN_VALUE)
                continue;
            maxGap = Math.max(maxGap, bucketMin[i]-previous);
            previous = bucketMax[i];
        }
        maxGap = Math.max(maxGap, max-previous);
        return maxGap;
    }
}

//My soln
class Solution {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        Set<Integer> set = new TreeSet();
        for (int i=0;i<nums.length;i++) {
            set.add(nums[i]);
        }
        Integer [] arr = new Integer[set.size()];
        arr = set.toArray(arr);
        int max = 0;
        for (int i=1;i<arr.length;i++) {
            max = Math.max(max, arr[i] - arr[i-1]);
        }
        return max;
    }
}
