/*
144. Binary Tree Preorder Traversal

Given the root of a binary tree, return the preorder traversal of its nodes' values.

 

Example 1:


Input: root = [1,null,2,3]
Output: [1,2,3]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [1]
Output: [1]
 

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 

Follow up: Recursive solution is trivial, could you do it iteratively?
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
//Iterative approach TC O(n) SC O(n)
class Solution {
    
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack();
        while (node != null) {
            list.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) node = node.left;
            else node = stack.isEmpty() ? null : stack.pop();
        }
        return list;
    }
}

//Recursive approach TC O(n) SC O(n)
class Solution {
    
    List<Integer> list = new ArrayList();
    
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root != null) {
            list.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return list;
    }
}

//Soln using Morris traversal TC O(n) SC O(1)
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        TreeNode curr = root, last = null;
        while (curr != null) {
            if (curr.left == null) {
                list.add(curr.val);
                curr = curr.right;
            } else {
                last = curr.left;
                while (last.right != null && last.right != curr) {
                    last = last.right;
                }
                if (last.right == null) {
                    last.right = curr;
                    list.add(curr.val);
                    curr = curr.left;
                } else {
                    last.right = null;
                    curr = curr.right;
                }
            }
        }
        return list;
    }
}
