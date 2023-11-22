/*
1424. Diagonal Traverse II

Given a 2D integer array nums, return all elements of nums in diagonal order as shown in the below images.

 

Example 1:


Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,4,2,7,5,3,8,6,9]
Example 2:


Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i].length <= 10^5
1 <= sum(nums[i].length) <= 10^5
1 <= nums[i][j] <= 10^5
*/

//Soln using hashmap TC O(N) SC O(N) where N is the no. of elements in nums
class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> map = new HashMap();
        int numElements = 0;
        for (int i=0;i<nums.size();i++) {
            numElements += nums.get(i).size();
            for (int j=0;j<nums.get(i).size();j++) {
                int sum = i+j;
                map.computeIfAbsent(sum, k -> new ArrayList()).add(0, nums.get(i).get(j));
            }
        }
        int i=0, j=0;
        int [] ans = new int[numElements];
        while (i < numElements) {
            List<Integer> list = map.get(j++);
            for (int num: list) {
                ans[i++] = num;
            }
        }
        return ans;
    }
}
