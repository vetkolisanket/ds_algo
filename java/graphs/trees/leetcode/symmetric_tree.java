/*
101. Symmetric Tree

Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

 

Example 1:


Input: root = [1,2,2,3,4,4,3]
Output: true
Example 2:


Input: root = [1,2,2,null,3,null,3]
Output: false
 

Constraints:

The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100
 

Follow up: Could you solve it both recursively and iteratively?
*/

//Recursive soln
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
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }
    
    private boolean isSymmetric(TreeNode nodeA, TreeNode nodeB) {
        if (nodeA == null || nodeB == null) return nodeA == nodeB;
        return nodeA.val == nodeB.val && isSymmetric(nodeA.left, nodeB.right) && isSymmetric(nodeA.right, nodeB.left);
    }
}

//More readable stack soln
class Solution {
    public boolean isSymmetric(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        stack.push(root.left);
        stack.push(root.right);
        while (!stack.isEmpty()) {
            TreeNode p = stack.pop(), q = stack.pop();
            if (p == null && q == null) continue;
            if (p == null || q == null || p.val != q.val) return false;
            stack.push(p.left);
            stack.push(q.right);
            stack.push(p.right);
            stack.push(q.left);
        }
        return true;
    }
}

//Iterative soln
class Solution {
    public boolean isSymmetric(TreeNode root) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        Stack<TreeNode> stackLeft = new Stack();
        Stack<TreeNode> stackRight = new Stack();
        while (left != null && right != null) {
            if (left.val != right.val) return false;
            stackLeft.push(left.right);
            stackLeft.push(left.left);
            stackRight.push(right.left);
            stackRight.push(right.right);
            left = stackLeft.pop();
            right = stackRight.pop();
            while (left == null && right == null && !stackLeft.isEmpty() && !stackRight.isEmpty()) {
                left = stackLeft.pop();
                right = stackRight.pop();
            }
        }
        return left == right;
    }
}
