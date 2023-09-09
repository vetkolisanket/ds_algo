/*
377. Combination Sum IV

Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.

The test cases are generated so that the answer can fit in a 32-bit integer.

 

Example 1:

Input: nums = [1,2,3], target = 4
Output: 7
Explanation:
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.
Example 2:

Input: nums = [9], target = 3
Output: 0
 

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 1000
All the elements of nums are unique.
1 <= target <= 1000
 

Follow up: What if negative numbers are allowed in the given array? How does it change the problem? What limitation we need to add to the question to allow negative numbers?
*/

//Top down soln TC O(T*N) SC O(T)
class Solution {

    public int combinationSum4(int[] nums, int target) {
        int[] memo = new int[target+1];
        Arrays.fill(memo, -1);
        return dfs(nums, target, memo);
    }

    private int dfs(int[] nums, int target, int[] memo) {
        if (target < 0) return 0;
        if (0 == target) {
            return 1;
        }
        if (memo[target] != -1) return memo[target];
        int ans = 0;
        for (int i=0;i<nums.length;i++) {
            ans += dfs(nums, target-nums[i], memo);
        }
        return memo[target] = ans;
    }
}

//Bottom up DP Soln TC O(T.N) SC O(T) where T is the target and N is the length of nums
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        Arrays.sort(nums);
        for (int i=1;i<=target;i++) {
            for (int num: nums) {
                if (i >= num) {
                    dp[i] += dp[i-num];
                } else break;
            }
        }
        return dp[target];
    }
}
