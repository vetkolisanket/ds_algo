/*
916. Word Subsets

You are given two string arrays words1 and words2.

A string b is a subset of string a if every letter in b occurs in a including multiplicity.

For example, "wrr" is a subset of "warrior" but is not a subset of "world".
A string a from words1 is universal if for every string b in words2, b is a subset of a.

Return an array of all the universal strings in words1. You may return the answer in any order.

 

Example 1:

Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
Output: ["facebook","google","leetcode"]
Example 2:

Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
Output: ["apple","google","leetcode"]
 

Constraints:

1 <= words1.length, words2.length <= 10^4
1 <= words1[i].length, words2[i].length <= 10
words1[i] and words2[i] consist only of lowercase English letters.
All the strings of words1 are unique.
*/

//TC O(N+M) SC O(n) if space for answer is considered, where n is the length of words1, N = n*avg length of word in word1, M = no. of words in words2*avg length of word in word2
class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] uArr = new int[26];
        for (String word: words2) {
            fillUArr(uArr, word);
        }
        List<String> ans = new ArrayList();
        for (String word: words1) {
            if (isUniversal(word, uArr)) {
                ans.add(word);
            }
        }
        return ans;
    }
    
    private void fillUArr(int[] uArr, String word) {
        int[] arr = new int[26];
        for (char c: word.toCharArray()) {
            arr[c-'a']++;
        }
        for (int i=0;i<26;i++) {
            uArr[i] = Math.max(uArr[i], arr[i]);
        }
    }
    
    private boolean isUniversal(String word, int[] uArr) {
        int[] arr = new int[26];
        for (char c: word.toCharArray()) {
            arr[c - 'a']++;
        }
        for (int i=0;i<26;i++) {
            if (arr[i] < uArr[i]) {
                return false;
            }
        }
        return true;
    }
}
