/*
547. Number of Provinces

There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

 

Example 1:


Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Example 2:


Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3
 

Constraints:

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
*/

//Soln using disjoint set or union find TC O(N^2) SC O(N) where N is the length of isConnected array or you can say no. of cities
class Solution {
    
    class DisjointSet {
        
        private int root[], rank[], count;
        
        public DisjointSet(int n) {
            root = new int[n];
            rank = new int[n];
            count = n;
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
                    rank[rootY] += 1;
                }
                count--;
            }
        }
        
        public int getComponents() {
            return count;
        }
        
    }
    
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DisjointSet set = new DisjointSet(n);
        for (int i=0;i<n;i++) {
            for (int j=i+1;j<n;j++) {
                if (isConnected[i][j] == 1) {
                    set.union(i, j);
                }
            }
        }
        return set.getComponents();
    }
}

//DFS soln TC O(N^2) SC O(N)
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int numComponents = 0;
        for (int i=0;i<n;i++) {
            if (!visited[i]) {
                numComponents++;
                dfs(isConnected, visited, i);
            }
        }
        return numComponents;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int idx) {
        visited[idx] = true;
        for (int i=0;i<isConnected.length;i++) {
            if (isConnected[idx][i] == 1 && !visited[i]) {
                dfs(isConnected, visited, i);
            }
        }
    }
}

//BFS soln TC O(N^2) SC O(N)
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque();
        int numComponents = 0;
        for (int i=0;i<n;i++) {
            if (visited[i]) continue;
            numComponents++;
            q.offer(i);
            while(!q.isEmpty()) {
                int node = q.poll();
                visited[node] = true;
                for (int j=0;j<n;j++) {
                    if (isConnected[node][j] == 1 && !visited[j]) {
                        q.offer(j);
                    }
                }
            }
        }
        return numComponents;
    }
}
