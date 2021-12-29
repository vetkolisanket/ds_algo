/*
140. Word Break II

Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []
 

Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
*/

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return traverse(s, wordDict, new HashMap());
    }
    
    private List<String> traverse(String s, List<String> wordDict, Map<String, List<String>> map) {
        if (map.containsKey(s)) return map.get(s);
        List<String> res = new ArrayList();
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                String next = s.substring(word.length());
                if (next.length() == 0) res.add(word);
                else for (String sub : traverse(s.substring(word.length()), wordDict, map)) res.add(word + " " + sub);
            }
        }
        map.put(s, res);
        return res;
    }
    
}

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return traverse(s, wordDict, new HashMap());
    }
    
    private List<String> traverse(String s, List<String> wordDict, Map<String, List<String>> map) {
        if (map.containsKey(s)) return map.get(s);
        List<String> res = new ArrayList();
        int length = s.length();
        if (wordDict.contains(s)) res.add(s);
        for (int i=1; i < length; i++) {
            String ss = s.substring(i);
            if (wordDict.contains(ss)) {
                for (String sub : traverse(s.substring(0, i), wordDict, map)) {
                    res.add(sub + " " + ss);
                }
            }
        }
        map.put(s, res);
        return res;
    }
    
}
