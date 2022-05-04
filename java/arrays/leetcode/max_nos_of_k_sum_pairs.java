/*
1679. Max Number of K-Sum Pairs

You are given an integer array nums and an integer k.

In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.

Return the maximum number of operations you can perform on the array.

 

Example 1:

Input: nums = [1,2,3,4], k = 5
Output: 2
Explanation: Starting with nums = [1,2,3,4]:
- Remove numbers 1 and 4, then nums = [2,3]
- Remove numbers 2 and 3, then nums = []
There are no more pairs that sum up to 5, hence a total of 2 operations.
Example 2:

Input: nums = [3,1,3,4,3], k = 6
Output: 1
Explanation: Starting with nums = [3,1,3,4,3]:
- Remove the first two 3's, then nums = [1,4,3]
There are no more pairs that sum up to 6, hence a total of 1 operation.
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= k <= 10^9

*/

//O(n) single pass soln
class Solution {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        int maxOps = 0;
        for (int num: nums) {
            int complement = k-num;
            if (map.getOrDefault(complement, 0) > 0) {
                maxOps++;
                map.put(complement, map.get(complement)-1);
            } else {
                map.put(num, map.getOrDefault(num, 0)+1);
            }
        }
        return maxOps;
    }
}

//My soln O(n) time 2 pass
class Solution {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        int maxOps = 0;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        for (int num: nums) {
            if (map.containsKey(num)) {
                if (map.get(num) == 1) {
                    map.remove(num);
                } else {
                    map.put(num, map.get(num)-1);
                }
                if (map.containsKey(k-num)) {
                    maxOps++;
                    if (map.get(k-num) == 1) {
                        map.remove(k-num);
                    } else {
                        map.put(k-num, map.get(k-num)-1);
                    }
                }
            }
        }
        return maxOps;
    }
}
