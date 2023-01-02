/*
520. Detect Capital

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital, like "Google".
Given a string word, return true if the usage of capitals in it is right.

 

Example 1:

Input: word = "USA"
Output: true
Example 2:

Input: word = "FlaG"
Output: false
 

Constraints:

1 <= word.length <= 100
word consists of lowercase and uppercase English letters.
*/

//Soln using 3 variable and in one iteration TC O(N) SC O(1)
class Solution {
    public boolean detectCapitalUse(String word) {
        boolean allCapital = true, allSmall = true, firstCapital = true;
        for (int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            if (i == 0) {
                if (c >= 'A' && c <= 'Z') {
                    allSmall = false;
                } else {
                    allCapital = false;
                    firstCapital = false;
                }
            } else {
                if (c >= 'A' && c <= 'Z') {
                    if (!allCapital) return false;
                    allSmall = false;
                    firstCapital = false;
                } else {
                    allCapital = false;
                }
            }
        }
        return allCapital || allSmall || firstCapital;
    }
}
