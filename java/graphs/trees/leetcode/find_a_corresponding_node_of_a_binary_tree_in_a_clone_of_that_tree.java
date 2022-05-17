/*
1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree

Given two binary trees original and cloned and given a reference to a node target in the original tree.

The cloned tree is a copy of the original tree.

Return a reference to the same node in the cloned tree.

Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.

 

Example 1:


Input: tree = [7,4,3,null,null,6,19], target = 3
Output: 3
Explanation: In all examples the original and cloned trees are shown. The target node is a green node from the original tree. The answer is the yellow node from the cloned tree.
Example 2:


Input: tree = [7], target =  7
Output: 7
Example 3:


Input: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
Output: 4
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
The values of the nodes of the tree are unique.
target node is a node from the original tree and is not null.
 

Follow up: Could you solve the problem if repeated values on the tree are allowed?
*/

//Using DFS
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == target) return cloned;
        if (original.left != null) {
            TreeNode node = getTargetCopy(original.left, cloned.left, target);
            if (node != null) return node;
        }
        if (original.right != null) {
            TreeNode node = getTargetCopy(original.right, cloned.right, target);
            if (node != null) return node;
        }
        return null;
    }
}

//Using BFS
class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        Queue<TreeNode> q = new ArrayDeque();
        Queue<TreeNode> cq = new ArrayDeque();
        q.offer(original);
        cq.offer(cloned);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            TreeNode clone = cq.poll();
            if (node == target) return clone;
            if (node.left != null) {
                q.offer(node.left);
                cq.offer(clone.left);
            }
            if (node.right != null) {
                q.offer(node.right);
                cq.offer(clone.right);
            }
        }
        return null;
    }
}
