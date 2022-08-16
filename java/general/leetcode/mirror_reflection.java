/*
858. Mirror Reflection

There is a special square room with mirrors on each of the four walls. Except for the southwest corner, there are receptors on each of the remaining corners, numbered 0, 1, and 2.

The square room has walls of length p and a laser ray from the southwest corner first meets the east wall at a distance q from the 0th receptor.

Given the two integers p and q, return the number of the receptor that the ray meets first.

The test cases are guaranteed so that the ray will meet a receptor eventually.

 

Example 1:


Input: p = 2, q = 1
Output: 2
Explanation: The ray meets receptor 2 the first time it gets reflected back to the left wall.
Example 2:

Input: p = 3, q = 1
Output: 1
 

Constraints:

1 <= q <= p <= 1000
*/

//Another soln with explanation TC O(log(p) + log(q)) SC O(1)
class Solution {
    public int mirrorReflection(int p, int q) {
        /*We want to find m,n such that m*p == n*q, hence we start with m=q and n=p*/
        int m = q, n = p;
        /*We keep on halving m and n up until one of them is odd. We do this to find the minimum value of m and n, since that is where the light will first meet a reflector and that is what we are supposed to find. It can meet other reflectors later, but that is not what we are interested in*/
        while (m%2 == 0 && n%2 == 0) {
            m >>= 1;
            n >>= 1;
        }
        /*If light meets after even reflections, it can only meet reflector 2, since that is the only reflector on that side*/
        if (n%2 == 0) return 2;
        /*If no. of mirrors required is even, light will meet reflector 0*/
        if (m%2 == 0) return 0;
        /*Else it will meet reflector 1*/
        return 1;
    }
}

//Soln with explanation
class Solution {
    public int mirrorReflection(int p, int q) {
        int m = 1, n = 1;
        /*We want to find m, n such m*p == n*q. This will ensure light will reach a corner after n reflections and m mirrors will be required for it to reach corner*/
        while (m*p != n*q) {
            n++;
            m = n*q/p;
        }
        /*If no. of reflections is even, then light will meet receptor 2, as that is the only receptor on even side*/
        if (n%2 == 0) return 2;
        /*If the no. of mirrors required is even, the light will meet receptor 0*/
        if (m%2 == 0) return 0;
        /*If the no. of mirrors required is odd, the light will meet receptor 1*/
        return 1;
    }
}

//TC O(log(p)) SC O(1)
class Solution {
    public int mirrorReflection(int p, int q) {
        while (p%2 == 0 && q%2 == 0) {
            p >>= 1;
            q >>=1;
        }
        return 1 - p%2 + q%2;
    }
}
