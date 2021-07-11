/*
118. Pascal's Triangle

Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

Input: numRows = 1
Output: [[1]]
*/

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> parentList = new ArrayList<List<Integer>>();
        for (int i=0;i<numRows;i++) {
            List<Integer> childList = new ArrayList<Integer>();
            for (int j=0;j<=i;j++) {
                if (i == 0 && j == 0) {
                    childList.add(1);
                } else {
                    List<Integer> preList = parentList.get(i-1);
                    int preListSize = preList.size();
                    int left = 0;
                    int right = 0;
                    if (j > 0) {
                        left = preList.get(j-1);
                    }
                    if (j < preListSize) {
                        right = preList.get(j);
                    }
                    childList.add(left+right);
                }
            }
            parentList.add(childList);
        }
        return parentList;
    }
}
