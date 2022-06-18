/*
745. Prefix and Suffix Search

Design a special dictionary with some words that searchs the words in it by a prefix and a suffix.

Implement the WordFilter class:

WordFilter(string[] words) Initializes the object with the words in the dictionary.
f(string prefix, string suffix) Returns the index of the word in the dictionary, which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 

Example 1:

Input
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
Output
[null, 0]

Explanation
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 

Constraints:

1 <= words.length <= 15000
1 <= words[i].length <= 10
1 <= prefix.length, suffix.length <= 10
words[i], prefix and suffix consist of lower-case English letters only.
At most 15000 calls will be made to the function f.
*/

class WordFilter {
    
    TrieNode trie;

    public WordFilter(String[] words) {
        trie = new TrieNode();
        for (int weight=0;weight<words.length;weight++) {
            String word = words[weight] + "{";
            int length = word.length();
            for (int i=0;i<length;i++) {
                TrieNode cur = trie;
                for (int j=i;j<2*length-1;j++) {
                    int k = word.charAt(j%length) - 'a';
                    if (cur.children[k] == null) {
                        cur.children[k] = new TrieNode();
                    }
                    cur = cur.children[k];
                    cur.weight = weight;
                }
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        TrieNode cur = trie;
        char[] arr = (suffix + "{" + prefix).toCharArray();
        for (int i=0;i<arr.length;i++) {
            int k = arr[i] - 'a';
            if (cur.children[k] == null) return -1;
            cur = cur.children[k];
        }
        return cur.weight;
    }
}

class TrieNode {
    TrieNode[] children;
    int weight;
    
    public TrieNode() {
        children = new TrieNode[27];
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
