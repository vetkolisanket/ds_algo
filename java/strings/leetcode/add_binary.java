/*
67. Add Binary

Given two binary strings a and b, return their sum as a binary string.

 

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
 

Constraints:

1 <= a.length, b.length <= 104
a and b consist only of '0' or '1' characters.
Each string does not contain leading zeros except for the zero itself.
*/

//A faster soln
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length()-1, j = b.length()-1, carry=0;
        while (i >=0 || j >= 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i) - '0';
            if (j >= 0) sum += b.charAt(j) - '0';
            sb.append(sum%2);
            carry = sum/2;
            i--; j--;
        }
        if (carry > 0) sb.append(1);
        return sb.reverse().toString();
    }
}

//My soln
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int m = a.length();
        int n = b.length();
        if (m < n) {
            m = m+n;
            n = m-n;
            m = m-n;
            String temp = a;
            a = b;
            b = temp;
        }
        int c = 0;
        int i = m-1;
        for (int j = n-1; j >= 0; j--, i--) {
            int x = a.charAt(i) - '0';
            int y = b.charAt(j) - '0';
            int sum = x+y+c;
            sb.insert(0, sum%2);
            c = sum/2;
        }
        while (i >= 0) {
            int x = a.charAt(i) - '0';
            int sum = x + c;
            sb.insert(0, sum%2);
            c = sum/2;
            i--;
        }
        if (c != 0) sb.insert(0, c);
        return sb.toString();
    }
}

//My older soln
class Solution {
    public String addBinary(String a, String b) {
        int carry = 0;
        int i = a.length()-1;
        int j = b.length()-1;
        StringBuilder sb = new StringBuilder();
        while(i >= 0 && j >= 0){
            if (a.charAt(i) == '0' && b.charAt(j) == '0' && carry == 0) {
                // sb = new StringBuilder("0").append(sb);
                sb.append("0");
                carry = 0;
            } else if (a.charAt(i) == '0' && b.charAt(j) == '0' && carry == 1) {
                // sb = new StringBuilder("1").append(sb);
                sb.append("1");
                carry = 0;
            } else if (a.charAt(i) == '0' && b.charAt(j) == '1' && carry == 0) {
                // sb = new StringBuilder("1").append(sb);
                sb.append("1");
                carry = 0;
            } else if (a.charAt(i) == '0' && b.charAt(j) == '1' && carry == 1) {
                // sb = new StringBuilder("0").append(sb);
                sb.append("0");
                carry = 1;
            } else if (a.charAt(i) == '1' && b.charAt(j) == '0' && carry == 0) {
                // sb = new StringBuilder("1").append(sb);
                sb.append("1");
                carry = 0;
            } else if (a.charAt(i) == '1' && b.charAt(j) == '0' && carry == 1) {
                // sb = new StringBuilder("0").append(sb);
                sb.append("0");
                carry = 1;
            } else if (a.charAt(i) == '1' && b.charAt(j) == '1' && carry == 0) {
                // sb = new StringBuilder("0").append(sb);
                sb.append("0");
                carry = 1;
            } else if (a.charAt(i) == '1' && b.charAt(j) == '1' && carry == 1) {
                // sb = new StringBuilder("1").append(sb);
                sb.append("1");
                carry = 1;
            }
            i--;
            j--;
        }
        while (i >= 0) {
            if (a.charAt(i) == '0' && carry == 0) {
                // sb = new StringBuilder("0").append(sb);
                sb.append("0");
                carry = 0;
            } else if (a.charAt(i) == '0' && carry == 1) {
                // sb = new StringBuilder("1").append(sb);
                sb.append("1");
                carry = 0;
            } else if (a.charAt(i) == '1' && carry == 0) {
                // sb = new StringBuilder("1").append(sb);
                sb.append("1");
                carry = 0;
            } else if (a.charAt(i) == '1' && carry == 1) {
                // sb = new StringBuilder("0").append(sb);
                sb.append("0");
                carry = 1;
            }
            i--;
        }
        while (j >= 0) {
            if (b.charAt(j) == '0' && carry == 0) {
                // sb = new StringBuilder("0").append(sb);
                sb.append("0");
                carry = 0;
            } else if (b.charAt(j) == '0' && carry == 1) {
                // sb = new StringBuilder("1").append(sb);
                sb.append("1");
                carry = 0;
            } else if (b.charAt(j) == '1' && carry == 0) {
                // sb = new StringBuilder("1").append(sb);
                sb.append("1");
                carry = 0;
            } else if (b.charAt(j) == '1' && carry == 1) {
                // sb = new StringBuilder("0").append(sb);
                sb.append("0");
                carry = 1;
            }
            j--;
        }
        if (carry == 1) {
            // sb = new StringBuilder("1").append(sb);
                sb.append("1");
        }
        return sb.reverse().toString();
    }
}
