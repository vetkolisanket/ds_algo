/*
Steps by Knight

Given a square chessboard, the initial position of Knight and position of a target. Find out the minimum steps a Knight will take to reach the target position.

Note:
The initial and the target position coordinates of Knight have been given according to 1-base indexing.

 

Example 1:

Input:
N=6
knightPos[ ] = {4, 5}
targetPos[ ] = {1, 1}
Output:
3
Explanation:

Knight takes 3 step to reach from 
(4, 5) to (1, 1):
(4, 5) -> (5, 3) -> (3, 2) -> (1, 1).
 

 

Your Task:
You don't need to read input or print anything. Your task is to complete the function minStepToReachTarget() which takes the initial position of Knight (KnightPos), the target position of Knight (TargetPos), and the size of the chessboard (N) as input parameters and returns the minimum number of steps required by the knight to reach from its current position to the given target position.

 

Expected Time Complexity: O(N2).
Expected Auxiliary Space: O(N2).

 

Constraints:
1 <= N <= 1000
1 <= Knight_pos(X, Y), Targer_pos(X, Y) <= N
*/

//Slight change in soln to make it better/faster
// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            int N = Integer.parseInt(br.readLine().trim());
            String[] S1 = br.readLine().trim().split(" ");
            String[] S2 = br.readLine().trim().split(" ");
            int[] KnightPos = new int[2];
            int[] TargetPos = new int[2];
            for(int i = 0; i < 2; i++){
                KnightPos[i] = Integer.parseInt(S1[i]);
                TargetPos[i] = Integer.parseInt(S2[i]);
            }
            Solution obj = new Solution();
            int ans = obj.minStepToReachTarget(KnightPos, TargetPos, N);
            System.out.println(ans);
       }
    }
}
// } Driver Code Ends


class Solution
{
    //Function to find out minimum steps Knight needs to reach target position.
    public int minStepToReachTarget(int KnightPos[], int TargetPos[], int N)
    {
        // Code here
        if (KnightPos[0] == TargetPos[0] && KnightPos[1] == TargetPos[1]) {
            return 0;
        }
        boolean[][] visited = new boolean[N+1][N+1];
        Queue<int[]> queue = new ArrayDeque();
        int minSteps = 1;
        visited[KnightPos[0]][KnightPos[1]] = true;
        queue.offer(KnightPos);
        int[][] steps = {{-1,-2}, {-2,-1}, {-2,1}, {-1,2}, {1,2}, {2,1}, {2,-1}, 
            {1,-2}
        };
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int[] pos = queue.poll();
                for (int[] step: steps) {
                    int dx = pos[0] + step[0];
                    int dy = pos[1] + step[1];
                    if (dx > 0 && dx <= N && dy > 0 && dy <= N && 
                    !visited[dx][dy]) {
                        if (dx == TargetPos[0] && dy == TargetPos[1]) {
                            return minSteps;
                        }
                        visited[dx][dy] = true;
                        queue.offer(new int[]{dx, dy});
                    }
                }
                size--;
            }
            minSteps++;
        }
        return -1;
    }
}

class Solution
{
    //Function to find out minimum steps Knight needs to reach target position.
    public int minStepToReachTarget(int KnightPos[], int TargetPos[], int N)
    {
        // Code here
        boolean[][] visited = new boolean[N+1][N+1];
        visited[KnightPos[0]][KnightPos[1]] = true;
        int steps = 0;
        Queue<int[]> q = new ArrayDeque();
        q.offer(KnightPos);
        
        int[][] dir = {{-2,-1}, {-2,1}, {-1,-2}, {-1,2}, {1,-2}, {1,2}, {2,-1},
        {2,1}};
        
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] pos = q.poll();
                if (pos[0] == TargetPos[0] && pos[1] == TargetPos[1]) return steps;
                for (int i=0;i<8;i++) {
                    int row = pos[0] + dir[i][0];
                    int col = pos[1] + dir[i][1];
                    if (row > 0 && row <= N && col > 0 && col <= N && 
                    !visited[row][col]) {
                        visited[row][col] = true;
                        q.offer(new int[]{row, col});
                    }
                }
            }
            steps++;
        }
        
        return -1;
    }
}
