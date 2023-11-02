/*
2265. Count Nodes Equal to Average of Subtree

Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.

Note:

The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
A subtree of root is a tree consisting of root and all of its descendants.
 

Example 1:


Input: root = [4,8,5,0,1,null,6]
Output: 5
Explanation: 
For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
For the node with value 0: The average of its subtree is 0 / 1 = 0.
For the node with value 1: The average of its subtree is 1 / 1 = 1.
For the node with value 6: The average of its subtree is 6 / 1 = 6.
Example 2:


Input: root = [1]
Output: 1
Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.
 

Constraints:

The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 1000
*/

//Soln using recusrion TC O(N) SC O(H)
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

class Holder {

    int n, sum;

    public Holder(int n, int sum) {
        this.n = n;
        this.sum = sum;
    }

}

class Solution {

    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        findAverage(root);
        return ans;
    }

    private Holder findAverage(TreeNode node) {
        if (node == null) return null;
        Holder left = findAverage(node.left);
        Holder right = findAverage(node.right);
        Holder holder = new Holder(1, node.val);
        if (left != null) {
            holder.n += left.n;
            holder.sum += left.sum;
        }
        if (right != null) {
            holder.n += right.n;
            holder.sum += right.sum;
        }
        if (node.val == (holder.sum/holder.n)) {
            ans++;
        }
        return holder;
    }

}
