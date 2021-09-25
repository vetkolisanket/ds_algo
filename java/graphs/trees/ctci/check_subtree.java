/*
Check Subtree: T l and T2 are two very large binary trees, with T l much bigger than T2. Create an
algorithm to determine if T2 is a subtree of Tl.
A tree T2 is a subtree ofT i if there exists a node n in T i such that the subtree of n is identical to T2.
That is, if you cut off the tree at node n, the two trees would be identical.
*/
//CTCI soln O(n+m) TC and SC
boolean checkSubtree(Node a, Node b) {
	StringBuilder sb1 = new StringBuilder();
	StringBuilder sb2 = new StringBuilder();

	createPreOrderString(sb1, a);
	createPreOrderString(sb2, b);

	return sb1.indexOf(sb2) != -1;
}

void createPreOrderTraversal(StringBuilder sb, Node node) {
	if (node == null) {
		sb.append("X");
		return;
	}
	sb.append(node.data);
	createPreOrderTraversal(sb, node.left);
	createPreOrderTraversal(sb, node.right);
}

//My soln, seems correct but TC is O(nm)
boolean checkSubtree(Node a, Node b) {
	if (a == null) return false;
	if (areTreesIdentical(a, b)) return true;
	return checkSubtree(a.left, b) || checkSubtree(a.right, b);
}

boolean areTreesIdentical(Node a, Node b) {
	if (a == null && b == null) return true;
	else if (a == null || b == null) return false;
	else if (a.data != b.data) return false;
	return areTreesIdentical(a.left, b.left) && areTreesIdentical(a.right, b.right);
}
