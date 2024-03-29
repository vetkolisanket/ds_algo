/*
967. Numbers With Same Consecutive Differences

Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.

Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is invalid.

You may return the answer in any order.

 

Example 1:

Input: n = 3, k = 7
Output: [181,292,707,818,929]
Explanation: Note that 070 is not a valid number, because it has leading zeroes.
Example 2:

Input: n = 2, k = 1
Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 

Constraints:

2 <= n <= 9
0 <= k <= 9
*/

//My soln TC O(2^n) SC O(2^n)
class Solution {
    public int[] numsSameConsecDiff(int n, int k) {
        Set<String> set = new HashSet();
        backtrack(n, k, set, new StringBuilder());
        int size = set.size();
        int[] ans = new int[size];
        int i = 0;
        for (String s: set) {
            ans[i++] = Integer.parseInt(s);
        }
        return ans;
    }
    
    private void backtrack(int n, int k, Set<String> set, StringBuilder sb) {
        if (sb.length() == n) {
            set.add(sb.toString());
            return;
        }
        if (sb.length() == 0) {
            for (int i=1;i<10;i++) {
                sb.append(i);
                backtrack(n, k, set, sb);
                sb.setLength(sb.length()-1);
            }
        } else {
            int num = sb.charAt(sb.length()-1) - '0';
            if (num - k >= 0) {
                sb.append(num-k);
                backtrack(n, k, set, sb);
                sb.setLength(sb.length()-1);
            }
            if (num + k < 10) {
                sb.append(num+k);
                backtrack(n, k, set, sb);
                sb.setLength(sb.length()-1);
            }
        }
    }
}
