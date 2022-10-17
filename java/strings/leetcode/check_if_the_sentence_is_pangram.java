/*
1832. Check if the Sentence Is Pangram

A pangram is a sentence where every letter of the English alphabet appears at least once.

Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.

 

Example 1:

Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
Output: true
Explanation: sentence contains at least one of every letter of the English alphabet.
Example 2:

Input: sentence = "leetcode"
Output: false
 

Constraints:

1 <= sentence.length <= 1000
sentence consists of lowercase English letters.
*/

//My soln TC O(N) SC O(1)
class Solution {
    public boolean checkIfPangram(String sentence) {
        Set<Character> set = new HashSet();
        for (char c: sentence.toCharArray()) {
            set.add(c);
            if (set.size() == 26) return true;
        }
        return false;
    }
}
