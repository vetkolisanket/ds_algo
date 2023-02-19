/*
103. Binary Tree Zigzag Level Order Traversal

Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
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
//Soln using BFS TC O(N) SC O(N)
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList();
        if (root == null) return ans;
        Queue<TreeNode> queue = new ArrayDeque();
        boolean isReverse = false;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            if (isReverse) Collections.reverse(list);
            ans.add(list);
            isReverse = !isReverse;
        }
        return ans;
    }
}

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList();
        zigzag(list, root, 0);
        return list;
    }
    
    private void zigzag(List<List<Integer>> list, TreeNode node, int height) {
        if (node == null) return;
        if (height == list.size()) {
            list.add(new LinkedList());
        }
        if (height%2 == 0) list.get(height).add(node.val);
        else list.get(height).add(0, node.val);
        
        zigzag(list, node.left, height+1);
        zigzag(list, node.right, height+1);
    }
}
