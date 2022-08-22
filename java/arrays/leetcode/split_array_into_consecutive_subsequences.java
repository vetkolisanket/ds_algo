/*
659. Split Array into Consecutive Subsequences

You are given an integer array nums that is sorted in non-decreasing order.

Determine if it is possible to split nums into one or more subsequences such that both of the following conditions are true:

Each subsequence is a consecutive increasing sequence (i.e. each integer is exactly one more than the previous integer).
All subsequences have a length of 3 or more.
Return true if you can split nums according to the above conditions, or false otherwise.

A subsequence of an array is a new array that is formed from the original array by deleting some (can be none) of the elements without disturbing the relative positions of the remaining elements. (i.e., [1,3,5] is a subsequence of [1,2,3,4,5] while [1,3,2] is not).

 

Example 1:

Input: nums = [1,2,3,3,4,5]
Output: true
Explanation: nums can be split into the following subsequences:
[1,2,3,3,4,5] --> 1, 2, 3
[1,2,3,3,4,5] --> 3, 4, 5
Example 2:

Input: nums = [1,2,3,3,4,4,5,5]
Output: true
Explanation: nums can be split into the following subsequences:
[1,2,3,3,4,4,5,5] --> 1, 2, 3, 4, 5
[1,2,3,3,4,4,5,5] --> 3, 4, 5
Example 3:

Input: nums = [1,2,3,4,4,5]
Output: false
Explanation: It is impossible to split nums into consecutive increasing subsequences of length 3 or more.
 

Constraints:

1 <= nums.length <= 10^4
-1000 <= nums[i] <= 1000
nums is sorted in non-decreasing order.
*/

//Soln after 2nd approach TC O(N) SC O(N)
class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap();
        Map<Integer, Integer> seq = new HashMap();
        for (int num: nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for (int num: nums) {
            if (freq.get(num) == 0) continue;
            if (seq.getOrDefault(num-1, 0) > 0) {
                seq.put(num-1, seq.get(num-1)-1);
                seq.put(num, seq.getOrDefault(num, 0)+1);
                freq.put(num, freq.get(num)-1);
            } else if (freq.getOrDefault(num+1, 0) > 0 && freq.getOrDefault(num+2, 0) > 0) {
                seq.put(num+2, seq.getOrDefault(num+2, 0)+1);
                freq.put(num, freq.get(num)-1);
                freq.put(num+1, freq.get(num+1)-1);
                freq.put(num+2, freq.get(num+2)-1);
            } else return false;
        }
        return true;
    }
}

class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap();
        Map<Integer, Integer> seq = new HashMap();
        for (int num: nums) {
            freq.put(num, freq.getOrDefault(num, 0)+1);
        }
        for (int num: nums) {
            if (freq.get(num) == 0) continue;
            
            if (seq.getOrDefault(num-1, 0) > 0) {
                seq.put(num-1, seq.get(num-1)-1);
                seq.put(num, seq.getOrDefault(num, 0)+1);
            } else {
                if (freq.getOrDefault(num+1, 0) == 0 || freq.getOrDefault(num+2, 0) == 0) {
                    return false;
                } else {
                    freq.put(num+1, freq.get(num+1)-1);
                    freq.put(num+2, freq.get(num+2)-1);
                    seq.put(num+2, seq.getOrDefault(num+2, 0)+1);
                }
            }
            freq.put(num, freq.get(num)-1);
        }
        return true;
    }
}
