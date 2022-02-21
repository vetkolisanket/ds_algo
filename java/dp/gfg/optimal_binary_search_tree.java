/*
Optimal binary search tree

Given a sorted array keys[0.. n-1] of search keys and an array freq[0.. n-1] of frequency counts, where freq[i] is the number of searches to keys[i]. Construct a binary search tree of all keys such that the total cost of all the searches is as small as possible.
Let us first define the cost of a BST. The cost of a BST node is level of that node multiplied by its frequency. Level of root is 1.


Example 1:

Input:
n = 2
keys = {10, 12}
freq = {34, 50}
Output: 118
Explaination:
There can be following two possible BSTs 
        10                       12
          \                     / 
           12                 10
                              
The cost of tree I is 34*1 + 50*2 = 134
The cost of tree II is 50*1 + 34*2 = 118 

Example 2:


Input:
N = 3
keys = {10, 12, 20}
freq = {34, 8, 50}
Output: 142
Explaination: There can be many possible BSTs
     20
    /
   10  
    \
     12  
     
Among all possible BSTs, 
cost of this BST is minimum.  
Cost of this BST is 1*50 + 2*34 + 3*8 = 142

Your Task:
You don't need to read input or print anything. Your task is to complete the function optimalSearchTree() which takes the array keys[], freq[] and their size n as input parameters and returns the total cost of all the searches is as small as possible.


Expected Time Complexity: O(n^3)
Expected Auxiliary Space: O(n^2)


Constraints:
1 ≤ N ≤ 100
*/

class Solution
{
    static int optimalSearchTree(int keys[], int freq[], int n)
    {
        // code here
        int[][] dp = new int[n][n];
        int[] psa = new int[n];
        psa[0] = freq[0];
        for (int i=1;i<n;i++) {
            psa[i] = freq[i] + psa[i-1];
        }
        
        for (int g=0;g<n;g++) {
            for (int i=0,j=g;j<n;j++) {
                if (g == 0) {
                    dp[i][j] = freq[i];
                } else if (g == 1) {
                    int fi = freq[i];
                    int fj = freq[j];
                    dp[i][j] = Math.min(fi+2*fj, 2*fi+fj);
                } else {
                    int min = Integer.MAX_VALUE;
                    int fs = psa[j] - (i==0 ? 0 : psa[i-1]);
                    
                    for (int k=i;k<=j;k++) {
                        int left = k==i ? 0 : dp[i][k-1];
                        int right = k==j ? 0 : dp[k+1][j];
                        min = Math.min(min, left+right+fs);
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][n-1];
    }
}
