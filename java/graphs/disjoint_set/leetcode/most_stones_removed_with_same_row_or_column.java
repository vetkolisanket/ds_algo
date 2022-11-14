/*
947. Most Stones Removed with Same Row or Column

On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.

A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.

Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.

 

Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Explanation: One way to make 3 moves is as follows:
1. Remove stone [2,2] because it shares the same row as [2,0].
2. Remove stone [2,0] because it shares the same column as [0,0].
3. Remove stone [0,2] because it shares the same row as [0,0].
Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
Example 3:

Input: stones = [[0,0]]
Output: 0
Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
 

Constraints:

1 <= stones.length <= 1000
0 <= xi, yi <= 10^4
No two stones are at the same coordinate point.
*/

//Using disjoint set TC O(N^2*alpha(N)) SC O(N) where alpha() is the inverse ackerman function
class UnionFind {
    
    private int parent[], size[];
    
    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i=0;i<n;i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    
    public int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    
    public int union(int x, int y) {
        x = find(x);
        y = find(y);
        
        if (x == y) return 0;
        
        if (size[x] > size[y]) {
            parent[y] = x;
            size[x] += size[y];
        } else {
            parent[x] = y;
            size[y] += size[x];
        }
        return 1;
    }
    
}

class Solution {
    public int removeStones(int[][] stones) {
        UnionFind uf = new UnionFind(stones.length);
        int components = stones.length;
        for (int i=0;i<stones.length;i++) {
            for (int j=i+1;j<stones.length;j++) {
                if (belongToSameRowOrCol(stones[i], stones[j])) {
                    components -= uf.union(i, j);
                }
            }
        }
        return stones.length - components;
    }
    
    private boolean belongToSameRowOrCol(int[] x, int[] y) {
        return x[0] == y[0] || x[1] == y[1];
    }
}
