/*
1002. Find Common Characters

Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.

 

Example 1:

Input: words = ["bella","label","roller"]
Output: ["e","l","l"]
Example 2:

Input: words = ["cool","lock","cook"]
Output: ["c","o"]
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of lowercase English letters.

*/

//My soln TC O(NK) SC O(1) where N is the length of the word array and K is the avg length of word
class Solution {
    public List<String> commonChars(String[] words) {
        int[] arr = new int[26];
        Arrays.fill(arr, 101);
        for (String word: words) {
            int[] freq = new int[26];
            for (char c: word.toCharArray()) {
                freq[c - 'a']++;
            }
            for (int i=0;i<26;i++) {
                arr[i] = Math.min(arr[i], freq[i]);
            }
        }
        List<String> ans = new ArrayList();
        for (int i=0;i<26;i++) {
            for (int j=0;j<arr[i];j++) {
                ans.add(String.valueOf((char)('a' + i)));
            }
        }
        return ans;
    }
}
