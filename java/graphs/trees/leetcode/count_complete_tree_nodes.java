/*
222. Count Complete Tree Nodes

Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.

 

Example 1:


Input: root = [1,2,3,4,5,6]
Output: 6
Example 2:

Input: root = []
Output: 0
Example 3:

Input: root = [1]
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [0, 5 * 10^4].
0 <= Node.val <= 5 * 10^4
The tree is guaranteed to be complete.
*/

//Soln using binary search TC O(log^2(N)) SC O(1)
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
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int depth = getDepth(root);
        if (depth == 0) return 1;
        int left = 1, right = (int) Math.pow(2, depth) - 1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (exists(root, mid, depth)) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return (int) Math.pow(2, depth) - 1 + left;
    }
    
    private int getDepth(TreeNode node) {
        int depth = 0;
        while (node.left != null) {
            depth++;
            node = node.left;
        }
        return depth;
    }
    
    private boolean exists(TreeNode node, int idx, int depth) {
        int left = 0, right = (int) Math.pow(2, depth) - 1;
        for (int i=0;i<depth;i++) {
            int mid = left + (right-left)/2;
            if (idx <= mid) {
                node = node.left;
                right = mid;
            } else {
                node = node.right;
                left = mid+1;
            }
        }
        return node != null;
    }
}
