/*
1662. Check If Two String Arrays are Equivalent

Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.

A string is represented by an array if the array elements concatenated in order forms the string.

 

Example 1:

Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
Output: true
Explanation:
word1 represents string "ab" + "c" -> "abc"
word2 represents string "a" + "bc" -> "abc"
The strings are the same, so return true.
Example 2:

Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
Output: false
Example 3:

Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
Output: true
 

Constraints:

1 <= word1.length, word2.length <= 10^3
1 <= word1[i].length, word2[i].length <= 10^3
1 <= sum(word1[i].length), sum(word2[i].length) <= 10^3
word1[i] and word2[i] consist of lowercase letters.
*/

//My soln using TC O(N*K) SC O(N*K) where N is the no. of strings in the list and K is the length of the largest string
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (String s: word1) {
            sb1.append(s);
        }
        for (String s: word2) {
            sb2.append(s);
        }
        return sb1.toString().equals(sb2.toString());
    }
}

//A more space efficient soln TC O(N*K) SC O(1) where N is the no. of strings in the array and K is the max length of the string
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int word1Pointer = 0, word2Pointer = 0, string1Pointer = 0, string2Pointer = 0;
        while (word1Pointer < word1.length && word2Pointer < word2.length) {
            if (word1[word1Pointer].charAt(string1Pointer++) != word2[word2Pointer].charAt(string2Pointer++)) {
                return false;
            }
            
            if (string1Pointer == word1[word1Pointer].length()) {
                word1Pointer++;
                string1Pointer = 0;
            }
            
            if (string2Pointer == word2[word2Pointer].length()) {
                word2Pointer++;
                string2Pointer = 0;
            }
        }
        
        return word1Pointer == word1.length && word2Pointer == word2.length;
    }
}
