/*
2488. Count Subarrays With Median K

You are given an array nums of size n consisting of distinct integers from 1 to n and a positive integer k.

Return the number of non-empty subarrays in nums that have a median equal to k.

Note:

The median of an array is the middle element after sorting the array in ascending order. If the array is of even length, the median is the left middle element.
For example, the median of [2,3,1,4] is 2, and the median of [8,4,3,5,1] is 4.
A subarray is a contiguous part of an array.
 

Example 1:

Input: nums = [3,2,1,4,5], k = 4
Output: 3
Explanation: The subarrays that have a median equal to 4 are: [4], [4,5] and [1,4,5].
Example 2:

Input: nums = [2,3,1], k = 3
Output: 1
Explanation: [3] is the only subarray that has a median equal to 3.
 

Constraints:

n == nums.length
1 <= n <= 105
1 <= nums[i], k <= n
The integers in nums are distinct.
*/

/*
Some explanation of the soln
A subarray has a median k if:

It includes k
Count n[i] < k is equal to count n[i] > k (odd-size subarrays).
Count n[i] < k is one less than count n[i] > k (even-size subarrays).
Or, in other words, the balance between the count of smaller and larger elements is zero or one.

Since integers are distinct, we have only one k element in the array. So, we find it first.

Then, we go right from k, tracking the ballance and counting balances using a hash map.

Finally, we go left from k, tracking the balance, and counting complimentary balances in the hash map.

Say the ballance is -3 (more smaller elements). We look for value 3 and 4 in the hash map to count valid subarrays.
This is how our balances look for [7, 1, 3, 4, 2, 5 ,6] resulting in 5 valid subarrays.

 7  1  3 4  2 5 6
-1 -`2 -1 0 -1 0 1

C++

int countSubarrays(vector<int>& n, int k) {
    unordered_map<int, int> cnt;
    int p = find(begin(n), end(n), k) - begin(n), res = 0;
    for(int i = p, bal = 0; i < n.size(); ++i) {
        bal += n[i] == k ? 0 : n[i] < k ? -1 : 1;
        ++cnt[bal];
    }
    for(int i = p, bal = 0; i >= 0; --i) {
        bal += n[i] == k ? 0 : n[i] < k ? -1 : 1;
        res += cnt[-bal] + cnt[-bal + 1];
    }
    return res;
}
*/

//Soln using logic and map TC O(N) SC O(N)
class Solution {
    public int countSubarrays(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        int idx = find(nums, k);
        for (int i=idx, bal=0;i<nums.length;i++) {
            bal += nums[i] == k ? 0 : nums[i] < k ? -1 : 1;
            map.put(bal, map.getOrDefault(bal, 0) + 1);
        }
        int res = 0;
        for (int i=idx, bal=0;i>=0;i--) {
            bal += nums[i] == k ? 0 : nums[i] < k ? -1 : 1;
            res += map.getOrDefault(-bal, 0) + map.getOrDefault(-bal+1, 0);
        }
        return res;
    }
    
    private int find(int[] nums, int k) {
        for (int i=0;i<nums.length;i++) {
            if (nums[i] == k) return i;
        }
        return -1;
    }
}
