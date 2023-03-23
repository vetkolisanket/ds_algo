/*
1319. Number of Operations to Make Network Connected

There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.

You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.

Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.

 

Example 1:


Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
Example 2:


Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2
Example 3:

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.
 

Constraints:

1 <= n <= 10^5
1 <= connections.length <= min(n * (n - 1) / 2, 10^5)
connections[i].length == 2
0 <= ai, bi < n
ai != bi
There are no repeated connections.
No two computers are connected by more than one cable.

*/

//Soln using union find TC O(n+e) SC (n) where n is the no. of nodes and e is the no. of edges
class Solution {

    class UnionFind {

        int n;
        int[] parent;

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

        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) return;
            if (x < y) {
                parent[y] = x;
            } else {
                parent[x] = y;
            }
        }

    }

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n-1) return -1;
        UnionFind uf = new UnionFind(n);
        for (int[] connection: connections) {
            uf.union(connection[0], connection[1]);
        }
        Set<Integer> set = new HashSet();
        for (int i=0;i<n;i++) {
            set.add(uf.find(i));
        }
        return set.size()-1;
    }
}
