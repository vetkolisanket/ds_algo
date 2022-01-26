/*
62. Unique Paths

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

 

Example 1:


Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
Example 3:

Input: m = 7, n = 3
Output: 28
Example 4:

Input: m = 3, n = 3
Output: 6
 

Constraints:

1 <= m, n <= 100
It's guaranteed that the answer will be less than or equal to 2 * 109.
*/

//Another attempt after watching dp video of freeCodeCamp from youtube
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] arr = new int[m][n];
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (i == 0 || j == 0) arr[i][j] = 1;
                else arr[i][j] = arr[i-1][j] + arr[i][j-1];
            }
        }
        return arr[m-1][n-1];
    }
    
}

//My soln on another attempt
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] arr = new int[m][n];
        int paths = computePaths(arr, 0, 0, m, n);
        return paths;
    }
    
    private int computePaths(int[][] arr, int i, int j, int m, int n) {
        if (i == m-1 || j == n-1) return 1;
        if (arr[i][j] > 0) return arr[i][j];
        int paths = computePaths(arr, i+1, j, m, n) + computePaths(arr, i, j+1, m, n);
        arr[i][j] = paths;
        return arr[i][j];
    }
    
}

//My soln using memoization
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] arr = new int[m][n];
        int paths = computePaths(arr, 0, 0, 1);
        return paths;
    }
    
    private int computePaths(int[][] arr, int i, int j, int paths) {
        if (i == arr.length-1 || j == arr[0].length-1) return paths;
        else {
            int rightPaths = arr[i][j+1] > 0 ? arr[i][j+1] : computePaths(arr, i, j+1, paths);
            int downPaths = arr[i+1][j] > 0 ? arr[i+1][j] : computePaths(arr, i+1, j, paths);
            arr[i][j] = rightPaths + downPaths;
            return rightPaths + downPaths;
        }
    }
}

//A better memoization soln
class Solution {
  public int uniquePaths(int m, int n) {
    return uniquePathsHelper(m - 1, n - 1, new int[n][m]);
  }
  
  private int uniquePathsHelper(int m, int n, int[][] memo) {
    if (m < 0 || n < 0) {
      return 0;
    } else if (m == 0 || n == 0) {
      return 1;
    } else if (memo[n][m] > 0) {
      return memo[n][m];
    } else {
      memo[n][m] = uniquePathsHelper(m - 1, n, memo) + uniquePathsHelper(m, n - 1, memo);
      return memo[n][m];
    }
  }
} 

//Math soln
This is a combinatorial problem and can be solved without DP. For mxn grid, robot has to move exactly m-1 steps down and n-1 steps right and these can be done in any order.

For the eg., given in question, 3x7 matrix, robot needs to take 2+6 = 8 steps with 2 down and 6 right in any order. That is nothing but a permutation problem. Denote down as 'D' and right as 'R', following is one of the path :-

D R R R D R R R

We have to tell the total number of permutations of the above given word. So, decrease both m & n by 1 and apply following formula:-

Total permutations = (m+n)! / (m! * n!)

Following is my code doing the same :-

public class Solution {
    public int uniquePaths(int m, int n) {
        if(m == 1 || n == 1)
            return 1;
        m--;
        n--;
        if(m < n) {              // Swap, so that m is the bigger number
            m = m + n;
            n = m - n;
            m = m - n;
        }
        long res = 1;
        int j = 1;
        for(int i = m+1; i <= m+n; i++, j++){       // Instead of taking factorial, keep on multiply & divide
            res *= i;
            res /= j;
        }
            
        return (int)res;
    }
}

//DP soln
class Solution {
  public int uniquePaths(int m, int n) {
    int[][] grid = new int[n][m];
    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (i == 0) grid[0][j] = 1;
        if (j == 0) grid[i][j] = 1;
        if (i != 0 && j != 0) {
          int up = grid[i - 1][j];
          int left = grid[i][j - 1];
          grid[i][j] = up + left;
        }
      }
    }
    return grid[n - 1][m - 1];
  }
} 
