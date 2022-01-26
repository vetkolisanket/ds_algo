/*
494. Target Sum

You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

 

Example 1:

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
Example 2:

Input: nums = [1], target = 1
Output: 1
 

Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000
*/

//My soln
class Solution {
    Map<String, Integer> map = new HashMap();
    
    public int findTargetSumWays(int[] nums, int target) {
        return ways(nums, 1, target-nums[0]) + ways(nums, 1, target+nums[0]);
    }
    
    private int ways(int[] nums, int i, int target) {
        String key = i + "," + target;
        if (map.containsKey(key)) {
            return map.get(key);
        } else if (i==nums.length) {
            if (target == 0) return 1;
            return 0;
        } else {
            int ways = ways(nums, i+1, target-nums[i]) + ways(nums, i+1, target+nums[i]);
            map.put(key, ways);
            return ways;
        }
    }
}

Map<Map.Entry<Integer,Integer>,Integer> map;

public int findTargetSumWays(int[] nums, int s) {
    
    map = new HashMap<>();
    return dp(nums,s,nums.length-1,0);
       
}
 
//Another almost similar approach soln
private int dp(int[] nums, int target, int index, int curSum){
    
    Map.Entry<Integer,Integer> entry = Map.entry(index,curSum);
    if(map.containsKey(entry)) return map.get(entry);
    
    if(index<0 && curSum==target) return 1;
    if(index<0) return 0;
    
    int pos = dp(nums,target,index-1,nums[index]+curSum);
    int neg = dp(nums,target,index-1,-nums[index]+curSum);
    
    entry = Map.entry(index,curSum);
    map.put(entry,pos+neg);
    
    return pos+neg;
    
}

//A faster soln, which I have not understood yet
public int findTargetSumWays(int[] nums, int s) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1); 
    }   

    public int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1]; 
        dp[0] = 1;
        for (int n : nums)
            for (int i = s; i >= n; i--)
                dp[i] += dp[i - n]; 
        return dp[s];
    } 
