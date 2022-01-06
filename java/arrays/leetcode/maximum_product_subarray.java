/*
152. Maximum Product Subarray

Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

A subarray is a contiguous subsequence of the array.

 

Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 

Constraints:

1 <= nums.length <= 2 * 10^4
-10 <= nums[i] <= 10
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
*/



//A few O(n) solns

class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, l=1, r=1;
        for (int i=0;i<nums.length;i++) {
            l = l==0 ? 1 : l;
            r = r==0 ? 1 : r;
            
            l *= nums[i];
            r *= nums[nums.length-i-1];
            
            max = Math.max(Math.max(l, r), max);
        }
        return max;
    }
}

class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], ans = nums[0];
        for (int i=1;i<nums.length;i++) {
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            
            max = Math.max(nums[i]*max, nums[i]);
            min = Math.min(nums[i]*min, nums[i]);
            
            if (max > ans) ans = max;
        }
        return ans;
    }
}

class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], ans = nums[0];
        for (int i=1;i<nums.length;i++) {
            int temp = max;
            max = Math.max(Math.max(max*nums[i], min*nums[i]), nums[i]);
            min = Math.min(Math.min(temp*nums[i], min*nums[i]), nums[i]);
            
            if (max > ans) ans = max;
        }
        return ans;
    }
}

//My O(n^2) soln
class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i=0;i<nums.length;i++) {
            int p = nums[i];
            max = Math.max(max, p);
            for (int j=i+1;j<nums.length;j++) {
                p *= nums[j];
                max = Math.max(max, p);
            }
        }
        return max;
    }
}
