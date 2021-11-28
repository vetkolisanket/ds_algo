/*
86. Partition List

Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

 

Example 1:


Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
Example 2:

Input: head = [2,1], x = 2
Output: [1,2]
 

Constraints:

The number of nodes in the list is in the range [0, 200].
-100 <= Node.val <= 100
-200 <= x <= 200

*/


//A more readable soln
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode smallerHead = new ListNode(0), biggerHead = new ListNode(0);
        ListNode smaller = smallerHead, bigger = biggerHead;
        while (head != null) {
            if (head.val < x) {
                smaller = smaller.next = head;
            } else {
                bigger = bigger.next = head;
            }
            head = head.next;
        }
        smaller.next = biggerHead.next;
        bigger.next = null;
        return smallerHead.next;
    }
}

//My soln
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
    public ListNode partition(ListNode head, int x) {
        ListNode smallList = null, bigList = null;
        ListNode node = head;
        ListNode tempSmall = null, tempBig = null;
        while (node != null) {
            if (node.val < x) {
                if (smallList == null) {
                    smallList = node;
                    tempSmall = node;
                } 
                else {
                    smallList.next = node;
                    smallList = smallList.next;
                }
            } else {
                if (bigList == null) {
                    bigList = node;
                    tempBig = node;
                } 
                else {
                    bigList.next = node;
                    bigList = bigList.next;
                }
            }
            node = node.next;
        }
        if (bigList != null) bigList.next = null;
        if (smallList == null) return tempBig;
        smallList.next = tempBig;
        return tempSmall;
    }
}
