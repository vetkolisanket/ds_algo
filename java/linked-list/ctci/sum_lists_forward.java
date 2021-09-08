/*
Sum Lists: You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.
EXAMPLE
Input: (7-) 1 -) 6) + (5 -) 9 -) 2).Thatis,617 + 295. Output: 2 -) 1 -) 9.That is, 912.
FOLLOW UP
Suppose the digits are stored in forward order. Repeat the above problem. EXAMPLE
Input: (6 -) 1 -) 7) + (2 -) 9 -) 5).Thatis,617 + 295. Output: 9 -) 1 -) 2.That is, 912.
*/

class PartialSum {
	LinkedListNode sum = null;
	int carry = 0;
}

LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2){
	int len1 = length(l1);
	int len2 = length(l2);

	if (l1 < l2){
		l1 = padList(l1, l2-l1);
	} else {
		l2 = padList(l2, l1-l2);
	}

	PartialSum sum = addListHelper(l1, l2);
	if (sum.carry == 0) return sum.sum;
	return insertBefore(sum.sum, sum.carry);
}

int length(LinkedListNode l){
	int len = 0;
	while (l != null) {
		len++;
		l = l.next;
	}
	return len;
}

LinkedListNode padList(LinkedListNode l, int padding) {
	LinkedListNode head = l;
	for (int i=0;i<padding;i++) {
		head = insertBefore(head, 0);
	}
	return head;
}

PartialSum addListHelper(LinkedListNode l1, LinkedListNode l2){
	if (l1 == null && l2 == null) {
		PartialSum sum = new PartialSum();
		return sum;
	}

	PartialSum sum = addListHelper(l1.next, l2.next);
	int val = sum.carry + l1.val + l2.val;
	LinkedListNode result = addBefore(sum.sum, val%10);
	sum.sum = result;
	sum.carry = val/10;
	return sum;
}

LinkedListNode insertBefore(LinkedListNode l, int val) {
	LinkedListNode node = new LinkedListNode(val);
	node.next = l;
	return node;
}
