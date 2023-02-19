/*
2572. Count the Number of Square-Free Subsets

You are given a positive integer 0-indexed array nums.

A subset of the array nums is square-free if the product of its elements is a square-free integer.

A square-free integer is an integer that is divisible by no square number other than 1.

Return the number of square-free non-empty subsets of the array nums. Since the answer may be too large, return it modulo 109 + 7.

A non-empty subset of nums is an array that can be obtained by deleting some (possibly none but not all) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.

 

Example 1:

Input: nums = [3,4,4,5]
Output: 3
Explanation: There are 3 square-free subsets in this example:
- The subset consisting of the 0th element [3]. The product of its elements is 3, which is a square-free integer.
- The subset consisting of the 3rd element [5]. The product of its elements is 5, which is a square-free integer.
- The subset consisting of 0th and 3rd elements [3,5]. The product of its elements is 15, which is a square-free integer.
It can be proven that there are no more than 3 square-free subsets in the given array.
Example 2:

Input: nums = [1]
Output: 1
Explanation: There is 1 square-free subset in this example:
- The subset consisting of the 0th element [1]. The product of its elements is 1, which is a square-free integer.
It can be proven that there is no more than 1 square-free subset in the given array.
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 30
*/

//Soln using dp, bit manipulation, dfs and math
class Solution {

    private final int MOD = 1000000007;
    private int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    private long[][] dp = new long[1001][1 << 11];

    public int squareFreeSubsets(int[] nums) {
        for (long[] arr: dp) Arrays.fill(arr, -1);
        return (int) (dfs(nums, 0, 1) - 1);
    }

    private long dfs(int[] nums, int idx, int productMask) {
        if (idx == nums.length) return 1;
        if (dp[idx][productMask] != -1) return dp[idx][productMask];
        long res = dfs(nums, idx+1, productMask);
        int mask = getMask(nums[idx]);
        if ((productMask & mask) == 0) {
            res = (res + dfs(nums, idx+1, productMask | mask)) % MOD;
        }
        return dp[idx][productMask] = res;
    }

    private int getMask(int num) {
        int mask = 0;
        for (int i=0;i<10;i++) {
            int dup = 0;
            while (num%primes[i] == 0) {
                dup++;
                num /= primes[i];
            }
            if (dup > 1) return -1;
            if (dup == 1) mask |= (1 << (i+1));
        }
        return mask;
    }
}
