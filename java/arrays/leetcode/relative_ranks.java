/*
506. Relative Ranks

You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition. All the scores are guaranteed to be unique.

The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:

The 1st place athlete's rank is "Gold Medal".
The 2nd place athlete's rank is "Silver Medal".
The 3rd place athlete's rank is "Bronze Medal".
For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
Return an array answer of size n where answer[i] is the rank of the ith athlete.

 

Example 1:

Input: score = [5,4,3,2,1]
Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
Explanation: The placements are [1st, 2nd, 3rd, 4th, 5th].
Example 2:

Input: score = [10,3,8,9,4]
Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
Explanation: The placements are [1st, 5th, 3rd, 2nd, 4th].

 

Constraints:

n == score.length
1 <= n <= 10^4
0 <= score[i] <= 10^6
All the values in score are unique.

*/

//Soln using priority queue TC O(NlogN) SC O(N)
class Solution {
    public String[] findRelativeRanks(int[] score) {
        String[] ans = new String[score.length];
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b) -> {
            return score[a] - score[b];
        });
        for (int i=0;i<score.length;i++) {
            pq.offer(i);
        }
        int val = score.length;
        while (!pq.isEmpty()) {
            if (val > 3) {
                ans[pq.poll()] = String.valueOf(val);
            } else if (val == 3) {
                ans[pq.poll()] = "Bronze Medal";
            } else if (val == 2) {
                ans[pq.poll()] = "Silver Medal";
            } else {
                ans[pq.poll()] = "Gold Medal";
            }
            val--;
        }
        return ans;
    }
}

//Soln using counting sort TC O(M+N) SC O(M) where M is the max val and N is the length of the array
class Solution {
    public String[] findRelativeRanks(int[] score) {
        String[] ans = new String[score.length];
        int max = Arrays.stream(score).max().getAsInt();
        int[] arr = new int[max+1];
        Arrays.fill(arr, -1);
        for (int i=0;i<score.length;i++) {
            arr[score[i]] = i;
        }
        int val = 1;
        for (int i=max;i>=0;i--) {
            if (arr[i] == -1) continue;
            if (val > 3) {
                ans[arr[i]] = String.valueOf(val);
            } else if (val == 3) {
                ans[arr[i]] = "Bronze Medal";
            } else if (val == 2) {
                ans[arr[i]] = "Silver Medal";
            } else {
                ans[arr[i]] = "Gold Medal";
            }
            val++;
        }
        return ans;
    }
}
