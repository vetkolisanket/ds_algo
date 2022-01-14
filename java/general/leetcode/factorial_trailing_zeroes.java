/*
172. Factorial Trailing Zeroes

Given an integer n, return the number of trailing zeroes in n!.

Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.

 

Example 1:

Input: n = 3
Output: 0
Explanation: 3! = 6, no trailing zero.
Example 2:

Input: n = 5
Output: 1
Explanation: 5! = 120, one trailing zero.
Example 3:

Input: n = 0
Output: 0
 

Constraints:

0 <= n <= 10^4
 

Follow up: Could you write a solution that works in logarithmic time complexity?
*/

//Another O(n) soln which might be faster
class Solution {
    public int trailingZeroes(int n) {
        int a = 0;
        while (n > 0) {
            n = n/5;
            a += n;
        }
        return a;
    }
}

//O(lg(n)) soln
class Solution {
    public int trailingZeroes(int n) {
        int res = 0;
        int num = 5;
        while (num <= n) {
            res += n/num;
            num *= 5;
        }
        return res;
    }
}

//My O(n) soln
class Solution {
    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 4) {
            int val = n;
            while (val%10==0) {
                res++;
                val /= 10;
            }
            while (val%5==0) {
                res++;
                val /= 5;
            }
            n--;
        }
        return res;
    }
}
