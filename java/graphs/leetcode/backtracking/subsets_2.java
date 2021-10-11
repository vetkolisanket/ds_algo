/*
90. Subsets II

Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

 

Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
*/

// Faster soln
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList();
        Arrays.sort(nums);
        generate(list, new ArrayList(), nums, 0);
        return list;
    }
    
    private void generate(List<List<Integer>> list, List<Integer> tempList, int[] nums, int i) {
        if (i <= nums.length) {
            list.add(new ArrayList(tempList));
        }
        while (i < nums.length) {
            tempList.add(nums[i]);
            generate(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
            i++;
            while (i < nums.length && nums[i] == nums[i-1]) i++;
        }
    }
}

//Backtracking soln
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList();
        Arrays.sort(nums);
        generate(list, new ArrayList(), nums, 0);
        return list;
    }
    
    private void generate(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList(tempList));
        for (int i = start; i < nums.length; ++i) {
            if (i > start && nums[i] == nums[i-1]) continue;
            tempList.add(nums[i]);
            generate(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
