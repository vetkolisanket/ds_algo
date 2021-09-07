/*
Partition: Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes greater than or equal to x. lf x is contained within the list, the values of x only need to be after the elements less than x (see below).The partition element x can appear anywhere in the "right partition"; it does not need to appear between the left and right partitions.
2.4
94
Cracking the Coding Interview, 6th Edition
EXAMPLE
Input: 3 -> 5 -> 8 -> 5 ->10 -> 2 -> 1[partition=5) 
Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
*/

//Maintaining two separate lists and merging in the end. This approach is to be used when the order is to be maintained
LinkedListNode partition(LinkedListNode node, int partition){
	LinkedListNode beforeStart = null;	
	LinkedListNode beforeEnd = null;
	LinkedListNode afterStart = null;
	LinkedListNode afterEnd = null;
	while (node != null){
		LinkedListNode next = node.next;
		node.next = null;                //This line is needed to make sure afterEnd.next doesn't refer to some element smaller than partition if that condition arrives
		if(node.data < partition){
			if (beforeStart == null){
				beforeStart = node;
				beforeEnd = node;
			} else {
				beforeEnd.next = node;
				beforeEnd = node;
			}
		} else {
			if (afterStart == null){
				afterStart = node;
				afterEnd = node;
			} else {
				afterEnd.next = node;
				afterEnd = node;
			}
		}
		node = next;
	}
	if (beforeStart == null) return afterStart;
	beforeEnd.next = afterStart;
	return beforeStart
	
}

//Less complicated soln where the order is not maintained
LinkedListNode partition(LinkedListNode n, int partition){
	LinkedListNode head = n;
	LinkedListNode tail = n;
	while (n != null){
		LinkedListNode next = n.next;
		if(n.data < partition) {
			n.next = head;
			head = n;
		} else {
			tail.next = n;
			tail = n;
		}
		n = next;
	}
	tail.next = null;
	return head;
}
