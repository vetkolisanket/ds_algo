/*
Paths with Sum: You are given a binary tree in which each node contains an integer value (which might be positive or negative). Design an algorithm to count the number of paths that sum to a given value. The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
*/
int countPathsWithSum(TreeNode root, int targetSum) {
	return countPathsWithSum(root, targetSum, 0, new HashMap<Integer, Integer>());
}

int countPathsWithSum(TreeNode node, int targetSum, int runningSum, Map<Integer, Integer> map) {
	if (node == null) return 0;

	runningSum += node.data;
	int sum = runningSum - targetSum;
	int totalPaths = map.getOrDefault(sum, 0);

	if (runningSum == targetSum) totalPaths++;

	incrementHashTable(map, runningSum, 1);
	totalPaths += countPathsWithSum(node.left, targetSum, runningSum, map);
	totalPaths += countPathsWithSum(node.right, targetSum, runningSum, map);
	incrementHashTable(map, runningSum, -1);

	return totalPaths;
}

void incrementHashTable(Map<Integer, Integer> map, int key, int val) {
	int newVal = map.getOrDefault(key, 0) + val;
	if (newVal == 0) mar.remove(key);
	else map.put(key, newVal);
}
