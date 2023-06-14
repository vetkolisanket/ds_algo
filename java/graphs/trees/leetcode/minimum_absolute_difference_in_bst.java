/*
Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.

 

Example 1:


Input: root = [4,2,6,1,3]
Output: 1
Example 2:


Input: root = [1,0,48,null,null,12,49]
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [2, 10^4].
0 <= Node.val <= 10^5

*/

//Soln using sort TC O(NlogN) SC O(N)
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
    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList();
        Queue<TreeNode> q = new ArrayDeque();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            list.add(Math.abs(node.val));
            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
        Collections.sort(list);
        int ans = Integer.MAX_VALUE;
        for (int i=1;i<list.size();i++) {
            ans = Math.min(ans, list.get(i) - list.get(i-1));
        }
        return ans;
    }
}
