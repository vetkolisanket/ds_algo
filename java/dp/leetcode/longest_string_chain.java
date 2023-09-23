/*
1048. Longest String Chain

You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

Return the length of the longest possible word chain with words chosen from the given list of words.

 

Example 1:

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
Example 2:

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
Example 3:

Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
 

Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of lowercase English letters.

*/

//Soln using top down dp TC O(L^2*N) SC O(N) where L is the max possible length of a word and N is the no. of words
class Solution {
    public int longestStrChain(String[] words) {
        Map<String, Integer> memo = new HashMap();
        Set<String> wordsPresent = new HashSet();
        Collections.addAll(wordsPresent, words);
        int ans = 0;
        for (String word: words) {
            ans = Math.max(ans, dfs(wordsPresent, memo, word));
        }
        return ans;
    }

    private int dfs(Set<String> wordsPresent, Map<String, Integer> memo, String word) {
        if (memo.containsKey(word)) {
            return memo.get(word);
        }

        int maxLength = 1;
        StringBuilder sb = new StringBuilder(word);

        for (int i=0;i<word.length();i++) {
            sb.deleteCharAt(i);
            String newWord = sb.toString();
            if (wordsPresent.contains(newWord)) {
                int currentLength = 1 + dfs(wordsPresent, memo, newWord);
                maxLength = Math.max(maxLength, currentLength);
            }
            sb.insert(i, word.charAt(i));
        }
        memo.put(word, maxLength);
        return maxLength;
    }
}
