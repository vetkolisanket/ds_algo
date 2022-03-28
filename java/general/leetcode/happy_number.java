/*
202. Happy Number

Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.

 

Example 1:

Input: n = 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
Example 2:

Input: n = 2
Output: false
 

Constraints:

1 <= n <= 2^31 - 1
*/

//Using Floyd Marshall Cycle Detection Algorithm (Tortoise-Hare Algorithm) (Fast Slow pointer algorithm)
class Solution {
    
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = getSumOfSquareOfDigits(slow);
            fast = getSumOfSquareOfDigits(fast);
            fast = getSumOfSquareOfDigits(fast);
            if (slow == 1 || fast == 1) return true;
        } while (slow != fast);
        return false;
    }
    
    private int getSumOfSquareOfDigits(int n) {
        int num = 0;
        while (n > 0) {
            num += Math.pow(n%10, 2);
            n /= 10;
        }
        return num;
    }
}

//Iteratively
class Solution {
    
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet();
        
        while (set.add(n)) {
            n = getSumOfSquareOfDigits(n);
            if (n == 1) return true;
        }
        return false;
    }
    
    private int getSumOfSquareOfDigits(int n) {
        int num = 0;
        while (n > 0) {
            num += Math.pow(n%10, 2);
            n /= 10;
        }
        return num;
    }
}


//Using recursion
class Solution {
    
    Set<Integer> set = new HashSet();
    
    public boolean isHappy(int n) {
        if (set.contains(n)) return false;
        set.add(n);
        int num = 0;
        while (n > 0) {
            num += Math.pow(n%10, 2);
            n /= 10;
        }
        if (num == 1) return true;
        return isHappy(num);
    }
}
