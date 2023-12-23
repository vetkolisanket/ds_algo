/*
1496. Path Crossing

Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.

Return true if the path crosses itself at any point, that is, if at any time you are on a location you have previously visited. Return false otherwise.

 

Example 1:


Input: path = "NES"
Output: false 
Explanation: Notice that the path doesn't cross any point more than once.
Example 2:


Input: path = "NESWW"
Output: true
Explanation: Notice that the path visits the origin twice.
 

Constraints:

1 <= path.length <= 10^4
path[i] is either 'N', 'S', 'E', or 'W'.
*/

//Soln using set TC O(N) SC O(N)
class Solution {
    public boolean isPathCrossing(String path) {
        Set<Integer> set = new HashSet();
        set.add(0);
        int val = 0;
        for (char c: path.toCharArray()) {
            if (c == 'N') {
                val += 100000;
            } else if (c == 'S') {
                val -= 100000;
            } else if (c == 'E') {
                val += 1;
            } else {
                val -= 1;
            }
            if (!set.add(val)) return true;
        }
        return false;
    }
}
