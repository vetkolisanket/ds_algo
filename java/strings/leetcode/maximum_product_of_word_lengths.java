/*
318. Maximum Product of Word Lengths

Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.

 

Example 1:

Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4
Explanation: The two words can be "ab", "cd".
Example 3:

Input: words = ["a","aa","aaa","aaaa"]
Output: 0
Explanation: No such pair of words.
 

Constraints:

2 <= words.length <= 1000
1 <= words[i].length <= 1000
words[i] consists only of lowercase English letters.
*/

//My soln
class Solution {
    public int maxProduct(String[] words) {
        Arrays.sort(words, (a, b) -> {
            return b.length() - a.length();
        });
        int ans = 0;
        for (int i=0;i<words.length-1;i++) {
            String a = words[i];
            if (Math.pow(a.length(), 2) < ans) break;
            for (int j=i+1;j<words.length;j++) {
                String b = words[j];
                if (areCharsDifferent(a, b)) {
                    ans = Math.max(ans, a.length()*b.length());
                }
            }
        }
        return ans;
    }
    
    private boolean areCharsDifferent(String a, String b) {
        Set<Character> set = new HashSet();
        for (char c: a.toCharArray()) {
            set.add(c);
        }
        for (char c: b.toCharArray()) {
            if (set.contains(c)) {
                return false;
            }
        }
        return true;
    }
}

//An optimised soln
class Solution {
    public int maxProduct(String[] words) {
        Map<Integer, Integer> map = new HashMap();
        preCompute(words, map);
        int maxProduct = 0;
        for (int x: map.keySet()) {
            for (int y: map.keySet()) {
                if ((x & y) == 0) {
                    maxProduct = Math.max(maxProduct, map.get(x)*map.get(y));
                }
            }
        }
        return maxProduct;
    }
    
    private void preCompute(String[] words, Map<Integer, Integer> map) {
        for (String word: words) {
            int bitMask = 0;
            for (char c: word.toCharArray()) {
                bitMask |= (1 << (c - 'a'));
            }
            map.put(bitMask, Math.max(map.getOrDefault(bitMask, 0), word.length()));
        }
    }
}
