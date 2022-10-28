/*
49. Group Anagrams

Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Example 2:

Input: strs = [""]
Output: [[""]]
Example 3:

Input: strs = ["a"]
Output: [["a"]]
 

Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
*/

//My soln TC O(NK) SC O(NK) where N is the no. of strings and K is the length of the largest string
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList();
        Map<String, List<String>> map = new HashMap();
        for (String s: strs) {
            String sorted = getSortedString(s);
            if (!map.containsKey(sorted)) {
                List<String> list = new ArrayList();
                map.put(sorted, list);
            }
            map.get(sorted).add(s);
        }
        for (Map.Entry<String, List<String>> entry: map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }
    
    private String getSortedString(String s) {
        int[] arr = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            arr[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            while (arr[i] > 0) {
                sb.append((char) 'a' + i);
                arr[i]--;
            }
        }
        return sb.toString();
    }
}
