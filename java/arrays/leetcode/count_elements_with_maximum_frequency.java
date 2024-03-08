/*
3005. Count Elements With Maximum Frequency

You are given an array nums consisting of positive integers.

Return the total frequencies of elements in nums such that those elements all have the maximum frequency.

The frequency of an element is the number of occurrences of that element in the array.

 

Example 1:

Input: nums = [1,2,2,3,1,4]
Output: 4
Explanation: The elements 1 and 2 have a frequency of 2 which is the maximum frequency in the array.
So the number of elements in the array with maximum frequency is 4.
Example 2:

Input: nums = [1,2,3,4,5]
Output: 5
Explanation: All elements of the array have a frequency of 1 which is the maximum.
So the number of elements in the array with maximum frequency is 5.
 

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100

*/

//Soln using hashmap TC O(N) SC O(N)
class Solution {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Map<Integer, Integer> freqMap = new HashMap();
        int maxFreq = 0;
        for (int val: map.values()) {
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
            maxFreq = Math.max(maxFreq, val);
        }
        return maxFreq * freqMap.get(maxFreq);
    }
}

//Soln using tree map TC O(N + MlogM) SC O(N) where N is the no. of elements and M is the no. of unique elements
class Solution {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        TreeMap<Integer, Integer> treeMap = new TreeMap();
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            treeMap.put(entry.getValue(), treeMap.getOrDefault(entry.getValue(), 0) + 1);
        }
        Map.Entry<Integer, Integer> entry = treeMap.lastEntry();
        return entry.getKey() * entry.getValue();
    }
}

//One pass soln TC O(N) SC O(N)
class Solution {
    public int maxFrequencyElements(int[] nums) {
        int maxFreq = 0, totalMaxFreq = 0;
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            int freq = map.get(num);
            if (freq > maxFreq) {
                maxFreq = freq;
                totalMaxFreq = 1;
            } else if (freq == maxFreq) {
                totalMaxFreq++;
            }
        }
        return maxFreq * totalMaxFreq;

    }
}
