/*
633. Sum of Square Numbers

Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.

 

Example 1:

Input: c = 5
Output: true
Explanation: 1 * 1 + 2 * 2 = 5
Example 2:

Input: c = 3
Output: false
 

Constraints:

0 <= c <= 2^31 - 1
*/

//Soln using math and binary search TC O(sqrt(c)logc) SC O(logc)
class Solution {
    public boolean judgeSquareSum(int c) {
        for (long a=0;a*a<=c;a++) {
            int b = c - (int)(a*a);
            if (binarySearch(0, b, b)) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearch(long s, long e, int val) {
        if (s > e) return false;
        long mid = s + (e-s)/2;
        if (mid*mid == val) return true;
        else if (mid*mid > val) return binarySearch(s, mid-1, val);
        return binarySearch(mid+1, e, val);
    }
}
