/*
211. Design Add and Search Words Data Structure

Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 

Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
 

Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 3 dots in word for search queries.
At most 10^4 calls will be made to addWord and search.
*/

//Using trie whose children are stored in array
class TrieNode {
    
    TrieNode[] children = new TrieNode[26];
    boolean isWord = false;
    
}

class WordDictionary {
    
    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }
    
    public boolean search(String word) {
        return search(word.toCharArray(), root, 0);
    }
    
    private boolean search(char[] arr, TrieNode node, int k) {
        if (k == arr.length) {
            return node.isWord;
        }
        char c = arr[k];
        if (c == '.') {
            for (TrieNode child: node.children) {
                if (child != null && search(arr, child, k+1)) {
                    return true;
                }
            }
            return false;
        } else if (node.children[c - 'a'] != null) {
            return search(arr, node.children[c - 'a'], k+1);
        } else return false;
    }
}

//Using trie whose children are stored in a hashmap TC O(N) for adding word O(M) for searching word where N is the no. of letters in the word and M is the total no. of nodes in root trie
class TrieNode {
    
    Map<Character, TrieNode> children = new HashMap();
    boolean isWord = false;
    
}

class WordDictionary {
    
    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }
        node.isWord = true;
    }
    
    public boolean search(String word) {
        return search(word.toCharArray(), root, 0);
    }
    
    private boolean search(char[] arr, TrieNode node, int k) {
        if (k == arr.length) {
            return node.isWord;
        }
        char c = arr[k];
        if (c == '.') {
            for (Map.Entry<Character, TrieNode> entry: node.children.entrySet()) {
                if (search(arr, entry.getValue(), k+1)) {
                    return true;
                }
            }
            return false;
        } else if (node.children.containsKey(c)) {
            return search(arr, node.children.get(c), k+1);
        } else return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
