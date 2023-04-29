/*
1697. Checking Existence of Edge Length Limited Paths

An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note that there may be multiple edges between two nodes.

Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j] whether there is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .

Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true if there is a path for queries[j] is true, and false otherwise.

 

Example 1:


Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
Output: [false,true]
Explanation: The above figure shows the given graph. Note that there are two overlapping edges between 0 and 1 with distances 2 and 16.
For the first query, between 0 and 1 there is no path where each distance is less than 2, thus we return false for this query.
For the second query, there is a path (0 -> 1 -> 2) of two edges with distances less than 5, thus we return true for this query.
Example 2:


Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
Output: [true,false]
Exaplanation: The above figure shows the given graph.
 

Constraints:

2 <= n <= 10^5
1 <= edgeList.length, queries.length <= 105
edgeList[i].length == 3
queries[j].length == 3
0 <= ui, vi, pj, qj <= n - 1
ui != vi
pj != qj
1 <= disi, limitj <= 10^9
There may be multiple edges between two nodes.

*/

//Soln using union find TC O(N + ElogE + QlogQ) SC O(N + Q) where N is the no. of nodes, E is the no. of edges and Q is the no. of queries
class UnionFind {

    private int n, parent[], rank[];

    public UnionFind(int n) {
        this.n = n;
        parent = new int[n];
        rank = new int[n];
        for (int i=0;i<n;i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        if (rank[x] > rank[y]) {
            parent[y] = x;
        } else if (rank[x] < rank[y]) {
            parent[x] = y;
        } else {
            parent[y] = x;
            rank[x]++;
        }
    }

    public boolean areConnected(int x, int y) {
        x = find(x);
        y = find(y);
        return x == y;
    }

}

class Solution {

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        UnionFind uf = new UnionFind(n);
        int queriesLength = queries.length;
        boolean[] ans = new boolean[queriesLength];
        int[][] queriesWithIndex = new int[queriesLength][4];

        for (int i=0;i<queriesLength;i++) {
            queriesWithIndex[i][0] = queries[i][0];
            queriesWithIndex[i][1] = queries[i][1];
            queriesWithIndex[i][2] = queries[i][2];
            queriesWithIndex[i][3] = i;
        }

        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        Arrays.sort(queriesWithIndex, (a, b) -> a[2] - b[2]);

        int curEdgeIdx = 0;

        for (int i=0;i<queriesLength;i++) {
            int p = queriesWithIndex[i][0];
            int q = queriesWithIndex[i][1];
            int limit = queriesWithIndex[i][2];
            int idx = queriesWithIndex[i][3];

            while (curEdgeIdx < edgeList.length && edgeList[curEdgeIdx][2] < limit) {
                uf.union(edgeList[curEdgeIdx][0], edgeList[curEdgeIdx][1]);
                curEdgeIdx++;
            }

            ans[idx] = uf.areConnected(p, q);
        }

        return ans;
    }
}
