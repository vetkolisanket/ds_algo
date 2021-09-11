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

//Iterative approach
boolean isPalindrome(LinkedListNode head){
	LinkedListNode slow = head;
	LinkedListNode fast = head;

	Stack<Integer> stack = new Stack();
	
	while (fast != null && fast.next != null){
		stack.push(slow.data);
		slow = slow.next;
		fast = fast.next.next;
	}

	if(fast != null){
		slow = slow.next;
	}

	while (slow != null){
		int top = stack.pop().intValue();
		if (top != slow.data) return false;
		slow = slow.next;
	}
	return true;
}

//Recursive approach
boolean isPalindrome(LinkedListNode head) {
	int length = lengthOfList(head);
	Result p = isPalindromeRecurse(head, length);
	return p.result;
}

Result isPalindromeRecurse(LinkedListNode head, int length){
	if (head == null || length <= 0) {
		return new Result(head, true);
	} else if (length == 1) {
		return new Result(head.next, true);
	}

	Result res = isPalindromeRecurse(head.next, length - 2);

	if (!res.result || res.node == null) return res;

	res.result = (head.data == res.node.data);
	res.node = res.node.next;

	return res;
}

int lengthOfList(LinkedListNode n){
	int size = 0;
	while (n != null) {
		size++;
		n = n.next;
	}
	return size;
}
