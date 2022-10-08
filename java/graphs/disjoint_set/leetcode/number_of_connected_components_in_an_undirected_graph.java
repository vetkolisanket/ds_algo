/*
323. Number of Connected Components in an Undirected Graph

You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.

Return the number of connected components in the graph.

 

Example 1:


Input: n = 5, edges = [[0,1],[1,2],[3,4]]
Output: 2
Example 2:


Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
Output: 1
 

Constraints:

1 <= n <= 2000
1 <= edges.length <= 5000
edges[i].length == 2
0 <= ai <= bi < n
ai != bi
There are no repeated edges.
*/

//Soln using disjoint set TC O(V + Ealpha(V)) SC O(V) where V is the no of vertices and E is the no. of edges
class Solution {
    
    class DisjointSet {
        
        int rank[], root[], count;
        
        public DisjointSet(int n) {
            count = n;
            rank = new int[n];
            root = new int[n];
            for (int i=0;i<n;i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }
        
        public int find(int x) {
            if (x == root[x]) return x;
            return root[x] = find(root[x]);
        }
        
        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX]++;
                }
                count--;
            }
        }
        
        
    }
    
    public int countComponents(int n, int[][] edges) {
        DisjointSet set = new DisjointSet(n);
        for (int[] edge: edges) {
            set.union(edge[0], edge[1]);
        }
        return set.count;
    }
}
