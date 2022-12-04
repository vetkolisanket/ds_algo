/*
2256. Minimum Average Difference

You are given a 0-indexed integer array nums of length n.

The average difference of the index i is the absolute difference between the average of the first i + 1 elements of nums and the average of the last n - i - 1 elements. Both averages should be rounded down to the nearest integer.

Return the index with the minimum average difference. If there are multiple such indices, return the smallest one.

Note:

The absolute difference of two numbers is the absolute value of their difference.
The average of n elements is the sum of the n elements divided (integer division) by n.
The average of 0 elements is considered to be 0.
 

Example 1:

Input: nums = [2,5,3,9,5,3]
Output: 3
Explanation:
- The average difference of index 0 is: |2 / 1 - (5 + 3 + 9 + 5 + 3) / 5| = |2 / 1 - 25 / 5| = |2 - 5| = 3.
- The average difference of index 1 is: |(2 + 5) / 2 - (3 + 9 + 5 + 3) / 4| = |7 / 2 - 20 / 4| = |3 - 5| = 2.
- The average difference of index 2 is: |(2 + 5 + 3) / 3 - (9 + 5 + 3) / 3| = |10 / 3 - 17 / 3| = |3 - 5| = 2.
- The average difference of index 3 is: |(2 + 5 + 3 + 9) / 4 - (5 + 3) / 2| = |19 / 4 - 8 / 2| = |4 - 4| = 0.
- The average difference of index 4 is: |(2 + 5 + 3 + 9 + 5) / 5 - 3 / 1| = |24 / 5 - 3 / 1| = |4 - 3| = 1.
- The average difference of index 5 is: |(2 + 5 + 3 + 9 + 5 + 3) / 6 - 0| = |27 / 6 - 0| = |4 - 0| = 4.
The average difference of index 3 is the minimum average difference so return 3.
Example 2:

Input: nums = [0]
Output: 0
Explanation:
The only index is 0 so return 0.
The average difference of index 0 is: |0 / 1 - 0| = |0 - 0| = 0.
 

Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 10^5
*/

//My soln using prefix sum TC O(N) SC O(N)
class Solution {
    public int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long[] pSum = new long[n];
        pSum[0] = nums[0];
        for (int i=1;i<n;i++) {
            pSum[i] += pSum[i-1] + nums[i];
        }
        int ans = -1;
        long minAvg = Long.MAX_VALUE;
        for (int i=0;i<n;i++) {
            long leftSum = pSum[i];
            long rightSum = pSum[n-1] - pSum[i];
            long leftAvg = leftSum/(i+1);
            long rightAvg = (rightSum == 0 ? rightSum : rightSum/(n-i-1));
            long avg = Math.abs(leftAvg - rightAvg);
            if (avg < minAvg) {
                minAvg = avg;
                ans = i;
            }
        }
        return ans;
    }
}

//More memory efficient soln TC O(N) SC O(1)
class Solution {
    public int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long sum = 0;
        for (int i=0;i<n;i++) {
            sum += nums[i];
        }
        long leftSum = 0, minAvg = Long.MAX_VALUE;
        int ans = -1;
        for (int i=0;i<n;i++) {
            leftSum += nums[i];
            long rightSum = sum - leftSum;
            long leftAvg = leftSum/(i+1);
            long rightAvg = rightSum == 0 ? 0 : rightSum/(n-i-1);
            long avg = Math.abs(leftAvg - rightAvg);
            if (avg < minAvg) {
                minAvg = avg;
                ans = i;
            }
        }
        return ans;
    }
}
