/*
2531. Make Number of Distinct Characters Equal

You are given two 0-indexed strings word1 and word2.

A move consists of choosing two indices i and j such that 0 <= i < word1.length and 0 <= j < word2.length and swapping word1[i] with word2[j].

Return true if it is possible to get the number of distinct characters in word1 and word2 to be equal with exactly one move. Return false otherwise.

 

Example 1:

Input: word1 = "ac", word2 = "b"
Output: false
Explanation: Any pair of swaps would yield two distinct characters in the first string, and one in the second string.
Example 2:

Input: word1 = "abcc", word2 = "aab"
Output: true
Explanation: We swap index 2 of the first string with index 0 of the second string. The resulting strings are word1 = "abac" and word2 = "cab", which both have 3 distinct characters.
Example 3:

Input: word1 = "abcde", word2 = "fghij"
Output: true
Explanation: Both resulting strings will have 5 distinct characters, regardless of which indices we swap.
 

Constraints:

1 <= word1.length, word2.length <= 10^5
word1 and word2 consist of only lowercase English letters.
*/

//Soln using 2 hash maps TC O(m*n) SC O(m+n)
class Solution {
    public boolean isItPossible(String word1, String word2) {
        Map<Character, Integer> map1 = new HashMap(), map2 = new HashMap();
        for (char c: word1.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        for (char c: word2.toCharArray()) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        for (char c1: map1.keySet()) {
            for (char c2: map2.keySet()) {
                if (c1 == c2) {
                    if (map1.size() == map2.size()) {
                        return true;
                    }
                } else {
                    int size1 = map1.size(), size2 = map2.size();
                    if (map1.get(c1) == 1) size1--;
                    if (!map1.containsKey(c2)) size1++;
                    if (map2.get(c2) == 1) size2--;
                    if (!map2.containsKey(c1)) size2++;
                    if (size1 == size2) return true;
                }
            }
        }
        return false;
    }
}
