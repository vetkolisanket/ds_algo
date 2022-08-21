/*
936. Stamping The Sequence

You are given two strings stamp and target. Initially, there is a string s of length target.length with all s[i] == '?'.

In one turn, you can place stamp over s and replace every letter in the s with the corresponding letter from stamp.

For example, if stamp = "abc" and target = "abcba", then s is "?????" initially. In one turn you can:
place stamp at index 0 of s to obtain "abc??",
place stamp at index 1 of s to obtain "?abc?", or
place stamp at index 2 of s to obtain "??abc".
Note that stamp must be fully contained in the boundaries of s in order to stamp (i.e., you cannot place stamp at index 3 of s).
We want to convert s to target using at most 10 * target.length turns.

Return an array of the index of the left-most letter being stamped at each turn. If we cannot obtain target from s within 10 * target.length turns, return an empty array.

 

Example 1:

Input: stamp = "abc", target = "ababc"
Output: [0,2]
Explanation: Initially s = "?????".
- Place stamp at index 0 to get "abc??".
- Place stamp at index 2 to get "ababc".
[1,0,2] would also be accepted as an answer, as well as some other answers.
Example 2:

Input: stamp = "abca", target = "aabcaca"
Output: [3,0,1]
Explanation: Initially s = "???????".
- Place stamp at index 3 to get "???abca".
- Place stamp at index 0 to get "abcabca".
- Place stamp at index 1 to get "aabcaca".
 

Constraints:

1 <= stamp.length <= target.length <= 1000
stamp and target consist of lowercase English letters.
*/

//As per me TC O((N-M)*(N-M)*M) and SC O(N) since N >= M where N is target length and M is stamp length
class Solution {
    public int[] movesToStamp(String stamp, String target) {
        char[] s = stamp.toCharArray();
        char[] t = target.toCharArray();
        List<Integer> res = new ArrayList();
        boolean[] visited = new boolean[t.length];
        int stars = 0;
        while (stars < t.length) {
            boolean didReplace = false;
            for (int i=0;i<=t.length-s.length;++i) {
                if (!visited[i] && canReplace(t, i, s)) {
                    stars += doReplace(t, i, s);
                    visited[i] = true;
                    res.add(i);
                    didReplace = true;
                    if (stars == t.length) break;
                }
            }
            if (!didReplace) return new int[0];
        }
        int[] ans = new int[res.size()];
        for (int i=0;i<ans.length;++i) {
            ans[i] = res.get(ans.length-i-1);
        }
        return ans;
    }
    
    private boolean canReplace(char[] t, int i, char[] s) {
        for (int j=0;j<s.length;++j) {
            if (t[i+j] != '*' && t[i+j] != s[j]) {
                return false;
            }
        }
        return true;
    }
    
    private int doReplace(char[] t, int i, char[] s) {
        int stars = 0;
        for (int j=0;j<s.length;++j) {
            if (t[i+j] != '*') {
                t[i+j] = '*';
                stars++;
            }
        }
        return stars;
    }
}
