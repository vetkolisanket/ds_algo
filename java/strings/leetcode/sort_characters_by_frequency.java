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

//Soln using multi set and bucket sort TC O(n) SC O(n)
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap();
        for (char c: s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int max = Collections.max(map.values());
        List<Character>[] buckets = new ArrayList[max+1];
        for (int i=0;i<=max;i++) {
            buckets[i] = new ArrayList();
        }
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            buckets[entry.getValue()].add(entry.getKey());
        }
        StringBuilder sb = new StringBuilder();
        for (int i=max;i>0;i--) {
            for (char c: buckets[i]) {
                for (int j=0;j<i;j++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}

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

//Soln from another attempt TC O(N + KlogK) SC O(K)
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> charToFreq = new HashMap();
        for (char c: s.toCharArray()) {
            charToFreq.put(c, charToFreq.getOrDefault(c, 0) + 1);
        }
        TreeMap<Integer, List<Character>> freqToChar = new TreeMap(Collections.reverseOrder());
        for (Map.Entry<Character, Integer> entry: charToFreq.entrySet()) {
            freqToChar.computeIfAbsent(entry.getValue(), k -> new ArrayList())
            .add(entry.getKey());
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<Character>> entry: freqToChar.entrySet()) {
            List<Character> list = entry.getValue();
            for (char c: list) {
                for (int i=0;i<entry.getKey();i++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
