/*
Merge Sort for Linked List

Given Pointer/Reference to the head of the linked list, the task is to Sort the given linked list using Merge Sort.
Note: If the length of linked list is odd, then the extra node should go in the first list while splitting.

Example 1:

Input:
N = 5
value[]  = {3,5,2,4,1}
Output: 1 2 3 4 5
Explanation: After sorting the given
linked list, the resultant matrix
will be 1->2->3->4->5.
Example 2:

Input:
N = 3
value[]  = {9,15,0}
Output: 0 9 15
Explanation: After sorting the given
linked list , resultant will be
0->9->15.
Your Task:
For C++ and Python: The task is to complete the function mergeSort() which sort the linked list using merge sort function.
For Java: The task is to complete the function mergeSort() and return the node which can be used to print the sorted linked list.

Expected Time Complexity: O(N*Log(N))
Expected Auxiliary Space: O(N)

Constraints:
1 <= T <= 100
1 <= N <= 10^5
*/

class Solution
{
    //Function to sort the given linked list using Merge Sort.
    static Node mergeSort(Node head)
    {
        // add your code here
        if (head == null || head.next == null) return head;
        Node mid = findMid(head);
        Node next = mid.next;
        mid.next = null;
        head = mergeSort(head);
        next = mergeSort(next);
        return merge(head, next);
    }
    
    private static Node findMid(Node node) {
        Node slow = node, fast = node;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    private static Node merge(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;
        if (a.data <= b.data) {
            a.next = merge(a.next, b);
            return a;
        } else {
            b.next = merge(a, b.next);
            return b;
        }
    }
    
}
