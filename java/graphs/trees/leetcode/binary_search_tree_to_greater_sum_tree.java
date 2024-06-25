/*
1038. Binary Search Tree to Greater Sum Tree

Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.

As a reminder, a binary search tree is a tree that satisfies these constraints:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:


Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
Example 2:

Input: root = [0,null,1]
Output: [1,null,1]
 

Constraints:

The number of nodes in the tree is in the range [1, 100].
0 <= Node.val <= 100
All the values in the tree are unique.

*/

//Soln using recursion TC O(N) SC O(N)
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
    public TreeNode bstToGst(TreeNode root) {
        int sum = traverse(root.right, 0);
        root.val = root.val + sum;
        traverse(root.left, root.val);
        return root;
    }

    private int traverse(TreeNode node, int sumSoFar) {
        if (node == null) return sumSoFar;
        int sum = traverse(node.right, sumSoFar);
        node.val = node.val + sum;
        return traverse(node.left, node.val);
    }
}

//A slightly different way using stack TC O(N) SC O(N)
class Solution {
    public TreeNode bstToGst(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        traverse(root, stack);
        int sum = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            sum += node.val;
            node.val = sum;
        }
        return root;
    }

    private void traverse(TreeNode node, Stack<TreeNode> stack) {
        if (node == null) return;
        traverse(node.left, stack);
        stack.push(node);
        traverse(node.right, stack);
    }
}
