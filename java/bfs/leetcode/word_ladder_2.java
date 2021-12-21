/*
126. Word Ladder II

A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 

Constraints:

1 <= beginWord.length <= 5
endWord.length == beginWord.length
1 <= wordList.length <= 1000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
*/

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new LinkedList();
        Set<String> wordSet = new HashSet(wordList);
        if (!wordSet.contains(endWord)) return ans;
        
        List<String> visited = new LinkedList();
        Queue<List<String>> queue = new LinkedList();
        queue.offer(Arrays.asList(beginWord));
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                List<String> cur = queue.poll();
                String lastWord = cur.get(cur.size()-1);
                List<String> neighbours = getNeighbours(lastWord, wordSet);
                for (String neighbour: neighbours) {
                    List<String> list = new ArrayList(cur);
                    list.add(neighbour);
                    visited.add(neighbour);
                    if (neighbour.equals(endWord)) {
                        ans.add(list);
                    } else {
                        queue.offer(list);
                    }
                }
            }
            for (String s : visited) wordSet.remove(s);
            visited.clear();
        }
        return ans;
    }
    
    private List<String> getNeighbours(String word, Set<String> wordSet) {
        int length = word.length();
        char[] ch = word.toCharArray();
        List<String> neighbours = new LinkedList();
        char oldChar;
        for (int i=0;i<length;i++) {
            oldChar = ch[i];
            for (char c = 'a';c <= 'z';c++) {
                ch[i] = c;
                String s = new String(ch);
                if (wordSet.contains(s)) neighbours.add(s);
            }
            ch[i] = oldChar;
        }
        return neighbours;
    }
}
