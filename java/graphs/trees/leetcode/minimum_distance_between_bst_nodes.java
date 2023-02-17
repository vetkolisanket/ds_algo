/*
783. Minimum Distance Between BST Nodes

Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.

 

Example 1:


Input: root = [4,2,6,1,3]
Output: 1
Example 2:


Input: root = [1,0,48,null,null,12,49]
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [2, 100].
0 <= Node.val <= 10^5
 

Note: This question is the same as 530: https://leetcode.com/problems/minimum-absolute-difference-in-bst/
*/

//My soln TC O(n) SC O(n)
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
    public int minDiffInBST(TreeNode root) {
        int ans = Integer.MAX_VALUE;
        if (root == null) return ans;
        int left = getRightMostVal(root.left);
        int right = getLeftMostVal(root.right);
        ans = Math.min(Math.abs(root.val-left), Math.abs(root.val-right));
        ans = Math.min(ans, Math.min(minDiffInBST(root.left), minDiffInBST(root.right)));
        return ans;
    }

    private int getRightMostVal(TreeNode node) {
        if (node == null) return Integer.MAX_VALUE;
        while (node.right != null) node = node.right;
        return node.val;
    }

    private int getLeftMostVal(TreeNode node) {
        if (node == null) return Integer.MAX_VALUE;
        while (node.left != null) node = node.left;
        return node.val;
    }
}

//Soln by storing inorder traversal in a list TC O(n) SC (n)
class Solution {
    public int minDiffInBST(TreeNode root) {
        List<Integer> list = new ArrayList();
        inorderTraversal(root, list);
        int ans = Integer.MAX_VALUE;
        for (int i=1;i<list.size();i++) {
            ans = Math.min(ans, Math.abs(list.get(i) - list.get(i-1)));
        }
        return ans;
    }

    private void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }
}

//Soln using inorder traversal with storing the prev value TC O(n) SC O(h) where h is the height of the tree which can be n-1 in worst case
class Solution {

    private int ans = Integer.MAX_VALUE;
    private TreeNode prev = null;

    public int minDiffInBST(TreeNode root) {
        inorderTraversal(root);
        return ans;
    }

    private void inorderTraversal(TreeNode node) {
        if (node == null) return;

        inorderTraversal(node.left);
        if (prev != null) {
            ans = Math.min(ans, Math.abs(node.val - prev.val));
        }
        prev = node;
        inorderTraversal(node.right);
    }
}
