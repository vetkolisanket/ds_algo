/*
100. Same Tree

Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

 

Example 1:


Input: p = [1,2,3], q = [1,2,3]
Output: true
Example 2:


Input: p = [1,2], q = [1,null,2]
Output: false
Example 3:


Input: p = [1,2,1], q = [1,1,2]
Output: false
 

Constraints:

The number of nodes in both trees is in the range [0, 100].
-10^4 <= Node.val <= 10^4
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

//TC O(N) SC O(N)
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) return p == q;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

//Iterative version TC O(N) SC O(N)
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (!checkNodes(p, q)) return false;
        Queue<TreeNode> pQueue = new ArrayDeque();
        Queue<TreeNode> qQueue = new ArrayDeque();
        if (p != null) pQueue.offer(p);
        if (q != null) qQueue.offer(q);
        while (!pQueue.isEmpty() && !qQueue.isEmpty()) {
            p = pQueue.poll();
            q = qQueue.poll();
            if (!checkNodes(p, q)) return false;
            if (p.left != null && q.left != null) {
                pQueue.offer(p.left);
                qQueue.offer(q.left);
            } else if ((p.left == null && q.left != null) || (p.left != null && q.left == null)) return false;
            if (p.right != null && q.right != null) {
                pQueue.offer(p.right);
                qQueue.offer(q.right);
            } else if ((p.right == null && q.right != null) || (p.right != null && q.right == null)) return false;
        }
        return pQueue.isEmpty() && qQueue.isEmpty();
    }

    private boolean checkNodes(TreeNode p, TreeNode q) {
        if (p == null || q == null) return p == q;
        return p.val == q.val;
    }
}
