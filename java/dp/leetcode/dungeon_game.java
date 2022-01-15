/*
174. Dungeon Game

The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of m x n rooms laid out in a 2D grid. Our valiant knight was initially positioned in the top-left room and must fight his way through dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons (represented by negative integers), so the knight loses health upon entering these rooms; other rooms are either empty (represented as 0) or contain magic orbs that increase the knight's health (represented by positive integers).

To reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

Return the knight's minimum initial health so that he can rescue the princess.

Note that any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.

 

Example 1:


Input: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
Output: 7
Explanation: The initial health of the knight must be at least 7 if he follows the optimal path: RIGHT-> RIGHT -> DOWN -> DOWN.
Example 2:

Input: dungeon = [[0]]
Output: 1
 

Constraints:

m == dungeon.length
n == dungeon[i].length
1 <= m, n <= 200
-1000 <= dungeon[i][j] <= 1000
*/

//Top down approach
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        for (int[] arr : dp) Arrays.fill(arr, Integer.MAX_VALUE);
        
        return getVal(dungeon, 0, 0, dp);
    }
    
    private int getVal(int[][] dungeon, int i, int j, int[][] dp) {
        if (i == dungeon.length || j == dungeon[0].length) return Integer.MAX_VALUE;
        
        if (dp[i][j] != Integer.MAX_VALUE) return dp[i][j];
        
        if (i == dungeon.length-1 && j == dungeon[0].length-1) {
            dp[i][j] = dungeon[i][j] <= 0 ? -dungeon[i][j]+1 : 1;
            return dp[i][j];
        }
        
        int goRight = getVal(dungeon, i, j+1, dp);
        int goDown = getVal(dungeon, i+1, j, dp);
        
        int minHealthRequired = Math.min(goRight, goDown) - dungeon[i][j];
        
        dp[i][j] = minHealthRequired > 0 ? minHealthRequired : 1;
        return dp[i][j];
    }
}

//Bottom up approach
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m+1][n+1];
        for (int[] arr : dp) Arrays.fill(arr, Integer.MAX_VALUE);
        dp[m][n-1] = 1;
        dp[m-1][n] = 1;
        
        for (int i=m-1;i>=0;i--) {
            for (int j=n-1;j>=0;j--) {
                int need = Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j];
                dp[i][j] = need <= 0 ? 1 : need;
            }
        }
        return dp[0][0];
    }
}
