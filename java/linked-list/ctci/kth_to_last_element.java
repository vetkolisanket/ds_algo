/*
Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
*/

//Iterative soln takes O(n) time and O(1) space. Assuming k=0 implies last element.
LinkedListNode findKthToLastElement(int k, LinkedListNode head){
	LinkedListNode first = new LinkedListNode(0);
	first.next = head;
	LinkedListNode second = head;
	for(int i=0;i<k;i++){
		if(second == null) return;
		second = second.next;
	}
	while (second != null){
		second = second.next;
		first = first.next;
	}
	return first;
}

//Recursive. Takes O(n) space for each recursive call. Assuming k=0 implies last element.
class Index {
	int val = 0;
}

LinkedListNode findKthToLastNode(LinkedListNode head, int k){
	Index idx = new Index();
	return findKthToLastNode(head, k, idx);
}

LinkedListNode findKthToLastNode(LinkedListNode head, int k, Index idx){
	if (head == null) return null;
	LinkedListNode node = findKthToLastNode(head.next, k, idx);
	if (idx.val == k) {
		return head;
	}
	idx.val = idx.val + 1;
	return node;
}
