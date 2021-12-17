/*
120. Triangle

Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

 

Example 1:

Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
Example 2:

Input: triangle = [[-10]]
Output: -10
 

Constraints:

1 <= triangle.length <= 200
triangle[0].length == 1
triangle[i].length == triangle[i - 1].length + 1
-104 <= triangle[i][j] <= 104
 

Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
*/

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[] arr = new int[size];
        arr[0] = triangle.get(0).get(0);
        for (int i=1;i<size;i++) {
            List<Integer> list = triangle.get(i);
            int lSize = list.size();
            for (int j=lSize-1;j>=0;j--) {
                if (j == 0) arr[j] += list.get(j);
                else if (j == lSize-1) arr[j] = arr[j-1] + list.get(j);
                else arr[j] = Math.min(arr[j], arr[j-1]) + list.get(j);
            }
        }
        int min = arr[0];
        for (int i=1;i<size;i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }
}
