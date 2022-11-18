/*
263. Ugly Number

An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return true if n is an ugly number.

 

Example 1:

Input: n = 6
Output: true
Explanation: 6 = 2 × 3
Example 2:

Input: n = 1
Output: true
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
Example 3:

Input: n = 14
Output: false
Explanation: 14 is not ugly since it includes the prime factor 7.
 

Constraints:

-2^31 <= n <= 2^31 - 1
*/

//Soln TC O(log(N)) SC O(1)
class Solution {
    public boolean isUgly(int n) {
        if (n <= 0) return false;
        for (int num: new int[]{2, 3, 5}) {
            while (n % num == 0) {
                n /= num;
            }
        }
        return n == 1;
    }
}
