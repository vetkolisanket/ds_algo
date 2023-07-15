/*
1751. Maximum Number of Events That Can Be Attended II

You are given an array of events where events[i] = [startDayi, endDayi, valuei]. The ith event starts at startDayi and ends at endDayi, and if you attend this event, you will receive a value of valuei. You are also given an integer k which represents the maximum number of events you can attend.

You can only attend one event at a time. If you choose to attend an event, you must attend the entire event. Note that the end day is inclusive: that is, you cannot attend two events where one of them starts and the other ends on the same day.

Return the maximum sum of values that you can receive by attending events.

 

Example 1:



Input: events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
Output: 7
Explanation: Choose the green events, 0 and 1 (0-indexed) for a total value of 4 + 3 = 7.
Example 2:



Input: events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
Output: 10
Explanation: Choose event 2 for a total value of 10.
Notice that you cannot attend any other event as they overlap, and that you do not have to attend k events.
Example 3:



Input: events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
Output: 9
Explanation: Although the events do not overlap, you can only attend 3 events. Pick the highest valued three.
 

Constraints:

1 <= k <= events.length
1 <= k * events.length <= 10^6
1 <= startDayi <= endDayi <= 10^9
1 <= valuei <= 10^6

*/

//Soln using top down dp and binary search TC O(NKlogN) SC (NK)
class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a,b) -> a[0] - b[0]);
        int n = events.length;
        int[][] dp = new int[k+1][n];
        for (int[] row: dp) Arrays.fill(row, -1);
        return dfs(0, k, events, dp);
    }

    private int dfs(int curIdx, int count, int[][] events, int[][] dp) {
        if (count == 0 || curIdx == events.length) return 0;
        if (dp[count][curIdx] != -1) return dp[count][curIdx];
        int nextIdx = find(events, events[curIdx][1]);
        dp[count][curIdx] = Math.max(dfs(curIdx+1, count, events, dp), events[curIdx][2] + dfs(nextIdx, count-1, events, dp));
        return dp[count][curIdx];
    }

    private int find(int[][] events, int target) {
        int left = 0, right = events.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (events[mid][0] <= target) {
                left = mid+1;
            } else right = mid;
        }
        return left;
    }
}

//Soln using bottom up dp TC O(NKlogN) SC O(NK)
class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a,b) -> a[0] - b[0]);
        int n = events.length;
        int[][] dp = new int[k+1][n+1];
        for (int curIdx=n-1;curIdx>=0;curIdx--) {
            for (int count=1;count<=k;count++) {
                int nextIdx = find(events, events[curIdx][1]);
                dp[count][curIdx] = Math.max(dp[count][curIdx+1], events[curIdx][2] + dp[count-1][nextIdx]);
            }
        }
        return dp[k][0];
    }

    private int find(int[][] events, int target) {
        int left = 0, right = events.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (events[mid][0] <= target) {
                left = mid+1;
            } else right = mid;
        }
        return left;
    }
}

//Soln using top down dp and cached binary search result TC O(N(K + logN)) SC O(NK)
class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a,b) -> a[0] - b[0]);
        int n = events.length;
        int[] nextIndices = new int[n];
        for (int i=0;i<n;i++) {
            nextIndices[i] = find(events, events[i][1]);
        }
        int[][] dp = new int[k+1][n];
        for (int[] row: dp) Arrays.fill(row, -1);
        return dfs(0, k, events, nextIndices, dp);
    }

    private int find(int[][] events, int target) {
        int left = 0, right = events.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (events[mid][0] <= target) {
                left = mid+1;
            } else right = mid;
        }
        return left;
    }

    private int dfs(int curIdx, int count, int[][] events, int[] nextIndices, int[][] dp) {
        if (count == 0 || curIdx == events.length) return 0;
        if (dp[count][curIdx] != -1) return dp[count][curIdx];
        dp[count][curIdx] = Math.max(dfs(curIdx+1, count, events, nextIndices, dp), events[curIdx][2] + dfs(nextIndices[curIdx], count-1, events, nextIndices, dp));
        return dp[count][curIdx];
    }
}

//Soln using bottom up dp and cached binary search result TC O(N(K+logN)) SC O(NK)
class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a,b) -> a[0] - b[0]);
        int n = events.length;
        int[] nextIndices = new int[n];
        for (int i=0;i<n;i++) {
            nextIndices[i] = find(events, events[i][1]);
        }
        int[][] dp = new int[k+1][n+1];
        for (int curIdx=n-1;curIdx>=0;curIdx--) {
            for (int count=1;count<=k;count++) {
                dp[count][curIdx] = Math.max(dp[count][curIdx+1], events[curIdx][2] + dp[count-1][nextIndices[curIdx]]);
            }
        }
        return dp[k][0];
    }

    private int find(int[][] events, int target) {
        int left = 0, right = events.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (events[mid][0] <= target) {
                left = mid+1;
            } else right = mid;
        }
        return left;
    }
}

//Soln top down dp and linear search TC O(N(k+logN)) SC O(NK)
class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a,b) -> a[0] - b[0]);
        int n = events.length;
        int[][] dp = new int[k+1][n];
        for (int[] row: dp) Arrays.fill(row, -1);
        return dfs(0, k, events, dp, -1);
    }

    private int dfs(int curIdx, int count, int[][] events, int[][] dp, int prevElemEndTime) {
        if (count == 0 || curIdx == events.length) return 0;
        if (prevElemEndTime >= events[curIdx][0]) return dfs(curIdx+1, count, events, dp, prevElemEndTime);
        if (dp[count][curIdx] != -1) return dp[count][curIdx];
        dp[count][curIdx] = Math.max(dfs(curIdx+1, count, events, dp, prevElemEndTime), events[curIdx][2] + dfs(curIdx+1, count-1, events, dp, events[curIdx][1]));
        return dp[count][curIdx];
    }
}
