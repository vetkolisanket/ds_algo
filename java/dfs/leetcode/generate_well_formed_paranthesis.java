/*
22. Generate Parentheses

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
 

Constraints:

1 <= n <= 8
*/

//Note have used string builder here as string concatenation is more expensive

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList();
        gVP(list, new StringBuilder(), 0, 0, n);
        return list;
    }
    
    private void gVP(List<String> list, StringBuilder sb, int start, int end, int n) {
        if(start == n && end == n) {
            list.add(sb.toString());
            return;
        }
        
        if(start<n) {
            gVP(list, sb.append("("), start+1, end, n);
            sb.setLength(sb.length()-1);
        }
        if(end<start) {
            gVP(list, sb.append(")"), start, end+1, n);
            sb.setLength(sb.length()-1);
            
        }
    }
}

/*
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList();
        gVP(list, "", 0, 0, n);
        return list;
    }
    
    private void gVP(List<String> list, String s, int start, int end, int n) {
        if(start == n && end == n) {
            list.add(s);
            return;
        }
        
        if(start<n) {
            gVP(list, s+"(", start+1, end, n);
        }
        if(end<start) {
            gVP(list, s+")", start, end+1, n);
        }
    }
}
*/
