/*
6. Zigzag Conversion

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
 

Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
Example 3:

Input: s = "A", numRows = 1
Output: "A"
 

Constraints:

1 <= s.length <= 1000
s consists of English letters (lower-case and upper-case), ',' and '.'.
1 <= numRows <= 1000
*/

//Soln using character matrix TC O(n*numRows) SC O(n*numRows)
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        int n = s.length();
        char[][] arr = new char[numRows][n];
        for (char[] row: arr) {
            Arrays.fill(row, '*');
        }
        for (int i=0, row = 0, col = 0;i<n;) {
            while (i < n && row < numRows) {
                arr[row++][col] = s.charAt(i++);
            }
            row = numRows-2;
            col++;
            while (i < n && row > 0) {
                arr[row--][col++] = s.charAt(i++);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<numRows;i++) {
            for (int j=0;j<n;j++) {
                if (arr[i][j] != '*') sb.append(arr[i][j]);
            }
        }
        return sb.toString();
    }
}
