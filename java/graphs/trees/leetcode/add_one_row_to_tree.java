/*
623. Add One Row to Tree

Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.

Note that the root node is at depth 1.

The adding rule is:

Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with value val as cur's left subtree root and right subtree root.
cur's original left subtree should be the left subtree of the new left subtree root.
cur's original right subtree should be the right subtree of the new right subtree root.
If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the new root of the whole original tree, and the original tree is the new root's left subtree.
 

Example 1:


Input: root = [4,2,6,3,1,5], val = 1, depth = 2
Output: [4,1,1,2,null,null,6,3,1,5]
Example 2:


Input: root = [4,2,null,3,1], val = 1, depth = 3
Output: [4,2,null,1,1,3,null,null,1]
 

Constraints:

The number of nodes in the tree is in the range [1, 10^4].
The depth of the tree is in the range [1, 10^4].
-100 <= Node.val <= 100
-10^5 <= val <= 10^5
1 <= depth <= the depth of tree + 1

*/

//My soln using BFS TC O(N) SC O(N) where N is the no. of nodes in the tree
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
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) return new TreeNode(val, root, null);
        int curDepth = 1;
        Queue<TreeNode> queue = new ArrayDeque();
        queue.offer(root);
        while (curDepth < depth) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                TreeNode left = node.left;
                TreeNode right = node.right;
                if (curDepth == depth-1) {
                    TreeNode newLeft = new TreeNode(val, left, null);
                    TreeNode newRight = new TreeNode(val, null, right);
                    node.left = newLeft;
                    node.right = newRight;
                } else {
                    if (left != null) queue.offer(left);
                    if (right != null) queue.offer(right);
                }
            }
            curDepth++;
        }
        return root;
    }
}

//Using DFS TC O(N) SC O(N)
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
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) return new TreeNode(val, root, null);
        dfs(root, val, depth, 1);
        return root;
    }
    
    private void dfs(TreeNode node, int val, int depth, int curDepth) {
        if (node == null) return;
        if (curDepth == depth) return;
        if (curDepth == depth-1) {
            TreeNode left = node.left;
            TreeNode right = node.right;
            TreeNode newLeft = new TreeNode(val, left, null);
            TreeNode newRight = new TreeNode(val, null, right);
            node.left = newLeft;
            node.right = newRight;
        } else {
            dfs(node.left, val, depth, curDepth+1);
            dfs(node.right, val, depth, curDepth+1);
        }
    }
}
