/*
1026. Maximum Difference Between Node and Ancestor

Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.

A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.

 

Example 1:


Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation: We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
Example 2:


Input: root = [1,null,2,null,0,3]
Output: 3
 

Constraints:

The number of nodes in the tree is in the range [2, 5000].
0 <= Node.val <= 10^5
*/

//My soln using recursion and updating min max at each node TC O(N) SC O(N)
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
    public int maxAncestorDiff(TreeNode root) {
        return Math.max(maxDiff(root.left, root.val, root.val, 0), maxDiff(root.right, root.val, root.val, 0));
    }

    private int maxDiff(TreeNode node, int low, int high, int diff) {
        if (node == null) return diff;
        if (node.val <= low) {
            diff = Math.max(diff, Math.abs(node.val - high));
            return Math.max(diff, Math.max(maxDiff(node.left, node.val, high, diff), maxDiff(node.right, node.val, high, diff)));
        } else if (node.val >= high) {
            diff = Math.max(diff, Math.abs(low-node.val));
            return Math.max(diff, Math.max(maxDiff(node.left, low, node.val, diff), maxDiff(node.right, low, node.val, diff)));
        } else {
            return Math.max(maxDiff(node.left, low, high, diff), maxDiff(node.right, low, high, diff));
        }
    }
}

//A simpler and more readable soln TC O(N) SC O(N)
class Solution {
    public int maxAncestorDiff(TreeNode root) {
        return maxDiff(root, root.val, root.val);
    }

    private int maxDiff(TreeNode node, int max, int min) {
        if (node == null) return max-min;
        max = Math.max(max, node.val);
        min = Math.min(min, node.val);
        int left = maxDiff(node.left, max, min);
        int right = maxDiff(node.right, max, min);
        return Math.max(left, right);
    }
}
