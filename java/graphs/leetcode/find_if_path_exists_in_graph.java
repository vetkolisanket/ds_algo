/*
1971. Find if Path Exists in Graph

There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.

 

Example 1:


Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
Output: true
Explanation: There are two paths from vertex 0 to vertex 2:
- 0 → 1 → 2
- 0 → 2
Example 2:


Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
Output: false
Explanation: There is no path from vertex 0 to vertex 5.
 

Constraints:

1 <= n <= 2 * 10^5
0 <= edges.length <= 2 * 10^5
edges[i].length == 2
0 <= ui, vi <= n - 1
ui != vi
0 <= source, destination <= n - 1
There are no duplicate edges.
There are no self edges.
*/

//Using disjoint set TC O(Elog(V)) SC O(V)
class Solution {
    
    class DisjointSet {
        int[] parent;
        int N;
        
        public DisjointSet(int n) {
            N = n;
            parent = new int[N];
            for (int i=0;i<N;i++) {
                parent[i] = i;
            }
        }
        
        private int find(int u) {
            int x = u;
            while (x != parent[x]) {
                x = parent[x];
            }
            parent[u] = x;
            return x;
        }
        
        public boolean areConnected(int u, int v) {
            return find(u) == find(v);
        }
        
        public void union(int u, int v) {
            if (u != v) {
                int a = find(u);
                int b = find(v);
                parent[a] = b;
            }
        }
    }
    
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        DisjointSet set = new DisjointSet(n);
        for (int[] edge: edges) {
            set.union(edge[0], edge[1]);
        }
        return set.areConnected(source, destination);
    }
}

//Using DFS TC O(V+E) SC O(V+E)
class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        HashSet<Integer>[] graph = new HashSet[n];
        for (int i=0;i<n;i++) {
            graph[i] = new HashSet<Integer>();
        }
        for (int[] edge: edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        if (graph[source].contains(destination)) return true;
        boolean[] visited = new boolean[n];
        return dfs(graph, visited, source, destination);
    }
    
    private boolean dfs(HashSet<Integer>[] graph, boolean[] visited, int source, int destination) {
        if (source == destination) return true;
        boolean seen = false;
        visited[source] = true;
        for (int node: graph[source]) {
            if (!visited[node]) {
                seen |= dfs(graph, visited, node, destination);
                if (seen) break;
            }
        }
        return seen;
    }
}

//Simple DFS soln from another attempt TC O(V+E) SC O(V+E)
class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<Integer>[] adjList = new ArrayList[n];
        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList();
        }
        for (int[] edge: edges) {
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        return visit(source, destination, adjList, visited);
    }

    private boolean visit(int source, int destination, List<Integer>[] adjList, boolean[] visited) {
        if (source == destination) return true;
        visited[source] = true;
        for (int v : adjList[source]) {
            if (visited[v]) continue;
            if (visit(v, destination, adjList, visited)) {
                return true;
            }
        }
        return false;
    }
}

//Soln using BFS TC O(V+E) SC O(V+E)
class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (source == destination) return true;
        Queue<Integer> queue = new ArrayDeque();
        boolean[] visited = new boolean[n];
        queue.offer(source);
        visited[source] = true;
        List<Integer>[] adjList = new ArrayList[n];
        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList();
        }
        for (int[] edge: edges) {
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
        }
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int p: adjList[v]) {
                if (p == destination) return true;
                if (visited[p]) continue;
                queue.offer(p);
                visited[p] = true;
            }
        }
        return false;
    }
}

//Another Union Find soln TC O(V + Ealpha(V)) SC O(V)
class UnionFind {

    private int n;
    private int[] parent, rank;

    public UnionFind(int n) {
        this.n = n;
        parent = new int[n];
        rank = new int[n];
        for (int i=0;i<n;i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;
        if (rank[x] >= rank[y]) {
            parent[y] = x;
            rank[x]++;
        } else {
            parent[x] = y;
            rank[y]++;
        }
    }

}

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge: edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.find(source) == uf.find(destination);
    }
}
