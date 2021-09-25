/*
BST Sequences: A binary search tree was created by traversing through an array from left to right and inserting each element. Given a binary search tree with distinct elements, print all possible arrays that could have led to this tree.
*/
ArrayList<LinkedList<Integer>> allSequences(TreeNode node) {
	ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
	
	if (node == null) {
		result.add(new LinkedList<Integer>());
		return result;
	}

	LinkedList<Integer> prefix = new LinkedList<Integer>();
	prefix.add(node.data);

	ArrayList<LinkedList<Integer>> leftSeq = new ArrayList<LinkedList<Integer>>();
	ArrayList<LinkedList<Integer>> rightSeq = new ArrayList<LinkedList<Integer>>();

	for (LinkedList<Integer> left: leftSeq) {
		for (LinkedList<Integer> right: rightSeq) {
			ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
		weaveLists(left, right, weaved, prefix);
		result.addAll(weaved);
		}
	}
	return result;
}

void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
	if (first.size() == 0 || second.size() == 0) {
		LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
		result.addAll(first);
		result.addAll(second);
		results.add(result);
		return;
	}

	int headFirst = first.removeFirst();
	prefix.addLast(headFirst);
	weaveLists(first, second, results, prefix);
	prefix.removeLast();
	first.addFirst(headFirst);

	int secondFirst = second.removeFirst();
	prefix.addLast(secondFirst);
	weaveList(first, second, results, prefix);
	prefix.removeLast();
	second.addFirst(secondFirst);
}


