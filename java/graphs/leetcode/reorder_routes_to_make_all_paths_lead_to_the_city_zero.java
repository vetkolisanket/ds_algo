/*
1466. Reorder Routes to Make All Paths Lead to the City Zero

There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach city 0 after reorder.

 

Example 1:


Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 2:


Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 3:

Input: n = 3, connections = [[1,0],[2,0]]
Output: 0
 

Constraints:

2 <= n <= 5 * 10^4
connections.length == n - 1
connections[i].length == 2
0 <= ai, bi <= n - 1
ai != bi

*/

//Soln using DFS TC O(N) SC O(N) where N is the no. of cities. It would have been O(V+E) but given V = N and E = N-1 (given its a tree) it boils down to O(N)
class Solution {

    int count = 0;

    public int minReorder(int n, int[][] connections) {
        List<Pair<Integer, Integer>>[] adjList = new ArrayList[n];
        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList();
        }
        for (int[] connection: connections) {
            adjList[connection[0]].add(new Pair(connection[1], 1));
            adjList[connection[1]].add(new Pair(connection[0], 0));
        }
        dfs(0, -1, adjList);
        return count;
    }

    private void dfs(int node, int parent, List<Pair<Integer, Integer>>[] adjList) {
        for (Pair<Integer, Integer> pair: adjList[node]) {
            int neighbour = pair.getKey();
            if (neighbour == parent) continue;
            count += pair.getValue();
            dfs(neighbour, node, adjList);
        }
    }
}

//My soln from another attempt TC O(N) SC O(N)
class Solution {
    public int minReorder(int n, int[][] connections) {
        List<Integer>[] incoming = new ArrayList[n];
        List<Integer>[] outgoing = new ArrayList[n];
        for (int i=0;i<n;i++) {
            incoming[i] = new ArrayList();
            outgoing[i] = new ArrayList();
        }
        for (int[] connection: connections) {
            incoming[connection[1]].add(connection[0]);
            outgoing[connection[0]].add(connection[1]);
        }
        int ans = 0;
        Queue<Integer> q = new ArrayDeque();
        boolean[] visited = new boolean[n];
        visited[0] = true;
        q.offer(0);
        while (!q.isEmpty()) {
            int city = q.poll();
            for (int neighbour: incoming[city]) {
                if (!visited[neighbour]) {
                    q.offer(neighbour);
                    visited[neighbour] = true;
                }
                
            }
            for (int neighbour: outgoing[city]) {
                if (!visited[neighbour]) {
                    ans++;
                    visited[neighbour] = true;
                    q.offer(neighbour);
                }
            }
        }
        return ans;
    }
}
