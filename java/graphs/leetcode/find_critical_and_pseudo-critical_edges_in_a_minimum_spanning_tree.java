/*
1489. Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree

Given a weighted undirected connected graph with n vertices numbered from 0 to n - 1, and an array edges where edges[i] = [ai, bi, weighti] represents a bidirectional and weighted edge between nodes ai and bi. A minimum spanning tree (MST) is a subset of the graph's edges that connects all vertices without cycles and with the minimum possible total edge weight.

Find all the critical and pseudo-critical edges in the given graph's minimum spanning tree (MST). An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. On the other hand, a pseudo-critical edge is that which can appear in some MSTs but not all.

Note that you can return the indices of the edges in any order.

 

Example 1:



Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
Output: [[0,1],[2,3,4,5]]
Explanation: The figure above describes the graph.
The following figure shows all the possible MSTs:

Notice that the two edges 0 and 1 appear in all MSTs, therefore they are critical edges, so we return them in the first list of the output.
The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are considered pseudo-critical edges. We add them to the second list of the output.
Example 2:



Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
Output: [[],[0,1,2,3]]
Explanation: We can observe that since all 4 edges have equal weight, choosing any 3 edges from the given 4 will yield an MST. Therefore all 4 edges are pseudo-critical.
 

Constraints:

2 <= n <= 100
1 <= edges.length <= min(200, n * (n - 1) / 2)
edges[i].length == 3
0 <= ai < bi < n
1 <= weighti <= 1000
All pairs (ai, bi) are distinct.

*/

//Soln using Kruskal's algorithm TC O(M^2alpha(N)) SC O(M) where M is the no. of edges alpha is inverse ackerman function which can be considered constant and N is the no. of vertices
class UnionFind {

    int parent[], size[], maxSize;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        maxSize = 1;
        for (int i=0;i<n;i++) {
            parent[i] = i;
            size[i] = 1;
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
        if (size[x] >= size[y]) {
            parent[y] = x;
            size[x] += size[y];
            maxSize = Math.max(maxSize, size[x]);
        } else {
            parent[x] = y;
            size[y] += size[x];
            maxSize = Math.max(maxSize, size[y]);
        }
        return true;
    }

}

class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] newEdges = new int[m][4];

        for (int i=0;i<m;i++) {
            for (int j=0;j<3;j++) {
                newEdges[i][j] = edges[i][j];
            }
            newEdges[i][3] = i;
        }

        Arrays.sort(newEdges, (a, b) -> a[2] - b[2]);

        UnionFind ufStd = new UnionFind(n);
        int stdWeight = 0;
        for (int[] edge: newEdges) {
            if (ufStd.union(edge[0], edge[1])) {
                stdWeight += edge[2];
            }
        }

        List<List<Integer>> result = new ArrayList();
        result.add(new ArrayList());
        result.add(new ArrayList());

        for (int i=0;i<m;i++) {
            UnionFind ufIgnore = new UnionFind(n);
            int ignoreWeight = 0;
            for (int j=0;j<m;j++) {
                if (i != j && ufIgnore.union(newEdges[j][0], newEdges[j][1])) {
                    ignoreWeight += newEdges[j][2];
                }
            }

            if (ufIgnore.maxSize < n || ignoreWeight > stdWeight) {
                result.get(0).add(newEdges[i][3]);
            } else {
                UnionFind ufForce = new UnionFind(n);
                int forceWeight = newEdges[i][2];
                ufForce.union(newEdges[i][0], newEdges[i][1]);
                for (int j=0;j<m;j++) {
                    if (i != j && ufForce.union(newEdges[j][0], newEdges[j][1])) {
                        forceWeight += newEdges[j][2];
                    }
                }
                if (forceWeight == stdWeight) {
                    result.get(1).add(newEdges[i][3]);
                }
            }
        }
        return result;
    }
}
