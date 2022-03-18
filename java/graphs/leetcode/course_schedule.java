/*
207. Course Schedule

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 

Constraints:

1 <= numCourses <= 10^5
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.

*/

//Using adjacency list
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adjList = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];
        
        for (int i=0;i<numCourses;i++) {
            adjList[i] = new ArrayList();
        }
        
        Arrays.fill(inDegree, 0);
        
        for (int[] pre: prerequisites) {
            adjList[pre[1]].add(pre[0]);
            inDegree[pre[0]]++;
        }
        
        Queue<Integer> q = new ArrayDeque();
        int count = 0;
        for (int i=0;i<numCourses;i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        
        while (!q.isEmpty()) {
            int val = q.poll();
            count++;
            for (int i: adjList[val]) {
                    inDegree[i]--;
                    if (inDegree[i] == 0) q.offer(i);
            }
        }
        
        return count == numCourses;
    }
}

//Using adjacency matrix
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses];
        int[] inDegree = new int[numCourses];
        
        for (int i=0;i<prerequisites.length;i++) {
            int pre = prerequisites[i][1];
            int cur = prerequisites[i][0];
            matrix[pre][cur] = 1;
            inDegree[cur]++;
        }
        
        Queue<Integer> queue = new LinkedList();
        for (int i=0;i<numCourses;i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            count++;
            for (int j=0;j<numCourses;j++) {
                if (matrix[i][j] == 1) {
                    if (--inDegree[j] == 0) {
                        queue.offer(j);
                    }
                }
            }
        }
        return count == numCourses;
    }
}
