/*
1584. Min Cost to Connect All Points

You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

 

Example 1:


Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation: 

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.
Example 2:

Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18
 

Constraints:

1 <= points.length <= 1000
-10^6 <= xi, yi <= 10^6
All pairs (xi, yi) are distinct.

*/

//Soln using union find TC O(N^2logN) SC O(N^2)
class UnionFind {

    private int n, parent[], rank[];

    public UnionFind(int n) {
        this.n = n;
        parent = new int[n];
        rank = new int[n];
        for (int i=0;i<n;i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;
        if (rank[x] > rank[y]) {
            parent[y] = x;
            rank[x]++;
        } else {
            parent[x] = y;
            rank[y]++;
        }
        return true;
    }

}

class Solution {
    public int minCostConnectPoints(int[][] points) {
        List<int[]> list = new ArrayList();
        int n = points.length;
        for (int i=0;i<n;i++) {
            for (int j=i+1;j<n;j++) {
                int weight = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                list.add(new int[]{weight, i, j});
            }
        }
        Collections.sort(list, (a, b) -> a[0] - b[0]);
        UnionFind uf = new UnionFind(n);
        int ans = 0, edges = 0;
        for (int i=0;i<list.size() && edges < n-1;i++) {
            int[] arr = list.get(i);
            if (uf.union(arr[1], arr[2])) {
                ans += arr[0];
                edges++;
            }
        }
        return ans;
    }
}
