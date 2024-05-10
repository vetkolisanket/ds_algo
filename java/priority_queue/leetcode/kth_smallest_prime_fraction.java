/*
786. K-th Smallest Prime Fraction

You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique. You are also given an integer k.

For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].

Return the kth smallest fraction considered. Return your answer as an array of integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j].

 

Example 1:

Input: arr = [1,2,3,5], k = 3
Output: [2,5]
Explanation: The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
The third fraction is 2/5.
Example 2:

Input: arr = [1,7], k = 1
Output: [1,7]
 

Constraints:

2 <= arr.length <= 1000
1 <= arr[i] <= 3 * 10^4
arr[0] == 1
arr[i] is a prime number for i > 0.
All the numbers of arr are unique and sorted in strictly increasing order.
1 <= k <= arr.length * (arr.length - 1) / 2
 

Follow up: Can you solve the problem with better than O(n^2) complexity?

*/

//My soln using priority queue TC O(N^2logN) SC O(N^2)
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        if (k == 1) return new int[]{arr[0], arr[arr.length-1]};
        PriorityQueue<double[]> pq = new PriorityQueue<double[]>((a, b) -> {
            if (a[0] < b[0]) return -1;
            return 1;
        });
        for (int i=0;i<arr.length;i++) {
            for (int j=i+1;j<arr.length;j++) {
                pq.offer(new double[]{((double)arr[i])/arr[j], arr[i], arr[j]});
            }
        }
        while (k-- > 1) {
            pq.poll();
        }
        double[] a = pq.poll();
        return new int[]{(int)a[1], (int)a[2]};
    }
}

//A more time and space efficient soln TC O((N+K)logN) SC O(N)
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<double[]> pq = new PriorityQueue<double[]>((a, b) -> {
            if (a[0] < b[0]) return -1;
            return 1;
        });
        int n = arr.length;
        for (int i=0;i<n-1;i++) {
            pq.offer(new double[]{((double)arr[i])/arr[n-1], i ,n-1});
        }
        while (k-- > 1) {
            double[] holder = pq.poll();
            int numIdx = (int)holder[1], denIdx = (int)holder[2]-1;
            if (denIdx > numIdx) {
                pq.offer(new double[]{((double)arr[numIdx])/arr[denIdx], numIdx, denIdx});
            }
        }
        double[] ans = pq.poll();
        return new int[]{arr[(int)ans[1]], arr[(int)ans[2]]};
    }
}
