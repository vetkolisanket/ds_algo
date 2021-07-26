/*
19. Remove Nth Node From End of List

Given the head of a linked list, remove the nth node from the end of the list and return its head.

 

Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]
 

Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
*/

//One pass using array
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode[] nodes = new ListNode[n+1];
        ListNode node = head;
        int size = 0;
        while(node != null) {
            nodes[size%(n+1)] = node;
            size++;
            node = node.next;
        }
        if(size == n) return head.next;
        node = nodes[(size-n-1)%(n+1)];
        node.next = node.next.next;
        return head;
    }
}

//One pass using arraylist
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> nodes = new ArrayList();
        ListNode node = head;
        while (node != null) {
            nodes.add(node);
            node = node.next;
        }
        int size = nodes.size();
        if (size == n) return head.next;
        node = nodes.get(size-n-1);
        node.next = node.next.next;
        return head;
    }
}

//Two pass two pointer soln
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        ListNode slow = start;
        ListNode fast = start;
        start.next = head;
        for(int i=0;i<=n;i++) {
            fast = fast.next;
        }
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return start.next;
    }
}
