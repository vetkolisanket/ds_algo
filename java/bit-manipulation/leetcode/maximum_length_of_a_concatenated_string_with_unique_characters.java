/*
1239. Maximum Length of a Concatenated String with Unique Characters

You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of arr that has unique characters.

Return the maximum possible length of s.

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

 

Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All the valid concatenations are:
- ""
- "un"
- "iq"
- "ue"
- "uniq" ("un" + "iq")
- "ique" ("iq" + "ue")
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
Explanation: The only string in arr has all 26 characters.
 

Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lowercase English letters.
*/

//Soln using bit manipulation TC O(2^N) SC O(2^min(N,K)) where N is the no. of strings and K is the no. of unique characters that appear in arr
class Solution {
    public int maxLength(List<String> arr) {
        Set<Integer> results = new HashSet<>(){{add(0);}};
        int best = 0;
        
        for (String word: arr) {
            best = Math.max(best, addWord(word, results));
        }
        return best;
    }
    
    private int addWord(String word, Set<Integer> results) {
        int charBitSet = 0;
        int best = 0;
        for (char c : word.toCharArray()) {
            int mask = 1 << c - 'a';
            if ((charBitSet & mask) > 0) {
                return 0;
            }
            charBitSet += mask;
        }
        
        if (results.contains(charBitSet)) {
            return 0;
        }
        
        Set<Integer> newResults = new HashSet();
        for (Integer res: results) {
            if ((res & charBitSet) > 0) {
                continue;
            }
            
            int newResLen = (res >> 26) + word.length();
            int newCharBitSet = (charBitSet + res) & ((1 << 26) - 1);
            
            newResults.add((newResLen << 26) + newCharBitSet);
            best = Math.max(best, newResLen);
        }
        results.addAll(newResults);
        return best;
    }
}
