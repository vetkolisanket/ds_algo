/*
Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.
DEFINI TION
Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so as to make a loop in the linked list.
EXAMPLE
Input: A -) B -) C -) 0 -) E -) C[thesameCasearlierl Output: C
*/

LinkedListNode findLoopStartingPoint(LinkedListNode head){
	LinkedListNode slow = head;
	LinkedListNode fast = head;
	while (fast != null && fast.next != null){
		slow = slow.next;
		fast = fast.next.next;
		if(slow == fast) break;
	}

	if (fast == null || fast.next == null) return null;

	slow = head;
	while (slow != fast) {
		slow = slow.next;
		fast = fast.next;
	}
	return fast;
}
