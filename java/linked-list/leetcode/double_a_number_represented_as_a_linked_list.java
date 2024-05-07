/*
2816. Double a Number Represented as a Linked List

You are given the head of a non-empty linked list representing a non-negative integer without leading zeroes.

Return the head of the linked list after doubling it.

 

Example 1:


Input: head = [1,8,9]
Output: [3,7,8]
Explanation: The figure above corresponds to the given linked list which represents the number 189. Hence, the returned linked list represents the number 189 * 2 = 378.
Example 2:


Input: head = [9,9,9]
Output: [1,9,9,8]
Explanation: The figure above corresponds to the given linked list which represents the number 999. Hence, the returned linked list reprersents the number 999 * 2 = 1998. 
 

Constraints:

The number of nodes in the list is in the range [1, 10^4]
0 <= Node.val <= 9
The input is generated such that the list represents a number that does not have leading zeros, except the number 0 itself.

*/

//Soln using stack TC O(N) SC O(N)
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
    public ListNode doubleIt(ListNode head) {
        Stack<Integer> stack = new Stack();
        ListNode node = head;
        while (node != null) {
            stack.push(node.val);
            node = node.next;
        }
        int carry = 0;
        ListNode cur = null;
        while (!stack.isEmpty()) {
            int val = stack.pop()*2 + carry;
            node = new ListNode(val%10, cur);
            carry = val/10;
            cur = node;
        }
        if (carry > 0) {
            cur = new ListNode(carry, cur);
        }
        return cur;
    }
}

//A more space efficient single pointer soln TC O(N) SC O(1)
class Solution {
    public ListNode doubleIt(ListNode head) {
        ListNode dummyHead = new ListNode(0, head), node = dummyHead;
        while (node != null) {
            int val = node.val*2;
            if (node.next != null && node.next.val > 4) {
                val++;
            }
            node.val = val%10;
            node = node.next;
        }
        return dummyHead.val == 0 ? dummyHead.next : dummyHead;
    }
}
