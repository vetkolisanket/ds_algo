/*
658. Find K Closest Elements

Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b
 

Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]
 

Constraints:

1 <= k <= arr.length
1 <= arr.length <= 10^4
arr is sorted in ascending order.
-10^4 <= arr[i], x <= 10^4
*/

//A faster soln using modified version of binary search
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length-k;
        while (left < right) {
            int mid = left + (right-left)/2;
            if (x-arr[mid] > arr[mid+k] - x) {
                left = mid+1;
            } else right = mid;
        }
        
        List<Integer> ans = new ArrayList();
        for (int i=0;i<k;i++) {
            ans.add(arr[left+i]);
        }
        return ans;
    }
}

//My soln using priority queue TC O(N + klog(N)) SC O(N)
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<Integer> small = new PriorityQueue<Integer>((a, b) -> {
            return b-a;
        });
        PriorityQueue<Integer> large = new PriorityQueue<Integer>();
        for (int num: arr) {
            if (num < x) small.offer(num);
            else large.offer(num);
        }
        List<Integer> list = new ArrayList();
        for (int i=0;i<k;i++) {
            if (!small.isEmpty() && !large.isEmpty()) {
                int s = small.peek();
                int l = large.peek();
                if (x-s <= l-x) {
                    list.add(small.poll());
                } else {
                    list.add(large.poll());
                }
            } else if (!small.isEmpty()) list.add(small.poll());
            else list.add(large.poll());
        }
        Collections.sort(list);
        return list;
    }
}
