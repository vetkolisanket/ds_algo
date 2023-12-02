/*
1160. Find Words That Can Be Formed by Characters


You are given an array of strings words and a string chars.

A string is good if it can be formed by characters from chars (each character can only be used once).

Return the sum of lengths of all good strings in words.

 

Example 1:

Input: words = ["cat","bt","hat","tree"], chars = "atach"
Output: 6
Explanation: The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
Example 2:

Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
Output: 10
Explanation: The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
 

Constraints:

1 <= words.length <= 1000
1 <= words[i].length, chars.length <= 100
words[i] and chars consist of lowercase English letters.
*/

//Soln using hash map TC O(N + MK) where N is the length of chars, M is the length of words and K is the avg length of word
class Solution {
    public int countCharacters(String[] words, String chars) {
        HashMap<Character, Integer> map = new HashMap();
        for (char c: chars.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int ans = 0;
        for (String word: words) {
            HashMap<Character, Integer> temp = new HashMap();
            boolean isPossible = true;
            for (char c: word.toCharArray()) {
                temp.put(c, temp.getOrDefault(c, 0) + 1);
                if (temp.get(c) > map.getOrDefault(c, 0)) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) {
                ans += word.length();
            }
        }
        return ans;
    }
}
