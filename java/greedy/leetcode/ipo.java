/*
502. IPO

Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.

You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.

Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.

Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.

The answer is guaranteed to fit in a 32-bit signed integer.

 

Example 1:

Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
Output: 4
Explanation: Since your initial capital is 0, you can only start the project indexed 0.
After finishing it you will obtain profit 1 and your capital becomes 1.
With capital 1, you can either start the project indexed 1 or the project indexed 2.
Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
Example 2:

Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
Output: 6
 

Constraints:

1 <= k <= 10^5
0 <= w <= 10^9
n == profits.length
n == capital.length
1 <= n <= 10^5
0 <= profits[i] <= 10^4
0 <= capital[i] <= 10^9
*/

//My soln using priority queue
class Solution {

    class Holder {

        int capital, profit;

        public Holder(int capital, int profit) {
            this.capital = capital;
            this.profit = profit;
        }

    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        Holder[] holders = new Holder[n];
        for (int i=0;i<n;i++) {
            holders[i] = new Holder(capital[i], profits[i]);
        }
        Arrays.sort(holders, (a,b) -> a.capital - b.capital);
        int ans = w;
        PriorityQueue<Holder> pq = new PriorityQueue<Holder>((a, b) -> b.profit - a.profit);
        int i = 0;
        while (k > 0) {
            while (i < n && holders[i].capital <= ans) {
                pq.offer(holders[i++]);
            }
            if (pq.isEmpty()) break;
            Holder holder = pq.poll();
            ans += holder.profit;
            k--;
        }
        return ans;
    }
}

