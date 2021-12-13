/*
114. Flatten Binary Tree to Linked List

Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 

Example 1:


Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [0]
Output: [0]
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
 

Follow up: Can you flatten the tree in-place (with O(1) extra space)?
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

//In place soln
class Solution {
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                TreeNode temp = root.right;
                root.right = root.left;
                root.left = null;
                TreeNode node = root.right;
                while (node.right != null) node = node.right;
                node.right = temp;
            }
            root = root.right;
        }
    }
}

//Extra space soln
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;
        List<TreeNode> list = new ArrayList();
        flatten(root, list);
        int size = list.size();
        for (int i=0; i<size-1; i++) {
            TreeNode node = list.get(i);
            node.left = null;
            node.right = list.get(i+1);
        }
    }
    
    private void flatten(TreeNode node, List<TreeNode> list) {
        list.add(node);
        if (node.left != null) flatten(node.left, list);
        if (node.right != null) flatten(node.right, list);
    }
}
