/*
387. First Unique Character in a String

Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.

 

Example 1:

Input: s = "leetcode"
Output: 0
Example 2:

Input: s = "loveleetcode"
Output: 2
Example 3:

Input: s = "aabb"
Output: -1
 

Constraints:

1 <= s.length <= 10^5
s consists of only lowercase English letters.
*/

//My soln TC O(n) SC O(1) since there can at most be 26 unique characters
class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap();
        int length = s.length();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        for (int i=0;i<length;i++) {
            if (map.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }
}

//Soln which will be faster if string in long
class Solution {
    public int firstUniqChar(String s) {
        int[] arr = new int[26];
        int length = s.length();
        Arrays.fill(arr, length);
        for (int i=0;i<length;i++) {
            char c = s.charAt(i);
            if (arr[c -'a'] == length) arr[c -'a'] = i;
            else arr[c - 'a'] = -1;
        }
        int res = length;
        for (int i=0;i<26;i++) {
            if (arr[i] != length && arr[i] != -1) {
                res = Math.min(res, arr[i]);
            }
        }
        return res == length ? -1 : res;
    }
}

//Soln from 2nd attempt
class Solution {
    public int firstUniqChar(String s) {
        TreeMap<Integer, Character> treeMap = new TreeMap();
        Map<Character, Integer> map = new HashMap();
        int len = s.length();
        for (int i=0;i<len;i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                treeMap.put(i, c);
                map.put(c, i);
            } else treeMap.remove(map.get(c));
        }
        for (Map.Entry<Integer, Character> entry: treeMap.entrySet()) {
            return entry.getKey();
        }
        return -1;
    }
}
