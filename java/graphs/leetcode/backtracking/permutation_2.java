/*
47. Permutations II

Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

 

Example 1:

Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
Example 2:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 

Constraints:

1 <= nums.length <= 8
-10 <= nums[i] <= 10
*/

//Leetcode premium soln
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList();
        Map<Integer, Integer> map = new HashMap();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        backtrack(list, nums.length, map, new LinkedList());
        return list;
    }
    
    private void backtrack(List<List<Integer>> list, int n, Map<Integer, Integer> map, LinkedList<Integer> temp) {
        if (temp.size() == n) {
            list.add(new ArrayList(temp));
            return;
        }
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int key = entry.getKey();
            int count = entry.getValue();
            if (count == 0) continue;
            map.put(key, count-1);
            temp.addLast(key);
            backtrack(list, n, map, temp);
            temp.removeLast();
            map.put(key, count);
        }
    }
}

//Discussion soln
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList();
        backtrack(list, new ArrayList(), nums, new boolean[nums.length]);
        return list;
    }
    
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
        if (tempList.size() == nums.length) list.add(new ArrayList(tempList));
        else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;
                tempList.add(nums[i]);
                used[i] = true;
                backtrack(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
