/*
1161. Maximum Level Sum of a Binary Tree

Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level x such that the sum of all the values of nodes at level x is maximal.

 

Example 1:


Input: root = [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
Example 2:

Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
Output: 2
 

Constraints:

The number of nodes in the tree is in the range [1, 10^4].
-10^5 <= Node.val <= 10^5

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
    public int maxLevelSum(TreeNode root) {
        int curLevel = 1, ans = 0, maxSum = Integer.MIN_VALUE;
        Queue<TreeNode> q = new ArrayDeque();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            int sum = 0;
            while (size-- > 0) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            if (sum > maxSum) {
                maxSum = sum;
                ans = curLevel;
            }
            curLevel++;
        }
        return ans;
    }
}

//DFS soln TC O(N) SC O(N)
class Solution {
    public int maxLevelSum(TreeNode root) {
        List<Integer> list = new ArrayList();
        dfs(root, list, 0);
        int ans = 0, maxSum = Integer.MIN_VALUE;
        for (int i=0;i<list.size();i++) {
            if (maxSum < list.get(i)) {
                maxSum = list.get(i);
                ans = i+1;
            }
        }
        return ans;
    }

    private void dfs(TreeNode node, List<Integer> list, int level) {
        if (level == list.size()) {
            list.add(node.val);
        } else {
            list.set(level, list.get(level) + node.val);
        }
        if (node.left != null) dfs(node.left, list, level+1);
        if (node.right != null) dfs(node.right, list, level+1);
    }
}
