/*
1372. Longest ZigZag Path in a Binary Tree

You are given the root of a binary tree.

A ZigZag path for a binary tree is defined as follow:

Choose any node in the binary tree and a direction (right or left).
If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
Change the direction from right to left or from left to right.
Repeat the second and third steps until you can't move in the tree.
Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

Return the longest ZigZag path contained in that tree.

 

Example 1:


Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
Output: 3
Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
Example 2:


Input: root = [1,1,1,null,1,null,null,1,1,null,1]
Output: 4
Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
Example 3:

Input: root = [1]
Output: 0
 

Constraints:

The number of nodes in the tree is in the range [1, 5 * 10^4].
1 <= Node.val <= 100

*/

//soln using DFS TC O(N) SC O(N)
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

    private int pathLength = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root, true, 0);
        dfs(root, false, 0);
        return pathLength;
    }

    private void dfs(TreeNode node, boolean goLeft, int steps) {
        if (node == null) return;
        pathLength = Math.max(pathLength, steps);
        if (goLeft) {
            dfs(node.left, false, steps+1);
            dfs(node.right, true, 1);
        } else {
            dfs(node.left, false, 1);
            dfs(node.right, true, steps+1);
        }
    }
}

//Soln from another attempt TC O(N) SC O(N)
class Solution {

    private int ans = 0;
    private final boolean LEFT = true, RIGHT = false;

    public int longestZigZag(TreeNode root) {
        if (root.left != null) {
            computeZigZagPath(root.left, 1, LEFT);
        }
        if (root.right != null) {
            computeZigZagPath(root.right, 1, RIGHT);
        }
        return ans;
    }

    private void computeZigZagPath(TreeNode node, int len, boolean dir) {
        if (dir == LEFT) {
            if (node.right == null) {
                ans = Math.max(ans, len);
            } else {
                computeZigZagPath(node.right, len+1, !dir);
            }
            if (node.left != null) {
                computeZigZagPath(node.left, 1, LEFT);
            }
        } else if (dir == RIGHT) {
            if (node.left == null) {
                ans = Math.max(ans, len);
            } else {
                computeZigZagPath(node.left, len+1, !dir);
            }
            if (node.right != null) {
                computeZigZagPath(node.right, 1, RIGHT);
            }
        }
    }
}
