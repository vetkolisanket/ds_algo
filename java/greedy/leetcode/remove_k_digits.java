/*
402. Remove K Digits

Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

 

Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 

Constraints:

1 <= k <= num.length <= 10^5
num consists of only digits.
num does not have any leading zeros except for the zero itself.

*/

//Soln using greedy and stack TC O(N) SC O(N)
class Solution {
    public String removeKdigits(String num, int k) {
        Deque<Integer> dq = new ArrayDeque();
        for (char c: num.toCharArray()) {
            int val = c - '0';
            while (!dq.isEmpty() && k > 0 && dq.peekLast() > val) {
                k--;
                dq.removeLast();
            }
            dq.offerLast(val);
        }
        for (int i=0;i<k;i++) {
            dq.removeLast();
        }
        StringBuilder sb = new StringBuilder();
        boolean leadingZeroes = true;
        while (!dq.isEmpty()) {
            int val = dq.pollFirst();
            if (leadingZeroes && val == 0) continue;
            leadingZeroes = false;
            sb.append(val);
        }
        if (sb.length() == 0) return "0";
        return sb.toString();
    }
}
