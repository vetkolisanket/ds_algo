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

//Soln from another attempt TC O(N*4^N) SC O(N) where N is the length of the digits string
class Solution {

    private String[] arr = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList();
        if (digits.length() == 0) return ans;
        backtrack(digits, ans, 0, new StringBuilder());
        return ans;
    }

    private void backtrack(String digits, List<String> ans, int idx, StringBuilder sb) {
        if (idx == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        for (char c: arr[digits.charAt(idx) - '0'].toCharArray()) {
            sb.append(c);
            backtrack(digits, ans, idx+1, sb);
            sb.setLength(sb.length()-1);
        }
    }
}

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
