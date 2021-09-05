/*
Remove Dups: Write code to remove duplicates from an unsorted li nked list. FOLLOW UP
How would you solve this problem if a temporary buffer is not allowed?
*/
//Buffer Allowed O(N) time
void removeDuplicates(LinkedListNode n){
	if(n == null) return;
	Set s = new HashSet<Integer>();
	LinkedListNode previous = n;
	s.add(n.data);
	while(previous.next != null) {
		if(s.contains(previous.next.data)){
			previous.next = previous.next.next;
		} else {
			s.add(previous.next.data);
		}
		previous = previous.next;
	}
}

//No Buffer Allowed O(1) space O(N^2) time
void removeDuplicates(LinkedListNode n){
	LinkedListNode a = n;
	while (a != null){
		LinkedListNode b = a;
		while (b.next != null){
			if(a.data == b.next.data) {
				b.next = b.next.next;
			} else {
				b = b.next;
			}
		}
		a = a.next;
	}
}
