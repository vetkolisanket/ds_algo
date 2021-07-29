/*
25. Reverse Nodes in k-Group

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.

 

Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]
Example 2:


Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]
Example 3:

Input: head = [1,2,3,4,5], k = 1
Output: [1,2,3,4,5]
Example 4:

Input: head = [1], k = 1
Output: [1]
 

Constraints:

The number of nodes in the list is in the range sz.
1 <= sz <= 5000
0 <= Node.val <= 1000
1 <= k <= sz

Follow-up: Can you solve the problem in O(1) extra memory space?
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
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k==1 || head == null || head.next == null) return head;
        int l=0;
        ListNode node = head;
        while(node != null) {
            l++;
            node = node.next;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p0 = dummy;
        ListNode p1 = dummy.next;
        for(int i=0;i<l/k;i++) {
            for(int j=0;j<k-1;j++) {
                ListNode t = p1.next;
                p1.next = t.next;
                t.next = p0.next;
                p0.next = t;
            }
            p0 = p1;
            p1 = p0.next;
        }
        return dummy.next;
    }
}

//Recursive soln
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k==1 || head == null || head.next == null) return head;
        
        ListNode c = head;
        int count = 0;
        while (c != null && count != k) {
            c = c.next;
            count++;
        }
        if(count == k) {
            c = reverseKGroup(c, k);
            
            while (count > 0) {
                ListNode t = head.next;
                head.next = c;
                c = head;
                head = t;
                count--;
            }
            head = c;
        }
        return head;
    }
}

