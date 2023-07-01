/*
46. Permutations

Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

 

Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]
 

Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
*/

//Faster soln
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList();
        generate(list, 0, nums);
        return list;
    }
    
    private void generate(List<List<Integer>> list, int i, int[] nums) {
        if (i == nums.length) {
            List<Integer> tempList = new ArrayList();
            for (int j = 0; j < nums.length; j++) {
                tempList.add(nums[j]);
            }
            list.add(tempList);
        } else {
            for (int j = i; j < nums.length; j++) {
                swap(nums, i, j);
                generate(list, i+1, nums);
                swap(nums, i, j);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
}

//Backtrack soln
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList();
        generate(list, new ArrayList(), nums);
        return list;
    }
    
    private void generate(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) continue;
                tempList.add(nums[i]);
                generate(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
