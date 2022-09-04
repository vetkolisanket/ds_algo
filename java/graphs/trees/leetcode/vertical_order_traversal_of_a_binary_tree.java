/*
987. Vertical Order Traversal of a Binary Tree

Given the root of a binary tree, calculate the vertical order traversal of the binary tree.

For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).

The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.

Return the vertical order traversal of the binary tree.

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation:
Column -1: Only node 9 is in this column.
Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
Column 1: Only node 20 is in this column.
Column 2: Only node 7 is in this column.
Example 2:


Input: root = [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
Column -2: Only node 4 is in this column.
Column -1: Only node 2 is in this column.
Column 0: Nodes 1, 5, and 6 are in this column.
          1 is at the top, so it comes first.
          5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
Column 1: Only node 3 is in this column.
Column 2: Only node 7 is in this column.
Example 3:


Input: root = [1,2,3,4,6,5,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
This case is the exact same as example 2, but with nodes 5 and 6 swapped.
Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.
 

Constraints:

The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 1000
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

//My soln TC O(Nlog(N)) SC O(N)
class Solution {
    
    class NodeInfo {
        TreeNode node;
        int x, y;
        
        public NodeInfo(TreeNode node, int x, int y) {
            this.node = node;
            this.x = x;
            this.y = y;
        }
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, PriorityQueue<NodeInfo>> map = new TreeMap();
        Queue<NodeInfo> queue = new ArrayDeque();
        queue.offer(new NodeInfo(root, 0, 0));
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                NodeInfo nodeInfo = queue.poll();
                if (nodeInfo.node.left != null) {
                    queue.offer(new NodeInfo(nodeInfo.node.left, nodeInfo.x+1, nodeInfo.y-1));
                }
                if (nodeInfo.node.right != null) {
                    queue.offer(new NodeInfo(nodeInfo.node.right, nodeInfo.x+1, nodeInfo.y+1));
                }
                map.putIfAbsent(nodeInfo.y, new PriorityQueue<NodeInfo>((a, b) -> {
                    if (a.x == b.x) {
                        return a.node.val - b.node.val;
                    } else {
                        return a.x - b.x;
                    }
                }));
                map.get(nodeInfo.y).offer(nodeInfo);
            }
        }
        List<List<Integer>> ans = new ArrayList();
        for (Map.Entry<Integer, PriorityQueue<NodeInfo>> entry: map.entrySet()) {
            PriorityQueue<NodeInfo> pq = entry.getValue();
            List<Integer> list = new ArrayList();
            while (!pq.isEmpty()) {
                list.add(pq.poll().node.val);
            }
            ans.add(list);
        }
        return ans;
    }
}
