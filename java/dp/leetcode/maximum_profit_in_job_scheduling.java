/*
1235. Maximum Profit in Job Scheduling

We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.

 

Example 1:



Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job. 
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
Example 2:



Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job. 
Profit obtained 150 = 20 + 70 + 60.
Example 3:



Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6
 

Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
1 <= startTime[i] < endTime[i] <= 10^9
1 <= profit[i] <= 10^4

*/

//Soln using top down DP + Binary search TC O(Nlog(N)) SC O(N)
class Solution {
    // maximum number of jobs are 50000
    int[] memo = new int[50001];
    
    private int findNextJob(int[] startTime, int lastEndingTime) {
        int start = 0, end = startTime.length - 1, nextIndex = startTime.length;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            if (startTime[mid] >= lastEndingTime) {
                nextIndex = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return nextIndex;
    }
    
    private int findMaxProfit(List<List<Integer>> jobs, int[] startTime, int n, int position) {
        // 0 profit if we have already iterated over all the jobs
        if (position == n) {
            return 0;
        }
        
        // return result directly if it's calculated 
        if (memo[position] != -1) {
            return memo[position];
        }
        
        // nextIndex is the index of next non-conflicting job
        int nextIndex = findNextJob(startTime, jobs.get(position).get(1));
        
        // find the maximum profit of our two options: skipping or scheduling the current job
        int maxProfit = Math.max(findMaxProfit(jobs, startTime, n, position + 1), 
                        jobs.get(position).get(2) + findMaxProfit(jobs, startTime, n, nextIndex));
        
        // return maximum profit and also store it for future reference (memoization)
        return memo[position] = maxProfit;
    }
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<List<Integer>> jobs = new ArrayList<>();
        
        // marking all values to -1 so that we can differentiate 
        // if we have already calculated the answer or not
        Arrays.fill(memo, -1);
        
        // storing job's details into one list 
        // this will help in sorting the jobs while maintaining the other parameters
        int length = profit.length;
        for (int i = 0; i < length; i++) {
            ArrayList<Integer> currJob = new ArrayList<>();
            currJob.add(startTime[i]);
            currJob.add(endTime[i]);
            currJob.add(profit[i]);
            jobs.add(currJob);
        }
        jobs.sort(Comparator.comparingInt(a -> a.get(0)));
        
        // binary search will be used in startTime so store it as separate list
        for (int i = 0; i < length; i++) {
            startTime[i] = jobs.get(i).get(0);
        }
        
        return findMaxProfit(jobs, startTime, length, 0);
    }
}

//My soln from another attempt when i was not able to solve on my own and had to revisit the soln TC O(Nlog(N)) SC O(N)
class Solution {

    Integer[] memo;

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        memo = new Integer[n];
        int[][] jobs = new int[n][3];
        for (int i=0;i<n;i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        return maxProfit(jobs, 0);
    }

    private int maxProfit(int[][] jobs, int position) {
        if (position == jobs.length) return 0;
        if (memo[position] != null) return memo[position];

        int nextIndex = findNextIndex(jobs, jobs[position][1]);
        int maxProfit = Math.max(maxProfit(jobs, position+1), jobs[position][2] + maxProfit(jobs, nextIndex));
        return memo[position] = maxProfit;
    }

    private int findNextIndex(int[][] jobs, int endTime) {
        int start = 0, end = jobs.length-1, nextIndex = jobs.length;
        while (start <= end) {
            int mid = start + (end-start)/2;
            if (jobs[mid][0] >= endTime) {
                nextIndex = mid;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return nextIndex;
    }
}

//My soln after understanding bottom up dp with binary search and attempting on my own TC O(Nlog(N)) SC O(N)
class Solution {

    Integer[] memo;

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        memo = new Integer[n+1];
        int[][] jobs = new int[n][3];
        for (int i=0;i<n;i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        return maxProfit(jobs);
    }

    private int maxProfit(int[][] jobs) {
        for (int i=jobs.length-1;i>=0;i--) {
            if (i == jobs.length-1) {
                memo[i] = jobs[i][2];
                continue;
            }
            int nextIndex = findNextIndex(jobs, jobs[i][1]);
            int curProfit = 0;
            if (nextIndex == jobs.length) {
                curProfit = jobs[i][2];
            } else {
                curProfit = jobs[i][2] + memo[nextIndex];
            }
            int maxProfit = Math.max(curProfit, memo[i+1]);
            memo[i] = maxProfit;
        }
        return memo[0];
    }

    private int findNextIndex(int[][] jobs, int endTime) {
        int start = 0, end = jobs.length-1, nextIndex = jobs.length;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (jobs[mid][0] >= endTime) {
                nextIndex = mid;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return nextIndex;
    }
}

//Top down and botton up approach combined soln from another attempt TC O(NlogN) SC O(N)
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i=0;i<n;i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        int[] memo = new int[n];
        // return findMaxProfitTd(jobs, memo, 0);
        return findMaxProfitBu(jobs, memo);
    }

    private int findMaxProfitBu(int[][] jobs, int[] memo) {
        int n = jobs.length;
        for (int i=n-1;i>=0;i--) {
            int nextIdx = findNextIdx(jobs, jobs[i][1]);

            int profit;
            if (nextIdx == n) {
                profit = jobs[i][2];
            } else {
                profit = jobs[i][2] + memo[nextIdx];
            }

            if (i == n-1) {
                memo[i] = profit;
            } else {
                memo[i] = Math.max(profit, memo[i+1]);
            }
        }
        return memo[0];
    }

    private int findMaxProfitTd(int[][] jobs, int[] memo, int pos) {
        if (pos == jobs.length) return 0;

        if (memo[pos] != 0) return memo[pos];

        int nextIdx = findNextIdx(jobs, jobs[pos][1]);

        int maxProfit = Math.max(jobs[pos][2] + findMaxProfitTd(jobs, memo, nextIdx), findMaxProfitTd(jobs, memo, pos+1));

        return memo[pos] = maxProfit;
    }

    private int findNextIdx(int[][] jobs, int time) {
        int start = 0, end = jobs.length-1, idx = jobs.length;

        while (start <= end) {
            int mid = start + (end-start)/2;
            if (jobs[mid][0] >= time) {
                idx = mid;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return idx;
    }
}
