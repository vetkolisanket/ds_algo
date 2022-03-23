/*
Multiply two strings

Given two numbers as strings s1 and s2. Calculate their Product.
Note: The numbers can be negative.


Example 1:

Input:
s1 = "33"
s2 = "2"
Output:
66
Example 2:

Input:
s1 = "11"
s2 = "23"
Output:
253

Your Task:

You don't need to read input or print anything. Your task is to complete the function multiplyStrings() which takes two strings s1 and s2 as input and returns their product as a string.

Note : You are not allowed to use any built-in function or convert the strings to integer.


Expected Time Complexity: O(n1* n2)
Expected Auxiliary Space: O(n1 + n2) ; where n1 and n2 are sizes of strings s1 and s2 respectively.


Constraints:
1 ≤ length of s1 and s2 ≤ 10^3
*/

class Solution
{
    public String multiplyStrings(String s1,String s2)
    {
        //code here.
        if ("0".equals(s1) || "0".equals(s2)) return "0";
        int n1 = s1.length();
        int n2 = s2.length();
        int[] arr = new int[n1+n2];
        for (int i=n1-1;i>=0;i--) {
            char c1 = s1.charAt(i);
            for (int j=n2-1;j>=0;j--) {
                char c2 = s2.charAt(j);
                if (c1 != '-' && c2 != '-') {
                    arr[i+j+1] += (c1-'0')*(c2-'0');
                    arr[i+j] += arr[i+j+1]/10;
                    arr[i+j+1] %= 10;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if ((s1.charAt(0) == '-' && s2.charAt(0) != '-') || 
        (s1.charAt(0) != '-' && s2.charAt(0) == '-')) sb.append("-");
        int i=0;
        while (i < n1+n2 && arr[i] == 0) i++;
        while (i < n1+n2) sb.append(arr[i++]);
        return sb.toString();
    }
}
