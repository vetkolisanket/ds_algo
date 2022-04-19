/*
230. Kth Smallest Element in a BST

Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

 

Example 1:


Input: root = [3,1,4,null,2], k = 1
Output: 1
Example 2:


Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3
 

Constraints:

The number of nodes in the tree is n.
1 <= k <= n <= 10^4
0 <= Node.val <= 10^4
 

Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
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

//Better soln using iteration instead of recursion and stack stopping when we reach kth element
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }
}

//Soln using recursion where O(H) space is used and time complexity should be O(H+k)
class Solution {
    
    int count = 0;
    
    public int kthSmallest(TreeNode root, int k) {
        return inOrderTraversal(root, k);
    }
    
    private int inOrderTraversal(TreeNode node, int k) {
        if (node == null) return -1;
        int val = inOrderTraversal(node.left, k);
        if (count == k) return val;
        count++;
        if (count == k) {
            return node.val;
        }
        return inOrderTraversal(node.right, k);
    }
}

//My soln using a list and inorder traversal
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList();
        traverseInOrder(root, list);
        return list.get(k-1);
    }
    
    private void traverseInOrder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        traverseInOrder(node.left, list);
        list.add(node.val);
        traverseInOrder(node.right, list);
    }
}


