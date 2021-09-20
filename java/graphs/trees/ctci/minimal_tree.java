/*
Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an algo- rithm to create a binary search tree with minimal height.
*/
Node minimalTree(int arr[]) {
	return createTree(arr, 0, arr.length-1);
}

Node createTree(int arr[], int start, int end) {
	if (end < start) return null;
	int mid = (start + end)/2;
	Node node = new Node(arr[mid]);
	node.left = createTree(arr, start, mid-1);
	node.right = createTree(arr, mid+1, end);
	return node;
}
