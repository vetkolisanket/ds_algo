/*
2024. Maximize the Confusion of an Exam

A teacher is writing a test with n true/false questions, with 'T' denoting true and 'F' denoting false. He wants to confuse the students by maximizing the number of consecutive questions with the same answer (multiple trues or multiple falses in a row).

You are given a string answerKey, where answerKey[i] is the original answer to the ith question. In addition, you are given an integer k, the maximum number of times you may perform the following operation:

Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i] to 'T' or 'F').
Return the maximum number of consecutive 'T's or 'F's in the answer key after performing the operation at most k times.

 

Example 1:

Input: answerKey = "TTFF", k = 2
Output: 4
Explanation: We can replace both the 'F's with 'T's to make answerKey = "TTTT".
There are four consecutive 'T's.
Example 2:

Input: answerKey = "TFFT", k = 1
Output: 3
Explanation: We can replace the first 'T' with an 'F' to make answerKey = "FFFT".
Alternatively, we can replace the second 'T' with an 'F' to make answerKey = "TFFF".
In both cases, there are three consecutive 'F's.
Example 3:

Input: answerKey = "TTFTTFTT", k = 1
Output: 5
Explanation: We can replace the first 'F' to make answerKey = "TTTTTFTT"
Alternatively, we can replace the second 'F' to make answerKey = "TTFTTTTT". 
In both cases, there are five consecutive 'T's.
 

Constraints:

n == answerKey.length
1 <= n <= 5 * 10^4
answerKey[i] is either 'T' or 'F'
1 <= k <= n
*/

//Soln using sliding window TC O(N) SC O(1)
class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int left = 0, right = 0, tCount = 0, fCount = 0, ans = 0;
        for (; right < answerKey.length(); right++) {
            if (answerKey.charAt(right) == 'T') tCount++;
            else fCount++;
            while (tCount > k) {
                if (answerKey.charAt(left++) == 'T') tCount--;
                else fCount--;
            }
            ans = Math.max(ans, right - left + 1);
        }
        left = 0; right = 0; tCount = 0; fCount = 0;
        for (; right < answerKey.length(); right++) {
            if (answerKey.charAt(right) == 'T') tCount++;
            else fCount++;
            while (fCount > k) {
                if (answerKey.charAt(left++) == 'T') tCount--;
                else fCount--;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}

//Soln from another attempt using sliding window TC O(N) SC O(1)
class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int n = answerKey.length();
        int tCount = 0, fCount = 0, i = 0, j = 0, ans = 0;
        while (i < n) {
            while (i < n && (tCount <= k || fCount <= k)) {
                if (answerKey.charAt(i++) == 'T') {
                    tCount++;
                } else {
                    fCount++;
                }
            }
            ans = Math.max(ans, i-j-1);
            while ((tCount > k && fCount > k)) {
                if (answerKey.charAt(j++) == 'T') {
                    tCount--;
                } else {
                    fCount--;
                }
            }
        }
        ans = Math.max(ans, i-j);
        return ans;
    }
}

//Soln using advanced sliding window TC O(N) SC O(1)
class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int ans = 0, tCount = 0, fCount = 0, n = answerKey.length();
        for (int i=0;i<n;i++) {
            if (answerKey.charAt(i) == 'T') tCount++;
            else fCount++;

            if (Math.min(tCount, fCount) <= k) ans++;
            else if (answerKey.charAt(i-ans) == 'T') tCount--;
            else fCount--;
        }
        return ans;
    }
}
