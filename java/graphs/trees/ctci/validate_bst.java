/*
Validate BST: Implement a function to check if a binary tree is a binary search tree.
*/
//Soln that works for duplicates but requires space equal to the height of the tree for those many recursive calls
boolean validateBST(Node root) {
	return validateBST(root, null, null);
}

boolean validateBST(Node node, Integer min, Integer max) {
	if (node == null) return true;

	if ((min != null && node.data <= min) || (max != null && node.data > max)) return false;

	return validateBST(node.left, min, node.data) && validateBST(node.right, node.data, max);
}

//Soln which can work when there are no duplicates
static Integer previousVal = null;

boolean validateBST(Node node) {
	if (node == null) return true;

	if (!validateBST(node.left)) return false;

	if (previousVal != null && node.data <= previousVal) return false;
	previousVal = node.data;

	if (!validateBST(node.right)) return false;

	return true;
}
