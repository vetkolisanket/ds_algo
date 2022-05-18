/*
1192. Critical Connections in a Network

There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.

 

Example 1:


Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
Example 2:

Input: n = 2, connections = [[0,1]]
Output: [[0,1]]
 

Constraints:

2 <= n <= 105
n - 1 <= connections.length <= 105
0 <= ai, bi <= n - 1
ai != bi
There are no repeated connections.
*/

class Solution {
    
    Map<Integer, List<Integer>> graph;
    Map<Integer, Integer> rankMap;
    Set<Pair<Integer, Integer>> connSet;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        buildGraph(n, connections);
        dfs(0, 0);
        
        List<List<Integer>> list = new ArrayList();
        for (Pair<Integer, Integer> pair: connSet) {
            list.add(new ArrayList(Arrays.asList(pair.getKey(), pair.getValue())));
        }
        return list;
    }
    
    private void buildGraph(int n, List<List<Integer>> connections) {
        graph = new HashMap();
        rankMap = new HashMap();
        connSet = new HashSet();
        
        for (int i=0;i<n;i++) {
            graph.put(i, new ArrayList());
            rankMap.put(i, null);
        }
        
        for (List<Integer> connection: connections) {
            int u = connection.get(0), v = connection.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
            
            int sortedU = Math.min(u, v), sortedV = Math.max(u, v);
            connSet.add(new Pair(sortedU, sortedV));
        }
    }
    
    private int dfs(int node, int displayRank) {
        if (rankMap.get(node) != null) {
            return rankMap.get(node);
        }
        
        rankMap.put(node, displayRank);
        
        int minRank = displayRank+1;
        
        for (int neighbor: graph.get(node)) {
            Integer neighRank = rankMap.get(neighbor);
            
            if (neighRank != null && neighRank == displayRank-1) {
                continue;
            }
            
            int recursiveRank = dfs(neighbor, displayRank+1);
            
            if (recursiveRank <= displayRank) {
                int sortedU = Math.min(node, neighbor), sortedV = Math.max(node, neighbor);
                connSet.remove(new Pair(sortedU, sortedV));
            }
            
            minRank = Math.min(minRank, recursiveRank);
        }
        return minRank;
    }
}
