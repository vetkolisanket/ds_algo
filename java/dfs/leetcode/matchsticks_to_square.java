/*
473. Matchsticks to Square

You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. You want to use all the matchsticks to make one square. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Return true if you can make this square and false otherwise.

 

Example 1:


Input: matchsticks = [1,1,2,2,2]
Output: true
Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:

Input: matchsticks = [3,3,3,3,4]
Output: false
Explanation: You cannot find a way to form a square with all the matchsticks.
 

Constraints:

1 <= matchsticks.length <= 15
1 <= matchsticks[i] <= 10^8
*/

//TC O(4^n) SC O(n)
class Solution {
    public boolean makesquare(int[] matchsticks) {
        int n = matchsticks.length;
        if (n < 4) return false;
        int sum = 0;
        for (int val: matchsticks) {
            sum += val;
        }
        if (sum%4 != 0) return false;
        Arrays.sort(matchsticks);
        return dfs(matchsticks, new int[4], n-1, sum/4);
    }
    
    private boolean dfs(int[] matchsticks, int[] sums, int index, int target) {
        if (index == -1) {
            return sums[0] == target && sums[1] == target && sums[2] == target;
        }
        for (int i=0;i<4;i++) {
            if (sums[i] + matchsticks[index] > target) continue;
            sums[i] += matchsticks[index];
            if (dfs(matchsticks, sums, index-1, target)) {
                return true;
            }
            sums[i] -= matchsticks[index];
        }
        return false;
    }
}
