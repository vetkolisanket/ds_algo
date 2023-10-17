/*
1361. Validate Binary Tree Nodes

You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.

If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

Note that the nodes have no values and that we only use the node numbers in this problem.

 

Example 1:


Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
Output: true
Example 2:


Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
Output: false
Example 3:


Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
Output: false
 

Constraints:

n == leftChild.length == rightChild.length
1 <= n <= 10^4
-1 <= leftChild[i], rightChild[i] <= n - 1
*/

//My soln TC O(N) SC O(N)
class Solution {

    private final int TO_VISIT = 0, VISITING = 1, VISITED = 2;

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int status[] = new int[n], indegree[] = new int[n];
        Queue<Integer> q = new ArrayDeque();
        int startNode = -1;
        for (int i=0;i<n;i++) {
            if (leftChild[i] != -1) {
                indegree[leftChild[i]]++;
            }
            if (rightChild[i] != -1) {
                indegree[rightChild[i]]++;
            }
        }
        for (int i=0;i<n;i++) {
            if (indegree[i] == 0) {
                if (startNode != -1) return false;
                startNode = i;
            }
        }
        if (startNode == -1) return false;
        q.offer(startNode);
        status[startNode] = VISITING;
        while (!q.isEmpty()) {
            int node = q.poll();
            status[node] = VISITED;
            if (leftChild[node] != -1) {
                if (status[leftChild[node]] != TO_VISIT) return false;
                q.offer(leftChild[node]);
                status[leftChild[node]] = VISITING;
            }
            if (rightChild[node] != -1) {
                if (status[rightChild[node]] != TO_VISIT) return false;
                q.offer(rightChild[node]);
                status[rightChild[node]] = VISITING;
            }
        }
        for (int val: status) {
            if (val != VISITED) return false;
        }
        return true;
    }
}
