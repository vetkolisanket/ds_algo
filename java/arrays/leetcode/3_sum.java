/*
15. 3Sum

Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Example 2:

Input: nums = []
Output: []
Example 3:

Input: nums = [0]
Output: []
 

Constraints:

0 <= nums.length <= 3000
-105 <= nums[i] <= 105
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        List<List<Integer>> list = new ArrayList();
        Arrays.sort(nums);
        for(int i=0;i<length-2;i++) {
            if (nums[i]>0) break;
            if (i==0|| nums[i]!= nums[i-1]){
                int l = i+1;
                int h = length-1;
                int sum = 0 - nums[i];
                while(l<h) {
                    if (nums[l]+nums[h] == sum) {
                        list.add(Arrays.asList(nums[i],nums[l],nums[h]));
                        while (l<h && nums[l] == nums[l+1]) l++;
                        while (l<h && nums[h] == nums[h-1]) h--;
                        l++;
                        h--;
                    } else if(nums[l]+nums[h]<sum) {
                        while (l<h && nums[l] == nums[l+1]) l++;
                        l++;
                    } else {
                        while (l<h && nums[h] == nums[h-1]) h--;
                        h--;
                    }
                }
            }
        }
        return list;
    }
    
}
