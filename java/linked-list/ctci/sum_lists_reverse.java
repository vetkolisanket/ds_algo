/*
Sum Lists: You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.
EXAMPLE
Input: (7-) 1 -) 6) + (5 -) 9 -) 2).Thatis,617 + 295. Output: 2 -) 1 -) 9.That is, 912.
*/

//Recursive code
LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2){
	int carry = 0;
	return addLists(l1, l2, carry);
}

private LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int carry){
	if (l1 == null && l2 == null && carry == 0) return null;
	LinkedListNode node = new LinkedListNode();
	int val = carry;
	if (l1 != null) val += l1.val;
	if (l2 != null) val += l2.val;
	node.val = val%10;
	if (l1 != null || l2 != null) {
		node.next = addLists(l1 != null ? l1.next : null, l2 != null ? l2.next : null, val/10);
	}
	return node;
}

//Iterative code
LinkedListNode sumLists(LinkedListNode a, LinkedListNode b){
	if (a==null && b==null) return null;
	int carry = 0;
	LinkedListNode c = new LinkedListNode();
	LinkedListNode n = c;
	while (a != null || b!= null || carry == 1){
		LinkedListNode temp = new LinkedListNode();
		n.next = temp;
		n = n.next;
		int x = a != null ? a.val : 0;
		int y = b != null ? b.val : 0;
		int z = x + y + carry;
		n.val = z%10;
		carry = z/10;
	}
	return c.next;
}
