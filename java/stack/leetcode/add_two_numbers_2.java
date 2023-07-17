/*
445. Add Two Numbers II

You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 

Example 1:


Input: l1 = [7,2,4,3], l2 = [5,6,4]
Output: [7,8,0,7]
Example 2:

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [8,0,7]
Example 3:

Input: l1 = [0], l2 = [0]
Output: [0]
 

Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
 

Follow up: Could you solve it without reversing the input lists?
*/

//Soln using stack TC O(M+N) SC O(M+N) where M,N are the length of the two linked lists
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        Stack<ListNode> s1 = new Stack(), s2 = new Stack();
        ListNode node = l1;
        while (node != null) {
            s1.push(node);
            node = node.next;
        }
        node = l2;
        while (node != null) {
            s2.push(node);
            node = node.next;
        }
        int carry = 0;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            int sum = s1.pop().val + s2.pop().val + carry;
            node = new ListNode(sum%10, head);
            carry = sum/10;
            head = node;
        }
        while (!s1.isEmpty()) {
            int sum = s1.pop().val + carry;
            node = new ListNode(sum%10, head);
            carry = sum/10;
            head = node;
        }
        while (!s2.isEmpty()) {
            int sum = s2.pop().val + carry;
            node = new ListNode(sum%10, head);
            carry = sum/10;
            head = node;
        }
        if (carry != 0) {
            node = new ListNode(carry, head);
            head = node;
        }
        return head;
    }
}
