/*
543. Diameter of Binary Tree

Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

 

Example 1:


Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
Example 2:

Input: root = [1,2]
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [1, 10^4].
-100 <= Node.val <= 100

*/

//My soln using recursion TC O(N) SC O(N)
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

    int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        getLength(root);
        return ans;
    }

    private int getLength(TreeNode node) {
        if (node.left == null && node.right == null) return 0;
        if (node.left == null) {
            int len = 1+getLength(node.right);
            ans = Math.max(ans, len);
            return len;
        } else if (node.right == null) {
            int len = 1+getLength(node.left);
            ans = Math.max(ans, len);
            return len;
        }
        int left = 1+getLength(node.left);
        int right = 1+getLength(node.right);
        ans = Math.max(ans, left+right);
        return Math.max(left, right);
    }
}
