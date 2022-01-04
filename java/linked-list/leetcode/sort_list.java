/*
148. Sort List

Given the head of a linked list, return the list after sorting it in ascending order.

Example 1:

Input: head = [4,2,1,3]
Output: [1,2,3,4]
Example 2:

Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
Example 3:

Input: head = []
Output: []
 
Constraints:

The number of nodes in the list is in the range [0, 5 * 10^4].
-10^5 <= Node.val <= 10^5
 

Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
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

//O(1) space and O(nlgn) time approach, bottom-up merge sort
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        int n=0;
        ListNode node = head;
        while (node != null) {
            n++;
            node = node.next;
        }
        
        ListNode dummy = new ListNode(0, head);
        for (int step=1;step < n; step <<= 1) {
            ListNode pre = dummy;
            ListNode cur = dummy.next;
            while (cur != null) {
                ListNode left = cur;
                ListNode right = split(left, step);
                cur = split(right, step);
                pre = merge(left, right, pre);
            }
        }
        return dummy.next;
    }
    
    private ListNode split(ListNode head, int n) {
        if (head == null) return null;
        
        for (int i=1;head.next != null && i<n; i++) head = head.next;
        
        ListNode right = head.next;
        head.next = null;
        return right;
    }
    
    private ListNode merge(ListNode left, ListNode right, ListNode pre) {
        ListNode cur = pre;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        
        if (left != null) cur.next = left;
        else if (right != null) cur.next = right;
        while (cur.next != null) cur = cur.next;
        return cur;
    }
}

//Insertion sort based approach
class Solution {
    public ListNode sortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode pre = dummy;
        while (head != null) {
            ListNode next = head.next;
            while (pre.next != null && pre.next.val < head.val) pre = pre.next;
            
            head.next = pre.next;
            pre.next = head;
            if (next != null && pre.val > next.val) pre = dummy;
            head = next;
        }
        return dummy.next;
    }
}


