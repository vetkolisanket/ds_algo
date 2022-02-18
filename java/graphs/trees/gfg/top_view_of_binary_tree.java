/*
Top View of Binary Tree

Given below is a binary tree. The task is to print the top view of binary tree. Top view of a binary tree is the set of nodes visible when the tree is viewed from the top. For the given below tree

       1
    /     \
   2       3
  /  \    /   \
4    5  6   7

Top view will be: 4 2 1 3 7
Note: Return nodes from leftmost node to rightmost node.

Example 1:

Input:
      1
   /    \
  2      3
Output: 2 1 3
Example 2:

Input:
       10
    /      \
  20        30
 /   \    /    \
40   60  90    100
Output: 40 20 10 30 100
Your Task:
Since this is a function problem. You don't have to take input. Just complete the function topView() that takes root node as parameter and returns a list of nodes visible from the top view from left to right.

Expected Time Complexity: O(N)
Expected Auxiliary Space: O(N).

Constraints:
1 ≤ N ≤ 10^5
1 ≤ Node Data ≤ 10^5
*/

class Solution
{
    //Function to return a list of nodes visible from the top view 
    //from left to right in Binary Tree.
    
    static ArrayList<Integer> topView(Node root)
    {
        // add your code
        ArrayList<Integer> list = new ArrayList();
        Map<Node, Integer> nodeMap = new HashMap();
        Map<Integer, Node> intMap = new TreeMap();
        Queue<Node> queue = new LinkedList();
        nodeMap.put(root, 0);
        intMap.put(0, root);
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int val = nodeMap.get(node);
            if (node.left != null) {
                queue.offer(node.left);
                nodeMap.put(node.left, val-1);
                if (!intMap.containsKey(val-1)) {
                    intMap.put(val-1, node.left);
                }
            }
            if (node.right != null) {
                queue.offer(node.right);
                nodeMap.put(node.right, val+1);
                if (!intMap.containsKey(val+1)) {
                    intMap.put(val+1, node.right);
                }
            }
        }
        for (Map.Entry<Integer, Node> entry : intMap.entrySet()) {
            list.add(entry.getValue().data);
        }
        return list;
    }
}
