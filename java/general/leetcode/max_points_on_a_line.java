/*
149. Max Points on a Line

Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.

 

Example 1:


Input: points = [[1,1],[2,2],[3,3]]
Output: 3
Example 2:


Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
 

Constraints:

1 <= points.length <= 300
points[i].length == 2
-10^4 <= xi, yi <= 10^4
All the points are unique.
*/

//Soln from another attempt TC O(n^2) SC O(n^2)
class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n < 3) return n;
        int ans = 0;
        for (int i=0;i<n;i++) {
            Map<Double, Integer> map = new HashMap();
            for (int j=0;j<n;j++) {
                if (i == j) continue;
                map.merge(Math.atan2(points[j][1] - points[i][1], points[j][0] - points[i][0]), 1, Integer::sum);
            }
            ans = Math.max(ans, Collections.max(map.values())+1);
        }
        return ans;
    }
}

//Faster soln
class Solution {
    public int maxPoints(int[][] points) {
        int result = 0;
        Map<String, Integer> map = new HashMap();
        for (int i=0;i<points.length;i++) {
            map.clear();
            int duplicates=0,max=0;
            for (int j=i+1;j<points.length;j++) {
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                
                if (x == 0 && y == 0) duplicates++;
                else {
                    int gcd = computeGCD(x, y);
                    if (gcd != 0) {
                        x /= gcd;
                        y /= gcd;
                    }
                    String key = new StringBuilder().append(x).append(y).toString();
                    map.put(key, map.getOrDefault(key, 0)+1);
                    max = Math.max(max, map.get(key));
                }
            }
            result = Math.max(result, max+duplicates+1);
        }
        return result;
    }
    
    private int computeGCD(int a, int b) {
        if (b==0) return a;
        return computeGCD(b, a%b);
    }
}

class Solution {
    public int maxPoints(int[][] points) {
        int result = 0;
        Map<Integer, Map<Integer, Integer>> map = new HashMap();
        for (int i=0;i<points.length;i++) {
            map.clear();
            int duplicates=0,max=0;
            for (int j=i+1;j<points.length;j++) {
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                
                if (x == 0 && y == 0) duplicates++;
                else {
                    int gcd = computeGCD(x, y);
                    if (gcd != 0) {
                        x /= gcd;
                        y /= gcd;
                    }
                    if (map.containsKey(x)) {
                        if (map.get(x).containsKey(y)) {
                            map.get(x).put(y, map.get(x).get(y)+1);
                        } else map.get(x).put(y, 1);
                    } else {
                        Map<Integer, Integer> m = new HashMap();
                        m.put(y, 1);
                        map.put(x, m);
                    }
                }
                max = Math.max(max, map.get(x).get(y));
            }
            result = Math.max(result, max+duplicates+1);
        }
        return result;
    }
    
    private int computeGCD(int a, int b) {
        if (b==0) return a;
        return computeGCD(b, a%b);
    }
}
