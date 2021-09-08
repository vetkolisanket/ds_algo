/*
Palindrome: Implement a function to check if a linked list is a palindrome.
*/

boolean isPalindrome(LinkedListNode l) {
	LinkedListNode r = reverseList(l);
	while (l != null) {
		if (l.data != r.data) return false;
		l = l.next;
		r = r.next;
	}
	return true;
}

LinkedListNode reverseList(LinkedListNode l) {
	LinkedListNode r = null;
	while (l != null) {
		LinkedListNode node = new LinkedListNode(l.data);
		node.next = r;
		r = node;
		l = l.next;
	}
	return r;
}
