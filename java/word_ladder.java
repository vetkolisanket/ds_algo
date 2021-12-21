/*
127. Word Ladder

A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 

Constraints:

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
*/

//Two end BFS soln, faster
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        
        Set<String> dict = new HashSet(wordList);
        Set<String> beginSet = new HashSet();
        Set<String> endSet = new HashSet();
        
        beginSet.add(beginWord);
        endSet.add(endWord);
        
        dict.remove(beginWord);
        dict.remove(endWord);
        
        int step = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            
            Set<String> temp = new HashSet();
            for (String word: beginSet) {
                char[] ch = word.toCharArray();
                for (int i=0;i<ch.length;i++) {
                    char oldChar = ch[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        ch[i] = c;
                        String target = String.valueOf(ch);
                        if (endSet.contains(target)) return step+1;
                        if (dict.contains(target)) {
                            temp.add(target);
                            dict.remove(target);
                        }
                    }
                    ch[i] = oldChar;
                }
            }
            beginSet = temp;
            step++;
        }
        return 0;
    }
}

//My soln based on word ladder 2
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        
        Set<String> wordSet = new HashSet(wordList);
        Set<String> visited = new HashSet();
        Queue<List<String>> queue = new LinkedList();
        queue.offer(Arrays.asList(beginWord));
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                List<String> cur = queue.poll();
                String lastWord = cur.get(cur.size()-1);
                List<String> neighbours = getNeighbours(lastWord, wordSet);
                for (String neighbour : neighbours) {
                    if (neighbour.equals(endWord)) return cur.size()+1;
                    else {
                        List<String> list = new ArrayList(cur);
                        list.add(neighbour);
                        visited.add(neighbour);
                        queue.offer(list);
                    }
                }
            }
            for (String s : visited) wordSet.remove(s);
            visited.clear();
        }
        return 0;
    }
    
    private List<String> getNeighbours(String word, Set<String> wordSet) {
        int length = word.length();
        char[] ch = word.toCharArray();
        List<String> neighbours = new LinkedList();
        char oldChar;
        for (int i=0; i<length; i++) {
            oldChar = ch[i];
            for (char c = 'a'; c <= 'z'; c++) {
                ch[i] = c;
                String s = new String(ch);
                if (wordSet.contains(s)) neighbours.add(s);
            }
            ch[i] = oldChar;
        }
        return neighbours;
    }
}
