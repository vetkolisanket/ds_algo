/*
List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth (e.g., if you have a tree with depth 0, you'll have 0 linked lists).
*/
//CTCI BFS
ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
	ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
	LinkedList<TreeNode> current = new LinkedList<TreeNode>();
	if (root != null) current.add(root);
	while (current.size() > 0) {
		result.add(current);
		LinkedList<TreeNode> parents = current;
		current = new LinkedList<TreeNode>();
		for (TreeNode parent: parents) {
			if (parent.left != null) {
				current.add(parent.left);
			}
			if (parent.right != null) {
				current.add(parent.right);
			}
		}
	}
	return result;
}


//CTCI DFS
ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
	ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
	createLevelLinkedList(root, lists, 0);
	return lists;
}

void createLevelLinkedList(TreeNode root, ArrayList<LikedList<TreeNode>> lists, int level) {
	if (root == null) return;

	LinkedList<TreeNode> list = null;
	if (lists.size() == level) {
		list = new LinkedList<TreeNode>();
		lists.add(list);
	} else list = lists.get(level);
	list.add(root);
	createLevelLinkedList(root.left, lists, level + 1);
	createLevelLinkedList(root.right, lists, level + 1);
}

//My modified to the point soln
void listOfDepths(Node root) {
        List<LinkedList<Node>> list = new ArrayList<LinkedList<Node>>();
        int depth = 1;
        populateList(root, list, depth);
}

void populateList(Node node, List<LinkedList<Node>> lists, int depth) {
        if (node == null) return;
        if (lists.size() < depth) {
                LinkedList<Node> list = new LinkedList<Node>();
                lists.add(list);
        }
        lists.get(depth).add(node);
        populateList(node.left, map, depth+1);
        populateList(node.right, map, depth+1);
}

//My original soln
void listOfDepths(Node root) {
	Map<Integer, LinkedList<Node>> map = new HashMap<Integer, LinkedList<Node>>();
	int depth = 1;
	populateList(root, map, depth);
}

void populateList(Node node, Map<Integer, LinkedList<Node>> map, int depth) {
	if (node == null) return;
	if (!map.containsKey(depth)) {
		LinkedList<Node> list = new LinkedList<Node>();
		map.put(depth, list);
	}
	map.get(depth).add(node);
	populateList(node.left, map, depth+1);
	populateList(node.right, map, depth+1);
}
