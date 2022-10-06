/*
Time Complexity
Union-find Constructor	Find	Union	Connected
Time Complexity	O(N)	O(N)	O(N)	O(N)

Note: N is the number of vertices in the graph. In the worst-case scenario, the number of operations to get the root vertex will be H where H is the height of the tree. Because this implementation does not always point the root of the shorter tree to the root of the taller tree, H can be at most N when the tree forms a linked list.

The same as in the quick find implementation, when initializing a union-find constructor, we need to create an array of size N with the values equal to the corresponding array indices; this requires linear time.
For the find operation, in the worst-case scenario, we need to traverse every vertex to find the root for the input vertex. The maximum number of operations to get the root vertex would be no more than the tree's height, so it will take O(N) time.
The union operation consists of two find operations which (only in the worst-case) will take O(N) time, and two constant time operations, including the equality check and updating the array value at a given index. Therefore, the union operation also costs O(N) in the worst-case.
The connected operation also takes O(N) time in the worst-case since it involves two find calls.
Space Complexity
We need O(N) space to store the array of size N.
*/

class UnionFind {

    int[] arr;

    public UnionFind(int n) {
        arr = new int[n];
        for (int i=0;i<n;i++) {
            arr[i] = i;
        }
    }

    public int find(int x){
        while (arr[x] != x) {
            x = arr[x];
        }
        return x;
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            arr[rootY] = rootX;
        }
    }

}
