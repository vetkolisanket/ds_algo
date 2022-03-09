/*
First non-repeating character in a stream

Given an input stream of A of n characters consisting only of lower case alphabets. The task is to find the first non repeating character, each time a character is inserted to the stream. If there is no such character then append '#' to the answer.
 

Example 1:

Input: A = "aabc"
Output: "a#bb"
Explanation: For every character first non
repeating character is as follow-
"a" - first non-repeating character is 'a'
"aa" - no non-repeating character so '#'
"aab" - first non-repeating character is 'b'
"aabc" - first non-repeating character is 'b'
Example 2:

Input: A = "zz"
Output: "z#"
Explanation: For every character first non
repeating character is as follow-
"z" - first non-repeating character is 'z'
"zz" - no non-repeating character so '#'
 

Your Task:
You don't need to read or print anything. Your task is to complete the function FirstNonRepeating() which takes A as input parameter and returns a string after processing the input stream.
 

Expected Time Complexity: O(26 * n)
Expected Space Complexity: O(26)
 

Constraints:
1 <= n <= 10^5
*/

class Solution
{
    public String FirstNonRepeating(String A)
    {
        // code here
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new ArrayDeque();
		Map<Character, Boolean> map = new HashMap();
		for (char c: A.toCharArray()) {
		    if (!map.containsKey(c)) {
		        q.offer(c);
		        map.put(c, false);
		        while (!q.isEmpty() && map.get(q.peek())) q.poll();
		        if (q.isEmpty()) sb.append("#");
		        else sb.append(q.peek());
		    } else {
		        map.put(c, true);
		        while (!q.isEmpty() && map.get(q.peek())) q.poll();
		        if (q.isEmpty()) sb.append("#");
		        else sb.append(q.peek());
		    }
		}
		return sb.toString();
    }
}
