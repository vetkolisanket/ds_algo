/*
Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the inter- secting node. Note that the intersection is defined based on reference, not value. That is, if the kth node of the first linked list is the exact same node (by reference) as the jth node of the second linked list, then they are intersecting.
*/

// O(n+m) soln
LinkedListNode intersection(LinkedListNode l1, LinkedListNode l2){
	if (l1 == null || l2 == null) return null;
	int len1 = length(l1);
	int len2 = length(l2);
	if (len1 < len2) {
		l2 = move(l2, l2-l1);
	} else {
		l1 = move(l1, l1-l2);
	}
	while (l1 != null) {
		if (l1 == l2) return l1;
		l1 = l1.next;
		l2 = l2.next;
	}
	return null;
}

int length(LinkedListNode l){
	int size = 0;
	while(l != null) {
		l = l.next;
		size++;
	}
	return size;
}

LinkedListNode move(LinkedListNode l, int steps){
	while (steps > 0){
		l = l.next;
		steps--;
	}
	return l;
}

//Brute Force O(mn)
LinkedListNode intersection(LinkedListNode l1, LinkedListNode l2){
	while (l2 != null){
		LinkedListNode node = l1;
		while (node != null){
			if (node == l2) return node;
			node = node.next;
		}
		l2 = l2.next;
	}
	return null;
}
