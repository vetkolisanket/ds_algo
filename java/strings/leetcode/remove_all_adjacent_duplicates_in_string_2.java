/*
1209. Remove All Adjacent Duplicates in String II

You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.

 

Example 1:

Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.
Example 2:

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
Example 3:

Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"
 

Constraints:

1 <= s.length <= 105
2 <= k <= 104
s only contains lower case English letters.
*/

//Another approach using two pointers
class Solution {
    
    public String removeDuplicates(String s, int k) {
        Stack<Integer> stack = new Stack();
        char[] sa = s.toCharArray();
        int j=0;
        for (int i=0;i<sa.length;i++,j++) {
            sa[j] = sa[i];
            if (j==0||sa[j] != sa[j-1]) {
                stack.push(1);
            } else {
                int count = stack.pop()+1;
                if (count == k) {
                    j -= k;
                } else {
                    stack.push(count);
                }
            }
        }
        return new String(sa, 0, j);
    }
}

//Using custom class to hold char and its count
class Solution {
    
    class Pair {
        int count;
        char ch;
        
        public Pair(int count, char ch) {
            this.count = count;
            this.ch = ch;
        }
    }
    
    public String removeDuplicates(String s, int k) {
        Stack<Pair> stack = new Stack();
        int length = s.length();
        for (int i=0;i<length;i++) {
            if (stack.isEmpty() || s.charAt(i) != stack.peek().ch) {
                stack.push(new Pair(1, s.charAt(i)));
            } else {
                if (++stack.peek().count == k) {
                    stack.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair p = stack.pop();
            for (int i=0;i<p.count;i++) {
                sb.append(p.ch);
            }
        }
        return sb.reverse().toString();
    }
}
