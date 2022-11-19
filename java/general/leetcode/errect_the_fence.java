/*
587. Erect the Fence

You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.

You are asked to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed.

Return the coordinates of trees that are exactly located on the fence perimeter.

 

Example 1:


Input: points = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
Output: [[1,1],[2,0],[3,3],[2,4],[4,2]]
Example 2:


Input: points = [[1,2],[2,2],[4,2]]
Output: [[4,2],[2,2],[1,2]]
 

Constraints:

1 <= points.length <= 3000
points[i].length == 2
0 <= xi, yi <= 100
All the given points are unique.
*/

//Soln using Graham Scan TC O(nlog(n)) SC O(n)
class Solution {
    public int[][] outerTrees(int[][] points) {
        if (points.length <= 1) return points;
        int[] bm = bottomLeft(points);
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] p, int[] q) {
                double diff = orientation(bm, p, q) - orientation(bm, q, p);
                if(diff == 0) return distance(bm, p) - distance(bm, q);
                else return diff > 0 ? 1 : -1;
            }
        });
        int i = points.length - 1;
        while (i >= 0 && orientation(bm, points[points.length - 1], points[i]) == 0) {
            i--;
        }
        for (int l = i+1, h = points.length - 1; l < h; l++, h--) {
            int[] temp = points[l];
            points[l] = points[h];
            points[h] = temp;
        }
        Stack<int[]> stack = new Stack();
        stack.push(points[0]);
        stack.push(points[1]);
        for (int j=2;j<points.length;j++) {
            int[] top = stack.pop();
            while (orientation(stack.peek(), top, points[j]) > 0) {
                top = stack.pop();
            }
            stack.push(top);
            stack.push(points[j]);
        }
        return stack.toArray(new int[stack.size()][]);
    }
    
    private int orientation(int[] p, int[] q, int[] r) {
        return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
    }
    
    private int distance(int[] p, int[] q) {
        return (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
    }
    
    private int[] bottomLeft(int[][] points) {
        int[] bottomLeft = points[0];
        for (int[] p : points) {
            if (p[1] < bottomLeft[1]) {
                bottomLeft = p;
            }
        }
        return bottomLeft;
    }
}
