/*
43. Multiply Strings

Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

 

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
 

Constraints:

1 <= num1.length, num2.length <= 200
num1 and num2 consist of digits only.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
*/

class Solution {
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        
        int[] ans = new int[len1 + len2];
        
        for (int i = len1-1;i >= 0; i--) {
            for (int j = len2-1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = p1 + 1;
                mul = mul + ans[p2];
                
                ans[p1] += mul/10;
                ans[p2] = mul%10;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int a : ans) {
            if (sb.length() != 0 || a != 0) sb.append(a);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
