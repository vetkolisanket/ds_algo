/*
735. Asteroid Collision

We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

 

Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 

Constraints:

2 <= asteroids.length <= 10^4
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
*/

//Soln using stack TC O(N) SC O(N)
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack();
        for (int val: asteroids) {
            if (stack.isEmpty()) {
                stack.push(val);
            } else {
                while(!stack.isEmpty()) {
                    int top = stack.peek();
                    if ((top < 0 && val < 0) || val > 0) {
                        stack.push(val);
                        break;
                    } else if (Math.abs(top) == Math.abs(val)) {
                        stack.pop();
                        break;
                    } else if (Math.abs(top) < Math.abs(val)) {
                        stack.pop();
                        if (stack.isEmpty()) {
                            stack.push(val);
                            break;
                        }
                    } else break;
                }
            }
        }
        int[] ans = new int[stack.size()];
        for (int i=ans.length-1;i>=0;i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }
}
