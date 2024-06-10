/*
1051. Height Checker

A school is trying to take an annual photo of all the students. The students are asked to stand in a single file line in non-decreasing order by height. Let this ordering be represented by the integer array expected where expected[i] is the expected height of the ith student in line.

You are given an integer array heights representing the current order that the students are standing in. Each heights[i] is the height of the ith student in line (0-indexed).

Return the number of indices where heights[i] != expected[i].

 

Example 1:

Input: heights = [1,1,4,2,1,3]
Output: 3
Explanation: 
heights:  [1,1,4,2,1,3]
expected: [1,1,1,2,3,4]
Indices 2, 4, and 5 do not match.
Example 2:

Input: heights = [5,1,2,3,4]
Output: 5
Explanation:
heights:  [5,1,2,3,4]
expected: [1,2,3,4,5]
All indices do not match.
Example 3:

Input: heights = [1,2,3,4,5]
Output: 0
Explanation:
heights:  [1,2,3,4,5]
expected: [1,2,3,4,5]
All indices match.
 

Constraints:

1 <= heights.length <= 100
1 <= heights[i] <= 100

*/

//My soln using priority queue TC O(NlogN) SC O(N)
class Solution {
    public int heightChecker(int[] heights) {
        PriorityQueue<Integer> pq = new PriorityQueue();
        for (int height: heights) {
            pq.offer(height);
        }
        int ans = 0;
        for (int i=0;i<heights.length;i++) {
            if (pq.poll() != heights[i]) {
                ans++;
            }
        }
        return ans;
    }
}

//Soln using counting sort TC O(N+K) SC O(N+K)
class Solution {
    public int heightChecker(int[] heights) {
        int[] sortedHeights = heights.clone();
        countingSort(sortedHeights);

        int ans = 0;
        for (int i=0;i<heights.length;i++) {
            if (sortedHeights[i] != heights[i]) {
                ans++;
            }
        }
        return ans;
    }

    private void countingSort(int[] heights) {
        int[] arr = new int[101];
        for (int height: heights) {
            arr[height]++;
        }
        for (int i=0,j=0;i<arr.length;i++) {
            while (arr[i]-- > 0) {
                heights[j++] = i;
            }
        }
    }
}
