/*
143. Reorder List

You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

 

Example 1:


Input: head = [1,2,3,4]
Output: [1,4,2,3]
Example 2:


Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]
 

Constraints:

The number of nodes in the list is in the range [1, 5 * 10^4].
1 <= Node.val <= 1000
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
//O(n) time O(1) space
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
    public void reorderList(ListNode head) {
        ListNode mid = getMid(head);
        ListNode nextToMid = mid.next;
        mid.next = null;
        ListNode p2 = reverse(nextToMid);
        ListNode p1 = head, p1Next;
        while (p1 != null && p2 != null) {
            p1Next = p1.next;
            p1.next = p2;
            
            p1 = p2;
            p2 = p1Next;
        }
    }
    
    private ListNode getMid(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head, next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}

//Recursion based soln
class Solution {
    public void reorderList(ListNode head) {
        ListNode [] left =  new ListNode[1];// it will create in heap
        left[0]  =  head;
        reorder(left,head);
        
    }
    
    // left pointer will be created in heap and right pointer will be created in stack
     public void reorder(ListNode left[],ListNode right){
        if(right==null){
            return ;
        }
        reorder(left,right.next);
        
    // in post area of recursion right pointer coming back(because of function remove from recursion stack)
   // and we move left pointer forward 
        if(left[0].next!=null){
            ListNode leftNext =  left[0].next;
            left[0].next =  right;
            right.next =  leftNext;
            left[0] =  leftNext;            
        }
        
    // as we need to  merge till left pointer behind the right pointer 
        if(left[0].next == right){
            left[0].next =  null;
        }                        
    }
}

//My soln O(n) time O(n) space
class Solution {
    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i=0, j=list.size()-1;
        while (i<=j) {
            if (node == null) node = head;
            else {
                node.next = list.get(i);
                node = node.next;
            }
            i++;
            if (i <= j) {
                node.next = list.get(j);
                node = node.next;
                j--;
            }
        }
        node.next = null;
    }
}
