/*
Reverse each word in a given string

Given a String. Reverse each word in it where the words are separated by dots.

Example 1:

Input:
S = "i.like.this.program.very.much"
Output: 
i.ekil.siht.margorp.yrev.hcum
Explanation: 
The words are reversed as
follows:"i" -> "i","like"->"ekil",
"this"->"siht","program" -> "margorp",
"very" -> "yrev","much" -> "hcum".
Example 2:

Input: 
S = "pqr.mno"
Output: 
rqp.onm
Explanation: 
The words are reversed as
follows:"pqr" -> "rqp" ,
"mno" -> "onm"

Your Task:
You don't need to read input or print anything. Your task is to complete the functionreverseWords()which takes the string S as input and returns the resultant string by reversing all the words separated by dots.


Expected Time Complexity:O(|S|).
Expected Auxiliary Space:O(|S|).


Constraints:
1<=|S|<=10^5
*/

//Soln using stack
class Solution
{
   
    String reverseWords(String S)
    {
        // your code here
        int length = S.length();
        Stack<Character> stack = new Stack();
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<length;i++) {
            if (S.charAt(i) != '.') {
                stack.push(S.charAt(i));
            } else {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append('.');
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}

//Soln using pointers
class Solution
{
   
    String reverseWords(String S)
    {
        // your code here
        int length = S.length();
        char[] s = S.toCharArray();
        int i=0, j=0;
        while (j < length) {
            while (j < length && s[j] != '.') j++;
            int start = i, end = j-1;
            while (start < end) {
                char temp = s[start];
                s[start] = s[end];
                s[end] = temp;
                start++;
                end--;
            }
            j++;
            i=j;
        }
        return String.valueOf(s);
    }
}
