/*
First Common Ancestor: Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not necessarily a binary search tree.
*/
//With links to parents
TreeNode findFirstCommonAncestor(TreeNode p, TreeNode q) {
	int delta = depth(p) - depth(q);
	TreeNode first = delta > 0 ? q : p;
	TreeNode second = delta > 0 p : q;
	second = goUpBy(second, Math.abs(delta));

	while (first != second && first != null && second != null) {
		first = first.parent;
		second = second.parent;
	}
	return first == null || second == null ? null : first;
}

TreeNode goUpBy(TreeNode node, int delta) {
	while (delta > 0 && node != null) {
		node = node.parent;
		delta--;
	}
	return node;
}

int depth(TreeNode node) {
	int depth = 0;
	while (node != null) {
		node = node.parent;
		depth++;
	}
	return depth;
}
