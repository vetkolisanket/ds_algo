/*
820. Short Encoding of Words

A valid encoding of an array of words is any reference string s and array of indices indices such that:

words.length == indices.length
The reference string s ends with the '#' character.
For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character is equal to words[i].
Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.

 

Example 1:

Input: words = ["time", "me", "bell"]
Output: 10
Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
Example 2:

Input: words = ["t"]
Output: 2
Explanation: A valid encoding would be s = "t#" and indices = [0].
 

Constraints:

1 <= words.length <= 2000
1 <= words[i].length <= 7
words[i] consists of only lowercase letters.
*/

//Soln using trie, faster O(summation(wi)) time and space complexity where wi is the length of ith word in words 
class Solution {
    public int minimumLengthEncoding(String[] words) {
        TrieNode trie = new TrieNode();
        Map<TrieNode, Integer> map = new HashMap();
        for (int j=0;j<words.length;j++) {
            String word = words[j];
            TrieNode cur = trie;
            for (int i=word.length()-1;i>=0;i--) {
                cur = cur.get(word.charAt(i));
            }
            map.put(cur, j);
        }
        int ans = 0;
        for (Map.Entry<TrieNode, Integer> entry: map.entrySet()) {
            if (entry.getKey().count == 0) {
                ans += words[entry.getValue()].length() + 1;
            }
        }
        return ans;
    }
}

class TrieNode {
    TrieNode[] children;
    int count;
    
    public TrieNode() {
        children = new TrieNode[26];
        count = 0;
    }
    
    public TrieNode get(char c) {
        int val = c - 'a';
        if (children[val] == null) {
            children[val] = new TrieNode();
        }
        count++;
        return children[val];
    }
}

//Soln by storing prefixes, O(summation(wi^2)) time and O(summation(wi)) space, where wi is the length of the ith word
class Solution {
    public int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet(Arrays.asList(words));
        for (String word: words) {
            int length = word.length();
            for (int i=1;i<length;i++) {
                set.remove(word.substring(i));
            }
        }
        int ans = 0;
        for (String word: set) {
            ans += word.length() + 1;
        }
        return ans;
    }
}


