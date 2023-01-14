/*
2246. Longest Path With Different Adjacent Characters

You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.

You are also given a string s of length n, where s[i] is the character assigned to node i.

Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.

 

Example 1:


Input: parent = [-1,0,0,1,1,2], s = "abacbe"
Output: 3
Explanation: The longest path where each two adjacent nodes have different characters in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 is returned.
It can be proven that there is no longer path that satisfies the conditions. 
Example 2:


Input: parent = [-1,0,0,0], s = "aabc"
Output: 3
Explanation: The longest path where each two adjacent nodes have different characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.
 

Constraints:

n == parent.length == s.length
1 <= n <= 10^5
0 <= parent[i] <= n - 1 for all i >= 1
parent[0] == -1
parent represents a valid tree.
s consists of only lowercase English letters.
*/

//Soln using DFS TC O(n) SC O(n)
class Solution {

    private int ans = 1;

    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] adjList = new ArrayList[n];
        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList();
        }
        for (int i=1;i<n;i++) {
            adjList[i].add(parent[i]);
            adjList[parent[i]].add(i);
        }
        dfs(adjList, 0, -1, 1, s);
        return ans;
    }

    private int dfs(List<Integer>[] adjList, int cur, int pre, int len, String s) {
        int max = 0;
        for (int neighbour: adjList[cur]) {
            if (neighbour == pre) continue;
            int child = dfs(adjList, neighbour, cur, len, s);
            ans = Math.max(ans, max+child+1);
            max = Math.max(max, child);
        }
        if (pre == -1 || s.charAt(cur) != s.charAt(pre)) return max+1;
        return 0;
    }
}

//Soln
class Solution {
    
    int ans = 1;
    int[] dist = new int[100000];
    
    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] adjList = new ArrayList[n];
        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList();
        }
        for (int i=1;i<n;i++) {
            adjList[parent[i]].add(i);
        }
        dfs(0, s, adjList);
        return ans;
    }
    
    private void dfs(int src, String s, List<Integer>[] adjList) {
        dist[src] = 1;
        for (int node: adjList[src]) {
            dfs(node, s, adjList);
            if (s.charAt(src) != s.charAt(node)) {
                ans = Math.max(ans, dist[src]+dist[node]);
                dist[src] = Math.max(dist[src], dist[node]+1);
            }
        }
    }
}
