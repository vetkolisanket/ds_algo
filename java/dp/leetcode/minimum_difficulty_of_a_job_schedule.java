/*
1335. Minimum Difficulty of a Job Schedule

You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).

You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.

You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].

Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.

 

Example 1:


Input: jobDifficulty = [6,5,4,3,2,1], d = 2
Output: 7
Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
Second day you can finish the last job, total difficulty = 1.
The difficulty of the schedule = 6 + 1 = 7 
Example 2:

Input: jobDifficulty = [9,9,9], d = 4
Output: -1
Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
Example 3:

Input: jobDifficulty = [1,1,1], d = 3
Output: 3
Explanation: The schedule is one job per day. total difficulty will be 3.
 

Constraints:

1 <= jobDifficulty.length <= 300
0 <= jobDifficulty[i] <= 1000
1 <= d <= 10
*/

//DP soln using top down approach TC O(N^2*D) SC O(ND) 
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) return -1;
        
        int[][] mem = new int[n][d+1];
        for (int i=0;i<n;i++) {
            for (int j=0;j<=d;j++) {
                mem[i][j] = -1;
            }
        }
        
        return minDiff(0, d, jobDifficulty, mem);
    }
    
    private int minDiff(int i, int daysRemaining, int[] jobDifficulty, int[][] mem) {
        if (mem[i][daysRemaining] != -1) return mem[i][daysRemaining];
        
        if (daysRemaining == 1) {
            int res = 0;
            for (int j=i;j<jobDifficulty.length;j++) {
                res = Math.max(res, jobDifficulty[j]);
            }
            return res;
        }
        
        int res = Integer.MAX_VALUE;
        int dailyMaxJobDiff = 0;
        for (int j=i;j<jobDifficulty.length - daysRemaining + 1; j++) {
            dailyMaxJobDiff = Math.max(dailyMaxJobDiff, jobDifficulty[j]);
            res = Math.min(res, dailyMaxJobDiff + minDiff(j+1, daysRemaining-1, jobDifficulty, mem));
        }
        mem[i][daysRemaining] = res;
        return res;
    }
}


