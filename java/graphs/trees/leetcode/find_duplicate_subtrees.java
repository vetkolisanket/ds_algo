/*
652. Find Duplicate Subtrees

Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.

 

Example 1:


Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]
Example 2:


Input: root = [2,1,1]
Output: [[1]]
Example 3:


Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]
 

Constraints:

The number of the nodes in the tree will be in the range [1, 5000]
-200 <= Node.val <= 200
*/

//My soln TC O(N^2) SC O(N^2)
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
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, TreeNode> nodeMap = new HashMap();
        Set<String> nodeStrSet = new HashSet();
        findDups(root, nodeMap, nodeStrSet);
        return new ArrayList(nodeMap.values());
    }

    private String findDups(TreeNode root, Map<String, TreeNode> map, Set<String> set) {
        if (root == null) return "N";
        String left = findDups(root.left, map, set);
        String right = findDups(root.right, map, set);
        String s = new StringBuilder().append(root.val).append("L").append(left).append("R").append(right).toString();
        if (!set.add(s)) {
            map.put(s, root);
        }
        return s;
    }
}

//Soln with node's string representation (key) is optimized TC O(N) SC O(N)
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList();
        traverse(root, new HashMap(), new HashMap(), res);
        return res;
    }

    private int traverse(TreeNode node, Map<String, Integer> map, Map<Integer, Integer> cnt, List<TreeNode> list) {
        if (node == null) return 0;
        String key = traverse(node.left, map, cnt, list) + "," + node.val + "," + traverse(node.right, map, cnt, list);
        if (!map.containsKey(key)) {
            map.put(key, map.size()+1);
        }
        int id = map.get(key);
        cnt.put(id, cnt.getOrDefault(id, 0) + 1);
        if (cnt.get(id) == 2) {
            list.add(node);
        }
        return id;
    }
    
}
