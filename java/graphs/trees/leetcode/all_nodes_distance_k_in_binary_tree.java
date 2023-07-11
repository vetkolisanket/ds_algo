/*
863. All Nodes Distance K in Binary Tree

Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.

 

Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
Example 2:

Input: root = [1], target = 1, k = 3
Output: []
 

Constraints:

The number of nodes in the tree is in the range [1, 500].
0 <= Node.val <= 500
All the values Node.val are unique.
target is the value of one of the nodes in the tree.
0 <= k <= 1000

*/

//Soln using DFS TC O(N) SC O(N)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<Integer, List<Integer>> graph = new HashMap();
        buildGraph(graph, root, null);
        List<Integer> list = new ArrayList();
        Set<Integer> visited = new HashSet();
        dfs(graph, list, visited, target.val, 0, k);
        return list;
    }

    private void buildGraph(Map<Integer, List<Integer>> graph, TreeNode a, TreeNode b) {
        if (b != null && a != null) {
            graph.computeIfAbsent(a.val, k -> new ArrayList()).add(b.val);
            graph.computeIfAbsent(b.val, k -> new ArrayList()).add(a.val);
        }
        if (a.left != null) buildGraph(graph, a.left, a);
        if (a.right != null) buildGraph(graph, a.right, a);
    }

    private void dfs(Map<Integer, List<Integer>> graph, List<Integer> list, Set<Integer> visited, int cur, int dist, int k) {
        visited.add(cur);
        if (dist == k) {
            list.add(cur);
            return;
        }
        for (int neighbour: graph.getOrDefault(cur, new ArrayList<>())) {
            if (!visited.contains(neighbour)) {
                dfs(graph, list, visited, neighbour, dist+1, k);
            }
        }
    }
}

//Soln using BFS TC O(N) SC O(N)
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<Integer, List<Integer>> graph = new HashMap();
        buildGraph(graph, root, null);
        List<Integer> list = new ArrayList();
        Set<Integer> visited = new HashSet();
        Queue<Integer> queue = new ArrayDeque();
        queue.offer(target.val);
        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                visited.add(cur);
                if (dist == k) list.add(cur);
                for (int neighbour: graph.getOrDefault(cur, new ArrayList<>())) {
                    if (visited.contains(neighbour)) continue;
                    queue.offer(neighbour);
                }
            }
            if (++dist > k) break;
        }
        return list;
    }

    private void buildGraph(Map<Integer, List<Integer>> graph, TreeNode cur, TreeNode parent) {
        if (cur != null && parent != null) {
            graph.computeIfAbsent(cur.val, k -> new ArrayList()).add(parent.val);
            graph.computeIfAbsent(parent.val, k -> new ArrayList()).add(cur.val);
        }
        if (cur.left != null) buildGraph(graph, cur.left, cur);
        if (cur.right != null) buildGraph(graph, cur.right, cur);
    }
}
