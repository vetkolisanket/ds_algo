/*
509. Fibonacci Number

The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
Given n, calculate F(n).

 

Example 1:

Input: n = 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
Example 2:

Input: n = 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
Example 3:

Input: n = 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 

Constraints:

0 <= n <= 30
*/

//Iterative soln
class Solution {
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        int fn = 0, fn1 = 1, fn2 = 0;
        for (int i=2; i<=n; i++) {
            fn = fn1 + fn2;
            fn2 = fn1;
            fn1 = fn;
        }
        return fn;
    }
}

//Recursive soln
class Solution {
    
    Map<Integer, Integer> map = new HashMap();
    
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (map.containsKey(n)) return map.get(n);
        
        int ans = fib(n-1) + fib(n-2);
        map.put(n, ans);
        return ans;
    }
}
