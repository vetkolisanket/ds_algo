/*
2248. Intersection of Multiple Arrays

Given a 2D integer array nums where nums[i] is a non-empty array of distinct positive integers, return the list of integers that are present in each array of nums sorted in ascending order.
 

Example 1:

Input: nums = [[3,1,2,4,5],[1,2,3,4],[3,4,5,6]]
Output: [3,4]
Explanation: 
The only integers present in each of nums[0] = [3,1,2,4,5], nums[1] = [1,2,3,4], and nums[2] = [3,4,5,6] are 3 and 4, so we return [3,4].
Example 2:

Input: nums = [[1,2,3],[4,5,6]]
Output: []
Explanation: 
There does not exist any integer present both in nums[0] and nums[1], so we return an empty list [].
 

Constraints:

1 <= nums.length <= 1000
1 <= sum(nums[i].length) <= 1000
1 <= nums[i][j] <= 1000
All the values of nums[i] are unique.
*/

class Solution {
    public List<Integer> intersection(int[][] nums) {
        Set<Integer> set1 = new TreeSet();
        for (int num: nums[0]) {
            set1.add(num);
        }
        Set<Integer> set2 = new TreeSet();
        for (int i=1;i<nums.length;i++) {
            for (int j=0;j<nums[i].length;j++) {
                if (set1.contains(nums[i][j])) {
                    set2.add(nums[i][j]);
                }
            }
            set1 = set2;
            set2 = new TreeSet();
        }
        List<Integer> list = new ArrayList(set1);
        return list;
    }
}
