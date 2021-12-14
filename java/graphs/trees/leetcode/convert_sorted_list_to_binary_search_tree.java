/*
109. Convert Sorted List to Binary Search Tree

Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

 

Example 1:


Input: head = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
Example 2:

Input: head = []
Output: []
Example 3:

Input: head = [0]
Output: [0]
Example 4:

Input: head = [1,3]
Output: [3,1]
 

Constraints:

The number of nodes in head is in the range [0, 2 * 104].
-10^5 <= Node.val <= 10^5
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
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
//O(lgn) soln
class Solution {
    
    ListNode listNode;
    
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        
        listNode = head;
        ListNode runner = head;
        int size = 0;
        
        while (runner != null) {
            runner = runner.next;
            size++;
        }
        
        return getTree(0, size-1);
    }
    
    private TreeNode getTree(int start, int end) {
        if (start > end) return null;
        
        int mid = start + (end - start)/2;
        
        TreeNode left = getTree(start, mid-1);
        TreeNode treeNode = new TreeNode(listNode.val);
        treeNode.left = left;
        listNode = listNode.next;
        
        TreeNode right = getTree(mid+1, end);
        treeNode.right = right;
        
        return treeNode;
    }
}

//O(nlgn) soln
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return getTree(head, null);
    }
    
    private TreeNode getTree(ListNode start, ListNode end) {
        if (start == end) return null;
        
        ListNode slow = start, fast = start;
        while (fast != end && fast.next != end) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        TreeNode treeNode = new TreeNode(slow.val);
        treeNode.left = getTree(start, slow);
        treeNode.right = getTree(slow.next, end);
        return treeNode;
    }
}
