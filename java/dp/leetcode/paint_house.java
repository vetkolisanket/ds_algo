/*
256. Paint House

There is a row of n houses, where each house can be painted one of three colors: red, blue, or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.

For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
Return the minimum cost to paint all houses.

 

Example 1:

Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
Minimum cost: 2 + 5 + 3 = 10.
Example 2:

Input: costs = [[7,6,2]]
Output: 2
 

Constraints:

costs.length == n
costs[i].length == 3
1 <= n <= 100
1 <= costs[i][j] <= 20

*/

//My soln TC O(N) SC O(N)
class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n][3];
        for (int i=0;i<3;i++) {
            dp[0][i] = costs[0][i];
        }
        for (int i=1;i<n;i++) {
            for (int j=0;j<3;j++) {
                dp[i][j] = costs[i][j] + Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]);
            }
        }
        return Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2]));
    }
}

//A more space efficient soln which pollutes the input costs matrix TC O(N) SC O(1)
class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        for (int i=1;i<n;i++) {
            for (int j=0;j<3;j++) {
                costs[i][j] += Math.min(costs[i-1][(j+1)%3], costs[i-1][(j+2)%3]);
            }
        }
        return Math.min(costs[n-1][0], Math.min(costs[n-1][1], costs[n-1][2]));
    }
}

//Space efficient soln which doesn't alter costs matrix TC O(N) SC O(1)
class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        int cost0 = costs[0][0], cost1 = costs[0][1], cost2 = costs[0][2];
        for (int i=1;i<n;i++) {
            int temp0 = costs[i][0] + Math.min(cost1, cost2);
            int temp1 = costs[i][1] + Math.min(cost0, cost2);
            int temp2 = costs[i][2] + Math.min(cost0, cost1);
            cost0 = temp0;
            cost1 = temp1;
            cost2 = temp2;
        }
        return Math.min(cost0, Math.min(cost1, cost2));
    }
}
