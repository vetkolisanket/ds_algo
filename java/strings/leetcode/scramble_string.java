/*
87. Scramble String

We can scramble a string s to get a string t using the following algorithm:

If the length of the string is 1, stop.
If the length of the string is > 1, do the following:
Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
Apply step 1 recursively on each of the two substrings x and y.
Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.

 

Example 1:

Input: s1 = "great", s2 = "rgeat"
Output: true
Explanation: One possible scenario applied on s1 is:
"great" --> "gr/eat" // divide at random index.
"gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
"gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at ranom index each of them.
"g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
"r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
"r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
The algorithm stops now and the result string is "rgeat" which is s2.
As there is one possible scenario that led s1 to be scrambled to s2, we return true.
Example 2:

Input: s1 = "abcde", s2 = "caebd"
Output: false
Example 3:

Input: s1 = "a", s2 = "a"
Output: true
 

Constraints:

s1.length == s2.length
1 <= s1.length <= 30
s1 and s2 consist of lower-case English letters.
*/

//Soln from another attempt, not sure about TC and SC :/

class Solution {

    Map<String, Boolean> map = new HashMap();

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        if (n == 1) return s1.charAt(0) == s2.charAt(0);
        String key = s1 + ":" + s2;
        if (map.containsKey(key)) return map.get(key);
        for (int i=1;i<n;i++) {
            if (
                (
                    isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                    isScramble(s1.substring(i), s2.substring(i))
                ) ||
                (
                    isScramble(s1.substring(0, i), s2.substring(n-i)) && 
                    isScramble(s1.substring(i), s2.substring(0, n-i))
                )
            ) {
                map.put(key, true);
                return true;
            }
        }
        map.put(key, false);
        return false;
    }
}

//Soln

class Solution {
    
    Map<String, Boolean> map = new HashMap();
    
    public boolean isScramble(String s1, String s2) {
        int len = s1.length();
        String s = new StringBuilder(s1).append(s2).toString();
        
        if (map.containsKey(s)) return map.get(s);
        
        if (s1.length() != s2.length()) {
            map.put(s, false);
            return false;
        }
        
        if (s1.equals(s2)) {
            map.put(s, true);
            return true;
        }
        
        int[] arr = new int[26];
        
        for (int i=0; i<len; i++) {
            arr[s1.charAt(i) - 'a']++;
            arr[s2.charAt(i) - 'a']--;
        }
        
        for (int i=0; i<26; i++) {
            if (arr[i] != 0) {
                map.put(s, false);
                return false;
            }
        }
        
        for (int i=1; i<len; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                map.put(s, true);
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(len-i)) && isScramble(s1.substring(i), s2.substring(0, len-i))) {
                map.put(s, true);
                return true;
            }
        }
        
        map.put(s, false);
        return false;
    }
}
