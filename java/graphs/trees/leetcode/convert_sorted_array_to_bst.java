/*
108. Convert Sorted Array to Binary Search Tree

Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.

A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

 

Example 1:


Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:


Input: nums = [1,3]
Output: [3,1]
Explanation: [1,3] and [3,1] are both a height-balanced BSTs.
 

Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in a strictly increasing order.
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

//TC O(n) SC O(log(n)) where n is the length of the array
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return getBST(nums, 0, nums.length-1);
    }
    
    private TreeNode getBST(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = (end+start)/2;
        // int mid = (end-start)/2 + start;
        // int mid = ((end-start)>>1) + start;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = getBST(nums, start, mid-1);
        node.right = getBST(nums, mid+1, end);
        return node;
    }
    
}
