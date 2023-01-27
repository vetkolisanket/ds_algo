/*
472. Concatenated Words

Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.

A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

 

Example 1:

Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
"dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
Example 2:

Input: words = ["cat","dog","catdog"]
Output: ["catdog"]
 

Constraints:

1 <= words.length <= 10^4
1 <= words[i].length <= 30
words[i] consists of only lowercase English letters.
All the strings of words are unique.
1 <= sum(words[i].length) <= 10^5

*/

//Soln using DP TC O(n*l^2)  SC O(n*l) where n is the no. of words and l is the length of longest word

class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> dict = new HashSet(Arrays.asList(words));
        List<String> ans = new ArrayList();
        for (String word: words) {
            int len = word.length();
            boolean[] dp = new boolean[len+1];
            dp[0] = true;
            for (int i=1;i<=len;i++) {
                for (int j = (i == len) ? 1 : 0; !dp[i] && j < i; j++) {
                    dp[i] = dp[j] && dict.contains(word.substring(j, i));
                }
            }
            if (dp[len]) ans.add(word);
        }
        return ans;
    }
}
