/*
1481. Least Number of Unique Integers after K Removals

Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.

 

Example 1:

Input: arr = [5,5,4], k = 1
Output: 1
Explanation: Remove the single 4, only 5 is left.
Example 2:
Input: arr = [4,3,1,1,3,3,2], k = 3
Output: 2
Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
 

Constraints:

1 <= arr.length <= 10^5
1 <= arr[i] <= 10^9
0 <= k <= arr.length

*/

//Soln using frequency counting and sorting TC O(NlogN) SC O(N)
class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap();
        for (int num: arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int val : map.values()) {
            list.add(val);
        }
        Collections.sort(list);
        int ans = list.size();
        for (int i=0;i<list.size() && k > 0;i++) {
            int val = list.get(i);
            if (k >= val) {
                k -= val;
                ans--;
            } else break;
        }
        return ans;
    }
}
