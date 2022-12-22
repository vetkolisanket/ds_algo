/*
886. Possible Bipartition

We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.

Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.

 

Example 1:

Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4] and group2 [2,3].
Example 2:

Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Example 3:

Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false
 

Constraints:

1 <= n <= 2000
0 <= dislikes.length <= 10^4
dislikes[i].length == 2
1 <= dislikes[i][j] <= n
ai < bi
All the pairs of dislikes are unique.

*/

//Soln using BFS TC O(V+E) SC O(V+E) where V is no. of people and E is the no. of dislikes
class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] adjList = new ArrayList[n+1];
        for (int i=1;i<=n;i++) {
            adjList[i] = new ArrayList();
        }
        for (int[] dislike: dislikes) {
            adjList[dislike[0]].add(dislike[1]);
            adjList[dislike[1]].add(dislike[0]);
        }
        int[] groups = new int[n+1];
        Arrays.fill(groups, -1);
        for (int i=1;i<=n;i++) {
            if (groups[i] != -1) continue;
            if (!bfs(adjList, groups, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean bfs(List<Integer>[] adjList, int[] groups, int source) {
        Queue<Integer> queue = new ArrayDeque();
        queue.offer(source);
        groups[source] = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbour: adjList[node]) {
                if (groups[node] == groups[neighbour]) {
                    return false;
                } else if (groups[neighbour] == -1) {
                    groups[neighbour] = 1 - groups[node];
                    queue.offer(neighbour);
                }
            }
        }
        return true;
    }
}

//DFS soln TC O(V+E) SC O(V+E)
class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] adjList = new ArrayList[n+1];
        for (int i=1;i<=n;i++) {
            adjList[i] = new ArrayList();
        }
        for (int[] dislike: dislikes) {
            adjList[dislike[0]].add(dislike[1]);
            adjList[dislike[1]].add(dislike[0]);
        }
        int[] groups = new int[n+1];
        Arrays.fill(groups, -1);
        for (int i=1;i<=n;i++) {
            if (groups[i] != -1) continue;
            if (!dfs(adjList, groups, i, 0)) return false;
        }
        return true;
    }

    private boolean dfs(List<Integer>[] adjList, int[] groups, int source, int group) {
        groups[source] = group;
        for (int neighbour: adjList[source]) {
            if (groups[neighbour] == group) return false;
            if (groups[neighbour] == -1 && !dfs(adjList, groups, neighbour, 1-group)) return false;
        }
        return true;
    }
}

//Soln using UnionFind TC O(V+E) SC O(V+E)
class Solution {

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

    }

    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] adjList = new ArrayList[n+1];
        for (int i=1;i<=n;i++) {
            adjList[i] = new ArrayList();
        }
        for (int[] dislike: dislikes) {
            adjList[dislike[0]].add(dislike[1]);
            adjList[dislike[1]].add(dislike[0]);
        }
        UnionFind uf = new UnionFind(n+1);
        for (int node=1;node<=n;node++) {
            for (int neighbour: adjList[node]) {
                if (uf.find(node) == uf.find(neighbour)) return false;
                uf.union(adjList[node].get(0), neighbour);
            }
        }
        return true;
    }
}
