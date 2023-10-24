/*
342. Power of Four

Given an integer n, return true if it is a power of four. Otherwise, return false.

An integer n is a power of four, if there exists an integer x such that n == 4x.

 

Example 1:

Input: n = 16
Output: true
Example 2:

Input: n = 5
Output: false
Example 3:

Input: n = 1
Output: true
 

Constraints:

-2^31 <= n <= 2^31 - 1
 

Follow up: Could you solve it without loops/recursion?
*/

//Soln TC O(logN) SC O(1)
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) return false;
        while (n > 1) {
            if (n%4 != 0) return false;
            n /= 4;
        }
        return true;
    }
}

//Soln using math TC O(1) SC O(1)
class Solution {
    public boolean isPowerOfFour(int n) {
        return n > 0 && (Math.log(n)/Math.log(2) % 2 == 0);
    }
}

//Soln using bitwise operations TC O(1) SC O(1)
class Solution {
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n-1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }
}

//Soln using Math and bitwise manipulation
class Solution {
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n-1)) == 0 && n % 3 == 1;
    }
}
