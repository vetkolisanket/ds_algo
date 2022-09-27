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

//Faster soln TC O(N) SC O(N)
class Solution {
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        int[] indexes = new int[n+2];
        char[] symbols = new char[n+2];
        indexes[0] = -1;
        symbols[0] = 'L';
        int len = 1;
        for (int i=0;i<n;i++) {
            if (dominoes.charAt(i) != '.') {
                indexes[len] = i;
                symbols[len++] = dominoes.charAt(i);
            }
        }
        indexes[len] = n;
        symbols[len] = 'R';
        char[] arr = dominoes.toCharArray();
        for (int i=0;i<len;i++) {
            char x = symbols[i];
            char y = symbols[i+1];
            int left = indexes[i];
            int right = indexes[i+1];
            if (x == y) {
                for (int j=left+1;j<right;j++) {
                    arr[j] = x;
                }
            } else if (x > y) { //RL
                for (int j=left+1;j<right;j++) {
                    arr[j] = right-j == j-left ? '.' : right-j > j-left ? 'R' : 'L';
                }
            }
        }
        return new String(arr);
    }
}

//Another interesting approach using forces from both sides on the current domino TC O(N) SC O(N)
class Solution {
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        int[] forces = new int[n];
        int force = 0;
        for (int i=0;i<n;i++) {
            if (dominoes.charAt(i) == 'R') force = n;
            else if (dominoes.charAt(i) == 'L') force = 0;
            else force = Math.max(force-1, 0);
            forces[i] = force;
        }
        force = 0;
        for (int i=n-1;i>=0;i--) {
            if (dominoes.charAt(i) == 'L') force = n;
            else if (dominoes.charAt(i) == 'R') force = 0;
            else force = Math.max(force-1, 0);
            forces[i] -= force;
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<n;i++) {
            sb.append(forces[i] == 0 ? '.' : forces[i] > 0 ? 'R' : 'L');
        }
        return sb.toString();
    }
}

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
