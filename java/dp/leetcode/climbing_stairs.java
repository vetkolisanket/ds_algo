/*
70. Climbing Stairs

You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?


Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 

Constraints:

1 <= n <= 45
*/

//Soln from another attempt TC O(N) SC O(1)
class Solution {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        int pre = 1, cur = 1, ans = 0;
        for (int i=2;i<=n;i++) {
            ans = cur + pre;
            pre = cur;
            cur = ans;
        }
        return ans;
    }
}

//Attempt after watching dp video by free code camp on YouTube
class Solution {
    public int climbStairs(int n) {
        if (n <= 3) return n;
        int pre = 2, cur = 3;
        for (int i = 4; i <= n; i++) {
            cur += pre;
            pre = cur - pre;
        }
        return cur;
    }
}

//More readable and concise soln
class Solution {
    public int climbStairs(int n) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        return noOfWays(n, map);
    }
    
    private int noOfWays(int n, Map<Integer, Integer> map) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (map.containsKey(n)) return map.get(n);
        int ways = noOfWays(n-1, map) + noOfWays(n-2, map);
        map.put(n, ways);
        return ways;
    }
    
}

//Older soln
class Solution {
    public int climbStairs(int n) {
        int[] arr = new int[n];
        int ways = computeDistinctWays(n-1, arr) + computeDistinctWays(n-2, arr);
        return ways;
    }
    
    private int computeDistinctWays(int n, int[] arr) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else {
            int ways = 0;
            if (arr[n] != 0) {
                ways = arr[n];
            } else {
                ways = computeDistinctWays(n-1, arr) + computeDistinctWays(n-2, arr);
                arr[n] = ways;
            }
            return ways;
        }
    }
    
}

/*
The problem seems to be a dynamic programming one. Hint: the tag also suggests that!
Here are the steps to get the solution incrementally.

Base cases:
if n <= 0, then the number of ways should be zero.
if n == 1, then there is only way to climb the stair.
if n == 2, then there are two ways to climb the stairs. One solution is one step by another; the other one is two steps at one time.

The key intuition to solve the problem is that given a number of stairs n, if we know the number ways to get to the points [n-1] and [n-2] respectively, denoted as n1 and n2 , then the total ways to get to the point [n] is n1 + n2. Because from the [n-1] point, we can take one single step to reach [n]. And from the [n-2] point, we could take two steps to get there.

The solutions calculated by the above approach are complete and non-redundant. The two solution sets (n1 and n2) cover all the possible cases on how the final step is taken. And there would be NO overlapping among the final solutions constructed from these two solution sets, because they differ in the final step.

Now given the above intuition, one can construct an array where each node stores the solution for each number n. Or if we look at it closer, it is clear that this is basically a fibonacci number, with the starting numbers as 1 and 2, instead of 1 and 1.

The implementation in Java as follows:

public int climbStairs(int n) {
    // base cases
    if(n <= 0) return 0;
    if(n == 1) return 1;
    if(n == 2) return 2;
    
    int one_step_before = 2;
    int two_steps_before = 1;
    int all_ways = 0;
    
    for(int i=2; i<n; i++){
    	all_ways = one_step_before + two_steps_before;
    	two_steps_before = one_step_before;
        one_step_before = all_ways;
    }
    return all_ways;
}

public int climbStairs(int n) {
    int a = 1, b = 1;
    while (n-- > 0)
        a = (b += a) - a;
    return a;
}
*/
