/*
2478. Number of Beautiful Partitions

You are given a string s that consists of the digits '1' to '9' and two integers k and minLength.

A partition of s is called beautiful if:

s is partitioned into k non-intersecting substrings.
Each substring has a length of at least minLength.
Each substring starts with a prime digit and ends with a non-prime digit. Prime digits are '2', '3', '5', and '7', and the rest of the digits are non-prime.
Return the number of beautiful partitions of s. Since the answer may be very large, return it modulo 109 + 7.

A substring is a contiguous sequence of characters within a string.

 

Example 1:

Input: s = "23542185131", k = 3, minLength = 2
Output: 3
Explanation: There exists three ways to create a beautiful partition:
"2354 | 218 | 5131"
"2354 | 21851 | 31"
"2354218 | 51 | 31"
Example 2:

Input: s = "23542185131", k = 3, minLength = 3
Output: 1
Explanation: There exists one way to create a beautiful partition: "2354 | 218 | 5131".
Example 3:

Input: s = "3312958", k = 3, minLength = 1
Output: 1
Explanation: There exists one way to create a beautiful partition: "331 | 29 | 58".
 

Constraints:

1 <= k, minLength <= s.length <= 1000
s consists of the digits '1' to '9'.
*/

//Soln using DP TC O(k*m) SC O(k*m) where k is the no. of partitions and m is the no. of valid partition indices in string s
class Solution {
    
    private final int MOD = 1_000_000_007;
    private Set<Character> primes = Set.of('2', '3', '5', '7');
    
    public int beautifulPartitions(String s, int k, int minLength) {
        int n = s.length();
        if (k*minLength > n) return 0;
        if (!primes.contains(s.charAt(0)) || primes.contains(s.charAt(n-1))) {
            return 0;
        }
        
        List<Integer> validBeginIndices = getValidBeginIndices(s, minLength, n);
        int m = validBeginIndices.size();
        if (m < k-1) return 0;
        
        Integer[][] dp = new Integer[k][m];
        return dfs(validBeginIndices, dp, 0, 0, k-1, minLength);
    }
    
    private List<Integer> getValidBeginIndices(String s, int minLen, int n) {
        List<Integer> list = new ArrayList();
        for (int i=minLen;i<=n-minLen;i++) {
            if (primes.contains(s.charAt(i)) && !primes.contains(s.charAt(i-1))) {
                list.add(i);
            }
        }
        return list;
    }
    
    private int dfs(List<Integer> list, Integer[][] dp, int idx, int pre, int k, int minLen) {
        if (k == 0) return 1;
        if (dp[k][idx] != null) return dp[k][idx];
        
        dp[k][idx] = 0;
        for (int i=idx;i<=list.size()-k;i++) {
            int cur = list.get(i);
            if (cur - pre < minLen) continue;
            dp[k][idx] = (dp[k][idx] + dfs(list, dp, i+1, cur, k-1, minLen)) % MOD;
        }
        return dp[k][idx];
    }
}
