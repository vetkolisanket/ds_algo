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

//Soln from another attempt
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList();
        int len = s.length();
        if (len < 4 || len > 12) return ans;
        for (char c: s.toCharArray()) {
            if (c < '0' || c > '9') return ans;
        }
        for (int i=1;i<4;i++) {
            for (int j=i+1;j<Math.min(i+4, len-1);j++) {
                for (int k=j+1;k<Math.min(j+4, len);k++) {
                    if (isValid(s, i, j, k)) {
                        ans.add(merge(s, i, j, k));
                    }
                }
            }
        }
        return ans;
    }

    private boolean isValid(String s, int i, int j, int k) {
        return isValid(s.substring(0, i)) && isValid(s.substring(i, j)) && isValid(s.substring(j, k)) && isValid(s.substring(k));
    }

    private boolean isValid(String s) {
        if (s.length() == 1) return true;
        if (s.charAt(0) == '0') return false;
        return Integer.parseInt(s) <= 255;
    }

    private String merge(String s, int i, int j, int k) {
        StringBuilder sb = new StringBuilder();
        return sb.append(s.substring(0, i))
                .append(".")
                .append(s.substring(i, j))
                .append(".")
                .append(s.substring(j, k))
                .append(".")
                .append(s.substring(k))
                .toString();
    }
}

//Soln using backtracking
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList();
        backtrack(s, ans, 0, new ArrayList());
        return ans;
    }

    private void backtrack(String s, List<String> ans, int start, List<Integer> dots) {
        if (dots.size() == 3) {
            if (isValid(s.substring(start))) {
                ans.add(merge(s, dots));
            }
            return;
        }
        int numCharsRemaining = s.length() - start;
        int numValsRemaining = 4 - dots.size();
        if (numCharsRemaining < numValsRemaining || numCharsRemaining > 3*numValsRemaining) return;
        for (int i=1;i<4 && i < numCharsRemaining;i++) {
            if (isValid(s.substring(start, start+i))) {
                dots.add(i);
                backtrack(s, ans, start+i, dots);
                dots.remove(dots.size()-1);
            }
        }
    }

    private boolean isValid(String s) {
        if (s.length() == 1) return true;
        if (s.charAt(0) == '0') return false;
        return Integer.parseInt(s) <= 255;
    }

    private String merge(String s, List<Integer> dots) {
        StringBuilder sb = new StringBuilder();
        int val = 0;
        for (int i=0;i<dots.size();i++) {
            sb.append(s.substring(val, val+dots.get(i)))
            .append(".");
            val += dots.get(i);
        }
        sb.append(s.substring(val));
        return sb.toString();
    }
}
