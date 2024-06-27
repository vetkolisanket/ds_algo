/*
1382. Balance a Binary Search Tree

Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.

A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.

 

Example 1:


Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
Example 2:


Input: root = [2,1,3]
Output: [2,1,3]
 

Constraints:

The number of nodes in the tree is in the range [1, 10^4].
1 <= Node.val <= 10^5

*/

//My soln using inorder traversal TC O(N) SC O(N)
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
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList();
        traverse(root, list);
        return makeBalancedBST(list, 0, list.size()-1);
    }

    private void traverse(TreeNode node, List<Integer> list) {
        if (node == null) return;
        traverse(node.left, list);
        list.add(node.val);
        traverse(node.right, list);
    }

    private TreeNode makeBalancedBST(List<Integer> list, int start, int end) {
        if (start > end) return null;
        if (start == end) return new TreeNode(list.get(start));
        int mid = start + (end - start)/2;
        TreeNode node = new TreeNode(list.get(mid));
        node.left = makeBalancedBST(list, start, mid-1);
        node.right = makeBalancedBST(list, mid+1, end);
        return node;
    }
}
