/*
451. Sort Characters By Frequency

Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.

Return the sorted string. If there are multiple answers, return any of them.

 

Example 1:

Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input: s = "cccaaa"
Output: "aaaccc"
Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input: s = "Aabb"
Output: "bbAa"
Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
 

Constraints:

1 <= s.length <= 5 * 10^5
s consists of uppercase and lowercase English letters and digits.
*/

//My soln using hash map and list TC O(n + klogk) SC O(n) where n is the length of the string k is the no. of unique characters in the string
class Solution {
    
    class Holder {
        char c;
        int freq;
        
        public Holder(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }
    
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap();
        List<Holder> list = new ArrayList();
        for (char c: s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            list.add(new Holder(entry.getKey(), entry.getValue()));
        }
        Collections.sort(list, (a,b) -> b.freq - a.freq);
        StringBuilder sb = new StringBuilder();
        for (Holder holder: list) {
            for (int i=0;i<holder.freq;i++) {
                sb.append(holder.c);
            }
        }
        return sb.toString();
    }
}
