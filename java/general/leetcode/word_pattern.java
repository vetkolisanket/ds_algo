/*
290. Word Pattern

Given a pattern and a string s, find if s follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.

 

Example 1:

Input: pattern = "abba", s = "dog cat cat dog"
Output: true
Example 2:

Input: pattern = "abba", s = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", s = "dog cat cat dog"
Output: false
 

Constraints:

1 <= pattern.length <= 300
pattern contains only lower-case English letters.
1 <= s.length <= 3000
s contains only lowercase English letters and spaces ' '.
s does not contain any leading or trailing spaces.
All the words in s are separated by a single space.

*/

//My soln using two hash maps TC O(M) SC O(M) where M is the length of the string
class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] arr = s.split(" ");
        Map<Character, String> csMap = new HashMap();
        Map<String, Character> scMap = new HashMap();
        if (pattern.length() != arr.length) return false;
        for (int i=0;i<pattern.length();i++) {
            char c = pattern.charAt(i);
            String word = arr[i];
            if ((!csMap.containsKey(c) && !scMap.containsKey(word))) {
                csMap.put(c, word);
                scMap.put(word, c);
            } else if (csMap.containsKey(c) && scMap.containsKey(word)) {
                if (!arr[i].equals(csMap.get(c)) || c != scMap.get(word)) {
                    return false;
                }
            } else return false;
        }
        return true;
    }
}

//Another approach using a map and a set TC O(M) SC O(M) where M is the no. of characters in S
class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) return false;
        Map<Character, String> map = new HashMap();
        Set<String> set = new HashSet();
        for (int i=0;i<words.length;i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            if (map.containsKey(c)) {
                if (!word.equals(map.get(c))) return false;
            } else {
                if (set.contains(word)) return false;
                map.put(c, word);
                set.add(word);
            }
        }
        return true;
    }
}

