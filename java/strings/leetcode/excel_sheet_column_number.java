/*
171. Excel Sheet Column Number

Given a string columnTitle that represents the column title as appear in an Excel sheet, return its corresponding column number.

For example:

A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 
...
 

Example 1:

Input: columnTitle = "A"
Output: 1
Example 2:

Input: columnTitle = "AB"
Output: 28
Example 3:

Input: columnTitle = "ZY"
Output: 701
 

Constraints:

1 <= columnTitle.length <= 7
columnTitle consists only of uppercase English letters.
columnTitle is in the range ["A", "FXSHRXW"].
*/

//Without using Math.pow()
class Solution {
    public int titleToNumber(String columnTitle) {
        int num=0;
        int mul=1;
        for (int i=columnTitle.length()-1;i>=0;i--) {
            num += (columnTitle.charAt(i)-'A'+1)*mul;
            mul*=26;
        }
        return num;
    }
}

//My soln
class Solution {
    public int titleToNumber(String columnTitle) {
        int num=0;
        int len = columnTitle.length();
        for (int i=len-1;i>=0;i--) {
            int val = columnTitle.charAt(i) - 'A' + 1;
            num += val*Math.pow(26, len-1-i);
        }
        return num;
    }
}
