/*
151. Reverse Words in a String

Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

 

Example 1:

Input: s = "the sky is blue"
Output: "blue is sky the"
Example 2:

Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:

Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 

Constraints:

1 <= s.length <= 10^4
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
There is at least one word in s.
 

Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
*/

//Soln using arraylist to store words and add them in reverse order to get the result TC O(N) SC O(N)
class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList();
        for (char c: s.toCharArray()) {
            if (c != ' ') {
                sb.append(c);
            } else if (sb.length() > 0) {
                list.add(sb.toString());
                sb.setLength(0);
            }
        }
        if (sb.length() > 0) {
            list.add(sb.toString());
            sb.setLength(0);
        }
        for (int i=list.size()-1;i>=0;i--) {
            sb.append(list.get(i)).append(" ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length()-1);
        }
        return sb.toString();
    }
}

//A more interview appropriate soln which is also faster
class Solution {
    public String reverseWords(String s) {
        char[] a = s.toCharArray();
        int n = a.length;
        
        reverse(a, 0, n-1);
        reverseWords(a, n);
        return cleanSpaces(a, n);
    }
    
    private void reverse(char[] a, int i, int n) {
        int j = n;
        while (i<j) {
            char c = a[j];
            a[j--] = a[i];
            a[i++] = c;
        }
    }
    
    private void reverseWords(char[] a, int n) {
        int i=0,j=0;
        while (i<n) {
            while (i<j||i<n && a[i] == ' ') i++;
            while (j<i||j<n && a[j] != ' ') j++;
            reverse(a, i, j-1);
        }
    }
    
    private String cleanSpaces(char[] a, int n) {
        int i=0,j=0;
        while (j<n) {
            while (j<n && a[j] == ' ') j++;
            while (j<n && a[j] != ' ') a[i++] = a[j++];
            while (j<n && a[j] == ' ') j++;
            if (j<n) a[i++] = ' ';
        }
        return new String(a).substring(0, i);
    }
}

//My soln
class Solution {
    public String reverseWords(String s) {
        String[] arr = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i=arr.length-1;i>0;i--) {
            sb.append(arr[i]).append(" ");
        }
        sb.append(arr[0]);
        return sb.toString();
    }
}
