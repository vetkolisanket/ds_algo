/*
Stock buy and sell

Example 1:

Input:
N = 7
A[] = {100,180,260,310,40,535,695}
Output:
1
Explanation:
One possible solution is (0 3) (4 6)
We can buy stock on day 0,
and sell it on 3rd day, which will 
give us maximum profit. Now, we buy 
stock on day 4 and sell it on day 6.
Example 2:

Input:
N = 5
A[] = {4,2,2,2,4}
Output:
1
Explanation:
There are multiple possible solutions.
one of them is (3 4)
We can buy stock on day 3,
and sell it on 4th day, which will 
give us maximum profit.

Your Task:
The task is to complete the function stockBuySell() which takes an array A[] and N as input parameters and finds the days of buying and selling stock. The function must return a 2D list of integers containing all the buy-sell pairs. If there is No Profit, return an empty list.

 

Note: Since there can be multiple solutions, the driver code will return 1 if your answer is correct, otherwise, it will return 0. In case there's no profit the driver code will return the string "No Profit" for a correct solution.


Expected Time Complexity: O(N)
Expected Auxiliary Space: O(N)


Constraints:
2 ≤ N ≤ 10^6
0 ≤ A[i] ≤ 10^6
*/

class Solution{
    //Function to find the days of buying and selling stock for max profit.
    ArrayList<ArrayList<Integer> > stockBuySell(int A[], int n) {
        // code here
        ArrayList<ArrayList<Integer>> list = new ArrayList();
        for (int i=0;i<n;i++) {
            int buy = -1;
            while (i<n-1 && A[i] >= A[i+1]) i++;
            buy = i;
            int sell = -1;
            while (i < n-1 && A[i] < A[i+1]) i++;
            sell = i;
            if (buy != sell) {
                ArrayList<Integer> pair = new ArrayList();
                pair.add(buy);
                pair.add(sell);
                list.add(pair);
            }
        }
        return list;
    }
}
