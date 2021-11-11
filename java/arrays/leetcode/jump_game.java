/*
55. Jump Game

You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 

Constraints:

1 <= nums.length <= 10^4
0 <= nums[i] <= 10^5
*/

//Even faster soln
class Solution {
    public boolean canJump(int[] nums) {
        int last = nums.length-1;
        for(int i = last-1; i >=0; i--) {
            if(i+nums[i] >= last) last = i;
        }
        return last == 0;
    }
}

//Faster soln
class Solution {
    public boolean canJump(int[] nums) {
        int maxLocation = 0;
        for (int i=0;i<nums.length;i++) {
            if (i > maxLocation) return false;
            maxLocation = (i+nums[i] > maxLocation) ? i+nums[i] : maxLocation;
        }
        return true;
    }
}

//My soln
class Solution {
    public boolean canJump(int[] nums) {
        boolean[] cannotJump = new boolean[nums.length];
        return canJump(0, cannotJump, nums);
    }
    
    private boolean canJump(int i, boolean[] cannotJump, int[] nums) {
        if (cannotJump[i]) return false;
        int len = nums.length;
        if (i >= len-1 || i+nums[i] >= len-1) return true;
        for (int j = nums[i]; j > 0; j--) {
            if (canJump(i+j, cannotJump, nums)) return true;
        }
        cannotJump[i] = true;
        return false;
    }
}
