/*
113. Path Sum II

Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

 

Example 1:


Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whose sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22
Example 2:


Input: root = [1,2,3], targetSum = 5
Output: []
Example 3:

Input: root = [1,2], targetSum = 0
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000
*/

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

//TC as per me should be O(nlog(n)) but as per soln is O(n^2) SC O(n)
/*
Time Complexity: O(N^2) where N are the number of nodes in a tree. In the worst case, we could have a complete binary tree and if that is the case, 
then there would be N/2 leafs. For every leaf, we perform a potential O(N) operation of copying over the pathNodes nodes to a new list to be added 
to the final pathsList. Hence, the complexity in the worst case could be O(N^2).

Space Complexity: O(N). The space complexity, like many other problems is debatable here. I personally choose not to consider the space occupied by 
the output in the space complexity. So, all the new lists that we create for the paths are actually a part of the output and hence, don't count towards 
the final space complexity. The only additional space that we use is the pathNodes list to keep track of nodes along a branch.
*/
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> list = new ArrayList();
        getList(root, list, new ArrayList(), targetSum);
        return list;
    }
    
    private void getList(TreeNode node, List<List<Integer>> list, List<Integer> tempList, int targetSum) {
        if (node == null) return;
        tempList.add(node.val);
        if (node.left == null && node.right == null) {
            if (targetSum == node.val) {
                list.add(new ArrayList(tempList));
            }
        } else {
            getList(node.left, list, tempList, targetSum - node.val);
            getList(node.right, list, tempList, targetSum - node.val);
        }
        tempList.remove(tempList.size()-1);
    }
}
