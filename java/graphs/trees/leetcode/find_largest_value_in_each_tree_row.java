/*
515. Find Largest Value in Each Tree Row

Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).

 

Example 1:


Input: root = [1,3,2,5,3,null,9]
Output: [1,3,9]
Example 2:

Input: root = [1,2,3]
Output: [1,3]
 

Constraints:

The number of nodes in the tree will be in the range [0, 104].
-2^31 <= Node.val <= 2^31 - 1
*/

//Soln using BFS TC O(N) SC O(N)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList();
        if (root == null) return list;
        Queue<TreeNode> q = new ArrayDeque();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            int max = Integer.MIN_VALUE;
            while (size-- > 0) {
                TreeNode node = q.poll();
                max = Math.max(max, node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            list.add(max);
        }
        return list;
    }
}

//Soln using DFS TC O(N) SC O(H) where N is the no. of nodes and H is the max height of the tree
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList();
        dfs(root, 0, list);
        return list;
    }

    private void dfs(TreeNode node, int height, List<Integer> list) {
        if (node == null) return;

        if (height == list.size()) {
            list.add(node.val);
        } else {
            list.set(height, Math.max(list.get(height), node.val));
        }

        dfs(node.left, height+1, list);
        dfs(node.right, height+1, list);
    }
}

//Iterative DFS soln TC O(N) SC O(H) where N is the no. of nodes and H is the height of the tree
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList();
        if (root == null) return list;

        Stack<Pair<TreeNode, Integer>> stack = new Stack();
        stack.push(new Pair(root, 0));
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> p = stack.pop();
            TreeNode node = p.getKey();
            int height = p.getValue();
            if (list.size() == height) {
                list.add(node.val);
            } else {
                list.set(height, Math.max(list.get(height), node.val));
            }

            if (node.left != null) stack.push(new Pair(node.left, height+1));
            if (node.right != null) stack.push(new Pair(node.right, height+1));
        }
        return list;
    }
}
