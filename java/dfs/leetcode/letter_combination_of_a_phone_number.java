/*
17. Letter Combinations of a Phone Number

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]
 

Constraints:

0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
*/
class Solution {
    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) return new ArrayList();
        List<String> combos = new ArrayList();
        String[] mapping = new String[] {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        dfs(combos, digits.toCharArray(), "", mapping);
        return combos;
    }
    
    private void dfs(List<String> combos, char[] digits, String s, String[] mapping){ 
        if(s.length() == digits.length) {
            combos.add(s);
            return;
        }
        int digit = digits[s.length()] - '0';
        for(char c: mapping[digit-2].toCharArray()){
            dfs(combos, digits, s+String.valueOf(c), mapping);
        }
    }
}
