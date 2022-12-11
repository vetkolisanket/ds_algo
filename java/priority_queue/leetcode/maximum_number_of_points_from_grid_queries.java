/*
2503. Maximum Number of Points From Grid Queries

You are given an m x n integer matrix grid and an array queries of size k.

Find an array answer of size k such that for each integer queres[i] you start in the top left cell of the matrix and repeat the following process:

If queries[i] is strictly greater than the value of the current cell that you are in, then you get one point if it is your first time visiting this cell, and you can move to any adjacent cell in all 4 directions: up, down, left, and right.
Otherwise, you do not get any points, and you end this process.
After the process, answer[i] is the maximum number of points you can get. Note that for each query you are allowed to visit the same cell multiple times.

Return the resulting array answer.

 

Example 1:


Input: grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
Output: [5,8,1]
Explanation: The diagrams above show which cells we visit to get points for each query.
Example 2:


Input: grid = [[5,2,1],[1,1,2]], queries = [3]
Output: [0]
Explanation: We can not get any points because the value of the top left cell is already greater than or equal to 3.
 

Constraints:

m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 10^5
k == queries.length
1 <= k <= 10^4
1 <= grid[i][j], queries[i] <= 10^6
*/

//Soln using priority queue and bfs 
class Solution {

	public int[] maxPoints(int[][] grid, int[] queries) {
		int[] index = IntStream.range(0, queries.length).boxed().sorted((o, p) -> queries[o] - queries[p])
				.mapToInt(v -> v).toArray(), result = new int[queries.length];
		PriorityQueue<int[]> q = new PriorityQueue<>((o, p) -> grid[o[0]][o[1]] - grid[p[0]][p[1]]);
		for (int i = 0, count = 0, visited[][] = new int[grid.length][grid[0].length]; i < queries.length; i++) {
			for (q.offer(new int[2]); !q.isEmpty() && grid[q.peek()[0]][q.peek()[1]] < queries[index[i]];) {
				int[] poll = q.poll();
				if (visited[poll[0]][poll[1]] == 0) {
					count += visited[poll[0]][poll[1]] = 1;
					if (poll[0] > 0) {
						q.offer(new int[] { poll[0] - 1, poll[1] });
					}
					if (poll[1] > 0) {
						q.offer(new int[] { poll[0], poll[1] - 1 });
					}
					if (poll[1] < grid[0].length - 1) {
						q.offer(new int[] { poll[0], poll[1] + 1 });
					}
					if (poll[0] < grid.length - 1) {
						q.offer(new int[] { poll[0] + 1, poll[1] });
					}
				}
			}
			result[index[i]] = count;
		}
		return result;
	}
}
