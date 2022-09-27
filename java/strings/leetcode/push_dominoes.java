/*
838. Push Dominoes

There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.

After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

You are given a string dominoes representing the initial state where:

dominoes[i] = 'L', if the ith domino has been pushed to the left,
dominoes[i] = 'R', if the ith domino has been pushed to the right, and
dominoes[i] = '.', if the ith domino has not been pushed.
Return a string representing the final state.

 

Example 1:

Input: dominoes = "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.
Example 2:


Input: dominoes = ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."
 

Constraints:

n == dominoes.length
1 <= n <= 105
dominoes[i] is either 'L', 'R', or '.'.
*/



//My soln TC O(N^2) SC O(N)
class Solution {
    public String pushDominoes(String dominoes) {
        boolean change = true;
        char[] arr = dominoes.toCharArray();
        while (change) {
            change = false;
            for (int i=0;i<arr.length;++i) {
                if (arr[i] != '.') continue;
                if (i == 0) {
                    if (arr.length > 1 && arr[1] == 'L') {
                        arr[i] = 'l';
                    }
                } else if (i == arr.length-1) {
                    if (arr[i-1] == 'R') {
                        arr[i] = 'r';
                    }
                } else {
                    if (arr[i-1] == 'R' && arr[i+1] == 'L') continue;
                    if (arr[i-1] == 'R') {
                        arr[i] = 'r';
                        change = true;
                    } else if (arr[i+1] == 'L') {
                        arr[i] = 'l';
                        change = true;
                    }
                }
            }
            for (int i=0;i<arr.length;++i) {
                if (arr[i] == 'l') arr[i] = 'L';
                else if (arr[i] == 'r') arr[i] = 'R';
            }
        }
        return new String(arr);
    }
}
