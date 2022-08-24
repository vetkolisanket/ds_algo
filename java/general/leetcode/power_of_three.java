/*
326. Power of Three

Given an integer n, return true if it is a power of three. Otherwise, return false.

An integer n is a power of three, if there exists an integer x such that n == 3x.

 

Example 1:

Input: n = 27
Output: true
Example 2:

Input: n = 0
Output: false
Example 3:

Input: n = 9
Output: true
 

Constraints:

-231 <= n <= 231 - 1
 

Follow up: Could you solve it without loops/recursion?
*/

//My soln TC O(log(n)) SC O(1)
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n < 1) return false;
        while (n > 1) {
            if (n%3 != 0) return false;
            n /= 3;
        }
        return true;
    }
}

//Loop iteration TC O(log(n)) SC O(1)
public class Solution {
    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }
}

//Base conversion TC O(log(n)) SC O(log(n))
public class Solution {
    public boolean isPowerOfThree(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }
}

//Logarithms TC O(log(n)) SC O(1)
public class Solution {
    public boolean isPowerOfThree(int n) {
        return (Math.log(n) / Math.log(3) + epsilon) % 1 <= 2 * epsilon;
    }
}

//Integer limitations approach TC O(1) SC O(1)
public class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
