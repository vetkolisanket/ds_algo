/*
2316. Count Unreachable Pairs of Nodes in an Undirected Graph

You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.

Return the number of pairs of different nodes that are unreachable from each other.

 

Example 1:


Input: n = 3, edges = [[0,1],[0,2],[1,2]]
Output: 0
Explanation: There are no pairs of nodes that are unreachable from each other. Therefore, we return 0.
Example 2:


Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
Output: 14
Explanation: There are 14 pairs of nodes that are unreachable from each other:
[[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
Therefore, we return 14.
 

Constraints:

1 <= n <= 10^5
0 <= edges.length <= 2 * 10^5
edges[i].length == 2
0 <= ai, bi < n
ai != bi
There are no repeated edges.

*/

//Soln using union find TC O(n + e) SC O(n)
class Solution {

    class UnionFind {

        int n, parent[];

        public UnionFind(int n) {
            this.n = n;
            parent = new int[n];
            for (int i=0;i<n;i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (x == parent[x]) return x;
            return parent[x] = find(parent[x]);
        }

        public void union(int x ,int y) {
            x = find(x);
            y = find(y);
            if (x == y) return;
            if (x < y) parent[y] = x;
            else parent[x] = y;
        }

    }

    public long countPairs(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge: edges) {
            uf.union(edge[0], edge[1]);
        }
        Map<Integer, Integer> components = new HashMap();
        for (int i=0;i<n;i++) {
            int x = uf.find(i);
            components.put(x, components.getOrDefault(x, 0)+1);
        }
        long ans = 0, remainingComponents = n;
        for (int componentSize: components.values()) {
            ans += componentSize * (remainingComponents -= componentSize);
        }
        return ans;
    }
}
