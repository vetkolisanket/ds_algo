/*
261. Graph Valid Tree

You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.

Return true if the edges of the given graph make up a valid tree, and false otherwise.

 

Example 1:


Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true
Example 2:


Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false
 

Constraints:

1 <= n <= 2000
0 <= edges.length <= 5000
edges[i].length == 2
0 <= ai, bi < n
ai != bi
There are no self-loops or repeated edges.
*/


//Slightly faster union find/disjoint set soln using advanced graph theory logic, that if a graph is a valid tree, it will have exactly n-1 edges and 
//all of the edges will connect two unconnected components
class Solution {
    
    class DisjointSet {
        
        int root[], rank[], count;
        
        public DisjointSet(int n) {
            count = n;
            rank = new int[n];
            root = new int[n];
            for (int i=0;i<n;i++) {
                rank[i] = 1;
                root[i] = i;
            }
        }
        
        public int find(int x) {
            if (x == root[x]) return x;
            return root[x] = find(root[x]);
        }
        
        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
        
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return false;
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
        
    }
    
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n-1) return false;
        DisjointSet set = new DisjointSet(n);
        for (int[] edge: edges) {
            if (!set.union(edge[0], edge[1])) return false;
        }
        return true;
    }
}

//Using disjoint set quick find TC O(EV) SC = O(V) where E is the no. of edges and V is the no. of vertices in the graph
class Solution {
    
    class DisjointSet {
        
        int root[], count;
        
        public DisjointSet(int n) {
            count = n;
            root = new int[n];
            for (int i=0;i<n;i++) {
                root[i] = i;
            }
        }
        
        public int find(int x) {
            return root[x];
        }
        
        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            for (int i=0;i<root.length;i++) {
                if (root[i] == rootY) root[i] = rootX;
            }
        }
        
    }
    
    public boolean validTree(int n, int[][] edges) {
        DisjointSet set = new DisjointSet(n);
        for (int[] edge: edges) {
            int x = edge[0];
            int y = edge[1];
            if (set.connected(x, y)) return false;
            else set.count--;
            set.union(x, y);
        }
        return set.count == 1;
    }
}

//Iterative DFS TC O(V+E) SC O(V+E)
class Solution {
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList();
        for (int i=0;i<n;i++) {
            adjList.add(new ArrayList());
        }
        for (int[] edge: edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        Set<Integer> seen = new HashSet();
        Stack<Integer> stack = new Stack();
        Map<Integer, Integer> parent = new HashMap();
        stack.push(0);
        parent.put(0, -1);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            seen.add(node);
            for (int neighbour: adjList.get(node)) {
                if (parent.get(node) == neighbour) continue;
                if (parent.containsKey(neighbour)) return false;
                stack.push(neighbour);
                parent.put(neighbour, node);
            }
        }
        return seen.size() == n;
    }
}
