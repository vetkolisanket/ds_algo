/*
229. Majority Element II

Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

 

Example 1:

Input: nums = [3,2,3]
Output: [3]
Example 2:

Input: nums = [1]
Output: [1]
Example 3:

Input: nums = [1,2]
Output: [1,2]
 

Constraints:

1 <= nums.length <= 5 * 104
-109 <= nums[i] <= 109
 

Follow up: Could you solve the problem in linear time and in O(1) space?


*/

//My soln using frequency map TC O(N) SC O(N)
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList();
        int n = nums.length;
        Map<Integer, Integer> freqMap = new HashMap();
        for (int num: nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry: freqMap.entrySet()) {
            if (entry.getValue() > n/3) {
                list.add(entry.getKey());
            }
        }
        return list;
    }
}

//Soln to the follow up TC O(N) SC O(1)
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int count1 = 0, count2 = 0;
        Integer candidate1 = null, candidate2 = null;
        for (int num: nums) {
            if (candidate1 != null && candidate1 == num) {
                count1++;
            } else if (candidate2 != null && candidate2 == num) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1++;
            } else if (count2 == 0) {
                candidate2 = num;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }

        List<Integer> list = new ArrayList();
        int n = nums.length;
        count1 = 0;
        count2 = 0;
        for (int num: nums) {
            if (candidate1 != null && candidate1 == num) count1++;
            if (candidate2 != null && candidate2 == num) count2++;
        }
        if (count1 > n/3) list.add(candidate1);
        if (count2 > n/3) list.add(candidate2);
        return list;
    }
}
