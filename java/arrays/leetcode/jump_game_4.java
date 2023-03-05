/*
1345. Jump Game IV

Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
Example 2:

Input: arr = [7]
Output: 0
Explanation: Start index is the last index. You do not need to jump.
Example 3:

Input: arr = [7,6,9,6,9,6,9,7]
Output: 1
Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 

Constraints:

1 <= arr.length <= 5 * 10^4
-10^8 <= arr[i] <= 10^8

*/

//Soln using BFS TC O(N) SC O(N)
class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0;
        Map<Integer, List<Integer>> graph = new HashMap();
        for (int i=0;i<n;i++) {
            graph.computeIfAbsent(arr[i], v -> new LinkedList()).add(i);
        }
        Set<Integer> visited = new HashSet();
        Deque<Integer> queue = new ArrayDeque();
        queue.offer(0);
        visited.add(0);
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int node = queue.poll();
                if (node == n-1) return steps;
                for (int child: graph.get(arr[node])) {
                    if (!visited.contains(child)) {
                        visited.add(child);
                        queue.offer(child);
                    }
                }
                graph.get(arr[node]).clear();
                if (node+1 < n && !visited.contains(node+1)) {
                    visited.add(node+1);
                    queue.offer(node+1);
                }
                if (node-1 >= 0 && !visited.contains(node-1)) {
                    visited.add(node-1);
                    queue.offer(node-1);
                }
            }
            steps++;
        }
        return -1;
    }
}

//Soln using bidirectional BFS TC O(N) SC O(N)
class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;
        Map<Integer, List<Integer>> graph = new HashMap();
        for (int i=0;i<arr.length;i++) {
            graph.computeIfAbsent(arr[i], v -> new LinkedList()).add(i);
        }
        Set<Integer> cur = new HashSet(), other = new HashSet(), visited = new HashSet();
        cur.add(0);
        other.add(n-1);
        visited.add(0);
        visited.add(n-1);
        int steps = 0;
        while (!cur.isEmpty()) {
            if (cur.size() > other.size()) {
                Set<Integer> temp = cur;
                cur = other;
                other = temp;
            }
            Set<Integer> nex = new HashSet();
            for (int node: cur) {
                for (int child: graph.get(arr[node])) {
                    if (other.contains(child)) return steps+1;
                    if (!visited.contains(child)) {
                        visited.add(child);
                        nex.add(child);
                    }
                }
                graph.get(arr[node]).clear();
                if (other.contains(node+1) || other.contains(node-1)) {
                    return steps+1;
                }
                if (node+1 < n && !visited.contains(node+1)) {
                    visited.add(node+1);
                    nex.add(node+1);
                }
                if (node-1 >= 0 && !visited.contains(node-1)) {
                    visited.add(node-1);
                    nex.add(node-1);
                }
            }
            cur = nex;
            steps++;
        }
        return -1;
    }
}
