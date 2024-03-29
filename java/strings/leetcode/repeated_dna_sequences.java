/*
187. Repeated DNA Sequences

The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.

For example, "ACGAATTCCG" is a DNA sequence.
When studying DNA, it is useful to identify repeated sequences within the DNA.

Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.

 

Example 1:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]
Example 2:

Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]
 

Constraints:

1 <= s.length <= 10^5
s[i] is either 'A', 'C', 'G', or 'T'.
*/

//My soln
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int len = s.length();
        List<String> list = new ArrayList();
        if (len < 10) return list;
        Map<String, Integer> map = new HashMap();
        for (int i=0;i<=len-10;i++) {
            String ss = s.substring(i, i+10);
            if (map.containsKey(ss) && map.get(ss) == 1) {
                list.add(ss);
            }
            map.put(ss, map.getOrDefault(ss, 0)+1);
        }
        return list;
    }
}

//A smaller but slower soln
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int len = s.length();
        Set<String> words = new HashSet(), repeated = new HashSet();
        for (int i=0;i<=len-10;i++) {
            String ss = s.substring(i, i+10);
            if (!words.add(ss)) repeated.add(ss);
        }
        return new ArrayList(repeated);
    }
}
