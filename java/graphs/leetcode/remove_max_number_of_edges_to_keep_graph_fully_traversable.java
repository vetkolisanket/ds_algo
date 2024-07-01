/*
1579. Remove Max Number of Edges to Keep Graph Fully Traversable

Alice and Bob have an undirected graph of n nodes and three types of edges:

Type 1: Can be traversed by Alice only.
Type 2: Can be traversed by Bob only.
Type 3: Can be traversed by both Alice and Bob.
Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi, find the maximum number of edges you can remove so that after removing the edges, the graph can still be fully traversed by both Alice and Bob. The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.

Return the maximum number of edges you can remove, or return -1 if Alice and Bob cannot fully traverse the graph.

 

Example 1:



Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
Output: 2
Explanation: If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice and Bob. Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.
Example 2:



Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
Output: 0
Explanation: Notice that removing any edge will not make the graph fully traversable by Alice and Bob.
Example 3:



Input: n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
Output: -1
Explanation: In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot reach 1. Therefore it's impossible to make the graph fully traversable.
 

 

Constraints:

1 <= n <= 10^5
1 <= edges.length <= min(105, 3 * n * (n - 1) / 2)
edges[i].length == 3
1 <= typei <= 3
1 <= ui < vi <= n
All tuples (typei, ui, vi) are distinct.

*/

//Soln using union find TC O(E + alpha(N)) SC O(N) where E is the no. of edges, N is the no. of nodes and alpha() is the inverse ackerman function
class UnionFind {

    private int n, parent[], rank[];

    public UnionFind(int n) {
        this.n = n;
        parent = new int[n+1];
        rank = new int[n+1];
        for (int i=0;i<=n;i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public int union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return 0;

        if (rank[x] > rank[y]) {
            rank[x] += rank[y];
            parent[y] = x;
        } else {
            rank[y] += rank[x];
            parent[x] = y;
        }

        n--;
        return 1;
    }

    public boolean isConnected() {
        return n == 1;
    }

}

class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind alice = new UnionFind(n);
        UnionFind bob = new UnionFind(n);

        int edgesRequired = 0;

        for (int[] edge: edges) {
            if (edge[0] == 3) {
                edgesRequired += (alice.union(edge[1], edge[2]) | bob.union(edge[1], edge[2]));
            }
        }

        for (int[] edge: edges) {
            if (edge[0] == 1) {
                edgesRequired += alice.union(edge[1], edge[2]);
            } else if (edge[0] == 2) {
                edgesRequired += bob.union(edge[1], edge[2]);
            }
        }

        if (alice.isConnected() && bob.isConnected()) {
            return edges.length - edgesRequired;
        }
        return -1;
    }
}

//Soln from another attempt using UnionFind TC O(V + E*alpha(V)) SC O(V)
class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        if (edges.length < n-1) return -1;
        UnionFind alice = new UnionFind(n), bob = new UnionFind(n);
        int ans = 0;
        for (int[] edge: edges) {
            if (edge[0] == 3) {
                boolean isNeededByAlice = alice.union(edge[1], edge[2]);
                boolean isNeededByBob = bob.union(edge[1], edge[2]);
                if (!isNeededByAlice && !isNeededByBob) {
                    ans++;
                }
            }
        }
        for (int[] edge: edges) {
            if (edge[0] != 3) {
                if (edge[0] == 1 && !alice.union(edge[1], edge[2])) {
                    ans++;
                }
                if (edge[0] == 2 && !bob.union(edge[1], edge[2])) {
                    ans++;
                }
            }
        }
        if (alice.components != 1 || bob.components != 1) return -1;
        return ans;
    }
}

class UnionFind {

    private int[] parent;
    public int components;

    public UnionFind(int n) {
        components = n;
        parent = new int[n+1];
        for (int i=0;i<=n;i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;
        components--;
        parent[y] = x;
        return true;
    }


}
