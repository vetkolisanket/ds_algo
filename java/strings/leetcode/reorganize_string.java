/*
767. Reorganize String

Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.

 

Example 1:

Input: s = "aab"
Output: "aba"
Example 2:

Input: s = "aaab"
Output: ""
 

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.

*/

//Soln using counting and priority queue TC O(N) SC O(1)
class Solution {
    public String reorganizeString(String s) {
        int[] charCount = new int[26];
        for (char c: s.toCharArray()) {
            charCount[c-'a']++;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[1] - a[1]);
        for (int i=0;i<26;i++) {
            if (charCount[i] == 0) continue;
            pq.offer(new int[]{i+'a', charCount[i]});
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            if (sb.isEmpty() || sb.charAt(sb.length()-1) != (char)first[0]) {
                sb.append((char)first[0]);
                if (--first[1] > 0) {
                    pq.offer(first);
                }
            } else {
                if (pq.isEmpty()) return "";
                int[] second = pq.poll();
                sb.append((char)second[0]);
                if (--second[1] > 0) {
                    pq.offer(second);
                }
                pq.offer(first);
            }
        }
        return sb.toString();
    }
}

//Another soln using odd even TC O(N) SC O(N)
class Solution {
    public String reorganizeString(String s) {
        int n = s.length();
        int[] charCount = new int[26];
        for (char c: s.toCharArray()) {
            charCount[c-'a']++;
        }
        int maxCount = 0, maxCountIdx = 0;
        char maxChar = 'a';
        for (int i=0;i<26;i++) {
            if (charCount[i] > maxCount) {
                maxCount = charCount[i];
                maxCountIdx = i;
                maxChar = (char)(i+'a');
            }
        }
        if (maxCount > (n+1)/2) return "";
        int index = 0;
        char[] arr = new char[n];
        while (charCount[maxCountIdx]-- > 0) {
            arr[index] = maxChar;
            index += 2;
        }
        for (int i=0;i<26;i++) {
            while (charCount[i]-- > 0) {
                if (index >= n) {
                    index = 1;
                }
                arr[index] = (char)(i+'a');
                index += 2;
            }
        }
        return new String(arr);
    }
}
