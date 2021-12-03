/*
93. Restore IP Addresses

A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.

 

Example 1:

Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]
Example 2:

Input: s = "0000"
Output: ["0.0.0.0"]
Example 3:

Input: s = "1111"
Output: ["1.1.1.1"]
Example 4:

Input: s = "010010"
Output: ["0.10.0.10","0.100.1.0"]
Example 5:

Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 

Constraints:

0 <= s.length <= 20
s consists of digits only.
*/


//A faster soln
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        
        for (int a=1;a<4;a++)
            for(int b=1;b<4;b++)
                for(int c=1;c<4;c++)
                    if (a+b+c > len-4 && a+b+c < len) {
                        int A = Integer.valueOf(s.substring(0, a));
                        int B = Integer.valueOf(s.substring(a, a+b));
                        int C = Integer.valueOf(s.substring(a+b, a+b+c));
                        int D = Integer.valueOf(s.substring(a+b+c));
                            
                        if (A <= 255 && B <= 255 && C <= 255 && D <= 255) {
                            sb.append(A)
                            .append(".")
                            .append(B)
                            .append(".")
                            .append(C)
                            .append(".")
                            .append(D);
                            
                            if (sb.length() == len+3)
                                res.add(sb.toString());
                            
                            sb.delete(0, sb.length());
                        }
                    }
        
        return res;
    }
    
}

//An easily readable soln
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        
        for (int a=1;a<4;a++)
            for(int b=1;b<4;b++)
                for(int c=1;c<4;c++)
                    for(int d=1;d<4;d++)
                        if (a+b+c+d == len) {
                            int A = Integer.valueOf(s.substring(0, a));
                            int B = Integer.valueOf(s.substring(a, a+b));
                            int C = Integer.valueOf(s.substring(a+b, a+b+c));
                            int D = Integer.valueOf(s.substring(a+b+c));
                            
                            if (A <= 255 && B <= 255 && C <= 255 && D <= 255) {
                                sb.append(A)
                                .append(".")
                                .append(B)
                                .append(".")
                                .append(C)
                                .append(".")
                                .append(D);
                            
                                if (sb.length() == len+3)
                                    res.add(sb.toString());
                            
                                sb.delete(0, sb.length());
                            }
                        }
        
        return res;
    }
    
}
