class MyBinarySearchTree {

	class TreeNode {
		int val;
		TreeNode left, right;

		public TreeNode(int val) {
			this.val = val;
		}
	}

	TreeNode root;

	public void add(int val) {
		TreeNode node = new TreeNode(val);
		if (root == null) root = node;
		else if (val <= root.val) {
			root.left = addNode(root.left, node);
		} else {
			root.right = addNode(root.right, node);
		}
	}

	private TreeNode addNode(TreeNode root, TreeNode node) {
		if (root == null) return node;
		else if (node.val <= root.val) {
			root.left = addNode(root.left, node);
		} else {
			root.right = addNode(root.right, node);
		}
		return root;
	}

	public void printInOrderTraversal() {
		printInOrderTraversal(root);
	}

	public void printInOrderTraversal(TreeNode node) {
		if (node == null) return;
		printInOrderTraversal(node.left);
		System.out.print(node.val + " ");
		printInOrderTraversal(node.right);
	}

	public static void main(String[] args) {
		MyBinarySearchTree tree = new MyBinarySearchTree();
		tree.add(1);
		tree.add(0);
		tree.add(2);
		tree.add(3);
		tree.add(7);
		tree.add(9);
		tree.add(5);
		tree.add(8);
		tree.add(6);
		tree.add(4);
		tree.printInOrderTraversal();
	}

}
