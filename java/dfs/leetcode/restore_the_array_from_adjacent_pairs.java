/*
1743. Restore the Array From Adjacent Pairs

There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.

You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.

It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.

Return the original array nums. If there are multiple solutions, return any of them.

 

Example 1:

Input: adjacentPairs = [[2,1],[3,4],[3,2]]
Output: [1,2,3,4]
Explanation: This array has all its adjacent pairs in adjacentPairs.
Notice that adjacentPairs[i] may not be in left-to-right order.
Example 2:

Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
Output: [-2,4,1,-3]
Explanation: There can be negative numbers.
Another solution is [-3,1,4,-2], which would also be accepted.
Example 3:

Input: adjacentPairs = [[100000,-100000]]
Output: [100000,-100000]
 

Constraints:

nums.length == n
adjacentPairs.length == n - 1
adjacentPairs[i].length == 2
2 <= n <= 105
-10^5 <= nums[i], ui, vi <= 10^5
There exists some nums that has adjacentPairs as its pairs.
*/

//Soln using DFS TC O(N) SC O(N)
class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> map = new HashMap();
        for (int[] pair: adjacentPairs) {
            map.computeIfAbsent(pair[0], k -> new ArrayList()).add(pair[1]);
            map.computeIfAbsent(pair[1], k -> new ArrayList()).add(pair[0]);
        }
        int cur = 0;
        for (Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
            if (entry.getValue().size() == 1) {
                cur = entry.getKey();
                break;
            }
        }
        int i = 0;
        int n = adjacentPairs.length;
        int[] ans = new int[n+1];
        dfs(cur, Integer.MAX_VALUE, ans, map, 0);
        return ans;
    }

    private void dfs(int cur, int pre, int[] ans, Map<Integer, List<Integer>> map, int i) {
        ans[i] = cur;
        for (int neighbour: map.get(cur)) {
            if (neighbour != pre) {
                dfs(neighbour, cur, ans, map, i+1);
                break;
            }
        }
    }
}
