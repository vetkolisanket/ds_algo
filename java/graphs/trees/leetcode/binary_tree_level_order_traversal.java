/*
102. Binary Tree Level Order Traversal

Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-1000 <= Node.val <= 1000
*/

//A better soln
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        dfs(res, root, 0);
        return res;
    }
    
    private void dfs(List<List<Integer>> list, TreeNode node, int height) {
        if (node == null) return;
        if (height == list.size()) {
            list.add(new LinkedList());
        }
        list.get(height).add(node.val);
        dfs(list, node.left, height+1);
        dfs(list, node.right, height+1);
    }
    
}

//My soln
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
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList();
        List<List<TreeNode>> res = new ArrayList();
        List<TreeNode> list = new ArrayList();
        list.add(root);
        res.add(list);
        bfs(list, res);
        return createRes(res);
    }
    
    private void bfs(List<TreeNode> list, List<List<TreeNode>> res) {
        List<TreeNode> temp = new ArrayList();
        for (TreeNode node: list) {
            if (node.left != null) temp.add(node.left);
            if (node.right != null) temp.add(node.right);
        }
        if (!temp.isEmpty()) {
            res.add(temp);
            bfs(temp, res);
        }
    }
    
    private List<List<Integer>> createRes(List<List<TreeNode>> res) {
        List<List<Integer>> list = new ArrayList();
        for (List<TreeNode> nodes: res) {
            List<Integer> l = new ArrayList();
            for (TreeNode node: nodes) {
                l.add(node.val);
            }
            list.add(l);
        }
        return list;
    }
    
}
