/*
Egg Dropping Puzzle

You are given N identical eggs and you have access to a K-floored building from 1 to K.

There exists a floor f where 0 <= f <= K such that any egg dropped at a floor higher than f will break, and any egg dropped at or below floor f will not break. There are few rules given below. 

An egg that survives a fall can be used again.
A broken egg must be discarded.
The effect of a fall is the same for all eggs.
If the egg doesn't break at a certain floor, it will not break at any floor below.
If the eggs breaks at a certain floor, it will break at any floor above.
Return the minimum number of moves that you need to determine with certainty what the value of f is.

For more description on this problem see wiki page

Example 1:

Input:
N = 1, K = 2
Output: 2
Explanation: 
1. Drop the egg from floor 1. If it 
   breaks, we know that f = 0.
2. Otherwise, drop the egg from floor 2.
   If it breaks, we know that f = 1.
3. If it does not break, then we know f = 2.
4. Hence, we need at minimum 2 moves to
   determine with certainty what the value of f is.
Example 2:

Input:
N = 2, K = 10
Output: 4
Your Task:
Complete the function eggDrop() which takes two positive integer N and K as input parameters and returns the minimum number of attempts you need in order to find the critical floor.

Expected Time Complexity : O(N*(K^2))
Expected Auxiliary Space: O(N*K)

Constraints:
1<=N<=200
1<=K<=200
*/

class Solution 
{
    //Function to find minimum number of attempts needed in 
    //order to find the critical floor.
    static int eggDrop(int n, int k) 
	{
	    // Your code here
	    int[][] dp = new int[n+1][k+1];
	    for (int i=0;i<=n;i++) {
	        for (int j=0;j<=k;j++) {
	            //If floors or eggs are 0 return 0
	            if (i==0||j==0) dp[i][j] = 0;
	            //If floors are 1 return 1
	            else if (j == 1) dp[i][j] = 1;
	            //If eggs are 1 we need to check at each floor, so return j
	            else if (i == 1) dp[i][j] = j;
	            else {
	                int ans = Integer.MAX_VALUE;
	                //Iterative step, to compute dp[i][j] we need to check from
	                //floor 1 upto floor j
	                for (int f=1;f<=j;f++) {
	                    //dp[i-1][f-1] -> Egg broke at floor f, so we need to 
	                    //check remaining f-1 floors below with i-1 eggs
	                    //dp[i][j-f] -> Egg did not break at floor f, so we need
	                    //to check remaining j-f floors above with i eggs
	                    //We don't know if the egg will break at floor f so we 
	                    //assume the worst hence 
	                    //Math.max(dp[i-1][f-1], dp[i][j-f]). We add +1 for the
	                    //move we do at dp[i][f]. Finally we compute the minimum
	                    //moves required from floor 1 to j using 
	                    //Math.min(ans, val)
	                    int val = 1 + Math.max(dp[i-1][f-1], dp[i][j-f]);
	                    ans = Math.min(ans, val);
	                }
	                dp[i][j] = ans;
	            }
	        }
	    }
	    return dp[n][k];
	}
}
