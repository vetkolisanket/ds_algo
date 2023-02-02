/*
953. Verifying an Alien Dictionary

In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.

 

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
*/

//Soln using hashmap to store character ordering TC O(M) SC O(1) where M is the total no. of characters in all words, SC is O(1) as no. of unique characters which we will be storing in order map is fixed to 26
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap();
        for (int i=0;i<26;i++) {
            map.put(order.charAt(i), i);
        }
        for (int i=0;i<words.length-1;i++) {
            String word1 = words[i], word2 = words[i+1];
            int len = Math.max(word1.length(), word2.length());
            for (int j=0;j<len;j++) {
                if (j == word2.length()) return false;
                if (j == word1.length()) break;
                if (map.get(word1.charAt(j)) > map.get(word2.charAt(j))) return false;
                if (map.get(word1.charAt(j)) < map.get(word2.charAt(j))) break;
            }
        }
        return true;
    }
}

//Faster soln using array instead of map TC and SC remain same though
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] arr = new int[26];
        for (int i=0;i<26;i++) {
            arr[order.charAt(i) - 'a'] = i;
        }
        for (int i=0;i<words.length-1;i++) {
            String word1 = words[i], word2 = words[i+1];
            int len = Math.max(word1.length(), word2.length());
            for (int j=0;j<len;j++) {
                if (j == word2.length()) return false;
                if (j == word1.length()) break;
                if (arr[word1.charAt(j) - 'a'] > arr[word2.charAt(j) - 'a']) return false;
                if (arr[word1.charAt(j) - 'a'] < arr[word2.charAt(j) - 'a']) break;
            }
        }
        return true;
    }
}
