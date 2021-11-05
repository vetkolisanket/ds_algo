/*
441. Arranging Coins

You have n coins and you want to build a staircase with these coins. The staircase consists of k rows where the ith row has exactly i coins. The last row of the staircase may be incomplete.

Given the integer n, return the number of complete rows of the staircase you will build.

 

Example 1:


Input: n = 5
Output: 2
Explanation: Because the 3rd row is incomplete, we return 2.
Example 2:


Input: n = 8
Output: 3
Explanation: Because the 4th row is incomplete, we return 3.
 

Constraints:

1 <= n <= 231 - 1
*/

class Solution {
    public int arrangeCoins(int n) {
        return (int) ((Math.sqrt(1. + 8.*n) - 1)/2.);
    }
}

class Solution {
    public int arrangeCoins(int n) {
        long low = 1, high = n;
        while (low <= high) {
            long mid = (low+high)/2;
            long sum = mid*(mid+1)/2;
            
            if (sum == n) return (int) mid;
            else if (sum > n) high = mid - 1;
            else low = mid + 1;
        }
        
        return (int) high;
    }
}

class Solution {
    public int arrangeCoins(int n) {
        int i = 0;
        while (n > i) {
            i++;
            n -= i;
        }
        return i;
    }
}


