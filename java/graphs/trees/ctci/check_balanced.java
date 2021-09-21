/*
Check Balanced: Implement a function to check if a binary tree is balanced. For the purposes of this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any node never differ by more than one.
*/
//CTCI
boolean checkBalanced(Node root) {
	return checkHeight(root) != Integer.MIN_VALUE;
}

int checkHeight(Node root) {
	if (root == null) return -1;

	int leftHeight = checkHeight(root.left);
	if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

	int rightHeight = checkHeight(root.right);
	if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

	int diff = leftHeight - rightHeight;
	if (Math.abs(diff) > 1) return Integer.MIN_VALUE;
	else return Math.max(leftHeight, rightHeight) + 1;
}

//My implementation but it is wrong
boolean checkBalanced(Node root) {
	if (root == null) return true;
	int min = getMinHeight(root, 0);
	int max = getMaxHeight(root, 0);
	return max - min <= 1;
}

int getMinHeight(Node node, int height) {
	if (node == null) return height;
	return Math.min(getMinHeight(node.left, height+1), getMinHeight(node.right, height+1));
}

int getMaxHeight(Node node, int height) {
	if (node == null) return height;
	return Math.max(getMaxHeight(node.left, height+1), getMaxHeight(node.right, height+1));
}


