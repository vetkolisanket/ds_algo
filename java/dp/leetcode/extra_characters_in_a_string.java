/*
2707. Extra Characters in a String

You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary. There may be some extra characters in s which are not present in any of the substrings.

Return the minimum number of extra characters left over if you break up s optimally.

 

Example 1:

Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
Output: 1
Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.

Example 2:

Input: s = "sayhelloworld", dictionary = ["hello","world"]
Output: 3
Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12. The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.
 

Constraints:

1 <= s.length <= 50
1 <= dictionary.length <= 50
1 <= dictionary[i].length <= 50
dictionary[i] and s consists of only lowercase English letters
dictionary contains distinct words

*/

//Soln using top down dp TC O(N^3) SC O(N+MK) where N is the length of the string, M is the average length of words in dictionary, K is the no. of words in dictionary
class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        Integer[] memo = new Integer[n];
        Set<String> set = new HashSet(Arrays.asList(dictionary));
        return dp(s, 0, n, memo, set);
    }

    private int dp(String s, int start, int n, Integer[] memo, Set<String> words) {
        if (start == n) return 0;
        if (memo[start] != null) return memo[start];

        int ans = dp(s, start+1, n, memo, words) + 1;
        for (int end = start; end < n; end++) {
            String ss = s.substring(start, end+1);
            if (words.contains(ss)) {
                ans = Math.min(ans, dp(s, end+1, n, memo, words));
            }
        }
        return memo[start] = ans;
    }
}
