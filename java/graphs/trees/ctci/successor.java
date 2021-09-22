/*
Successor: Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a binary search tree. You may assume that each node has a link to its parent.
*/
Node findSuccessor(Node node) {
	if (node == null) return null;

	if (node.right != null) return leftMostChild(node.right);
	Node parent = node.parent;
	while (parent != null && parent.left != node) {
		node = parent;
		parent = parent.parent;
	}
	return parent;
}

Node findLeftMostChild(Node node) {
	while (node.left != null) node = node.left;
	return node;
}
