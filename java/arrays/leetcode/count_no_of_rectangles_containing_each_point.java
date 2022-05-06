/*
2250. Count Number of Rectangles Containing Each Point

You are given a 2D integer array rectangles where rectangles[i] = [li, hi] indicates that ith rectangle has a length of li and a height of hi. You are also given a 2D integer array points where points[j] = [xj, yj] is a point with coordinates (xj, yj).

The ith rectangle has its bottom-left corner point at the coordinates (0, 0) and its top-right corner point at (li, hi).

Return an integer array count of length points.length where count[j] is the number of rectangles that contain the jth point.

The ith rectangle contains the jth point if 0 <= xj <= li and 0 <= yj <= hi. Note that points that lie on the edges of a rectangle are also considered to be contained by that rectangle.

 

Example 1:


Input: rectangles = [[1,2],[2,3],[2,5]], points = [[2,1],[1,4]]
Output: [2,1]
Explanation: 
The first rectangle contains no points.
The second rectangle contains only the point (2, 1).
The third rectangle contains the points (2, 1) and (1, 4).
The number of rectangles that contain the point (2, 1) is 2.
The number of rectangles that contain the point (1, 4) is 1.
Therefore, we return [2, 1].
Example 2:


Input: rectangles = [[1,1],[2,2],[3,3]], points = [[1,3],[1,1]]
Output: [1,3]
Explanation:
The first rectangle contains only the point (1, 1).
The second rectangle contains only the point (1, 1).
The third rectangle contains the points (1, 3) and (1, 1).
The number of rectangles that contain the point (1, 3) is 1.
The number of rectangles that contain the point (1, 1) is 3.
Therefore, we return [1, 3].
 

Constraints:

1 <= rectangles.length, points.length <= 5 * 10^4
rectangles[i].length == points[j].length == 2
1 <= li, xj <= 10^9
1 <= hi, yj <= 100
All the rectangles are unique.
All the points are unique.
*/

class Solution {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap();
        for (int[] rect: rectangles) {
            int l = rect[0];
            int h = rect[1];
            if (!map.containsKey(h)) {
                map.put(h, new ArrayList());
            }
            map.get(h).add(l);
        }
        for (int key: map.keySet()) {
            Collections.sort(map.get(key));
        }
        int[] res = new int[points.length];
        for (int i=0;i<points.length;i++) {
            int x = points[i][0];
            int y = points[i][1];
            int cnt = 0;
            for (int j=y;j<=100;j++) {
                if (map.containsKey(j)) {
                    cnt += map.get(j).size() - getIndex(map.get(j), x);
                }
            }
            res[i] = cnt;
        }
        return res;
    }
    
    private int getIndex(List<Integer> list, int x) {
        int index = list.size();
        int start = 0, end = list.size()-1;
        
        while (start <= end) {
            int mid = start + (end-start)/2;
            if (list.get(mid) >= x) {
                index = mid;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return index;
    }
}
