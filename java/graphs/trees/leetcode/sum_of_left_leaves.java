/*
404. Sum of Left Leaves

Given the root of a binary tree, return the sum of all left leaves.

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 24
Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
Example 2:

Input: root = [1]
Output: 0
 

Constraints:

The number of nodes in the tree is in the range [1, 1000].
-1000 <= Node.val <= 1000
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
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        sum += sumOfLeftLeaves(root.right);
        if (root.left != null && !hasChild(root.left)) {
            sum += root.left.val;
        } else {
            sum += sumOfLeftLeaves(root.left);
        }
        return sum;
    }
    
    private boolean hasChild(TreeNode node) {
        return node.left != null || node.right != null;
    }
}

//Soln from another attempt TC O(N) SC O(N)
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        return getSum(root.left) + sumOfLeftLeaves(root.right);
    }

    private int getSum(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) {
            return node.val;
        }
        return getSum(node.left) + sumOfLeftLeaves(node.right);
    }
}
