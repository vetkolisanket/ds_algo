/*
95. Unique Binary Search Trees II

Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.

 

Example 1:


Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
Example 2:

Input: n = 1
Output: [[1]]
 

Constraints:

1 <= n <= 8
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
    public List<TreeNode> generateTrees(int n) {
        return generateTreeList(1, n);
    }
    
    private List<TreeNode> generateTreeList(int start, int end) {
        List<TreeNode> list = new ArrayList();
        
        if (start > end) {
            list.add(null);
        } else {
            for (int i=start; i<=end; i++) {
                List<TreeNode> left = generateTreeList(start, i-1);
                List<TreeNode> right = generateTreeList(i+1, end);
            
                for (TreeNode l: left) {
                    for (TreeNode r: right) {
                        TreeNode node = new TreeNode(i, l, r);
                        list.add(node);
                    }
                }
            }
        }
        
        return list;
    }
    
}
