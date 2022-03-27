/*
Given an matrix of 0's and 1's which are sorted i.e. all 1's come after all 0's find the row with max value and return the no. of 1's in it. In case of a tie return the one with lowest index
*/

class Solution {
    public int[] findMaxRow(int mat[][], int N) {
        // code here
        int i=0,j=N-1;
        int[] ans = new int[2];
        while (i < N && j >= 0) {
            if (mat[i][j] == 1) {
                ans[0] = i;
                while (j >= 0 && mat[i][j] == 1) j--;
            } else i++;
        }
        for (i=0;i<N;i++) {
            if (mat[ans[0]][i] == 1) ans[1]++;
        }
        return ans;
    }
};
