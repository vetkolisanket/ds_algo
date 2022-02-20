/*

Input:
N=7 M=3
arr[] = { 2 ,1 ,3 ,5 ,0 ,1 ,4 }
Output: 9
Explanation: 
She can start from tree 1 and move
to tree2 and then to tree 3.
Hence, total number of gathered fruits
= 1 + 3 + 5 = 9.


Input:
N=6 M=2
arr[] = { 1, 6, 2, 5, 3, 4 }
Output: 8
Explanation: 
She can start from tree 1 and move 
to tree 2. In this case she will gather
6 + 2 = 8 fruits. She can also start 
from tree 3 and move to tree 4. In this
case, too, she will gather 5 + 3 = 8 
fruits.
*/

class Solution
{
    long maxFruits(long arr[] ,int n,int m)
    {
        long curSum = 0, maxSum = 0;
        for (int i=0;i<m;i++) {
            curSum += arr[i];
        }
        maxSum = curSum;
        for (int start=0,end=m;start<n;start++, end++) {
            curSum = curSum-arr[start]+arr[end%n];
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }
}
