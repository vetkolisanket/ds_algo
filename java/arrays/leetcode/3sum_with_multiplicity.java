/*
923. 3Sum With Multiplicity

Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.

As the answer can be very large, return it modulo 109 + 7.

 

Example 1:

Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20
Explanation: 
Enumerating by the values (arr[i], arr[j], arr[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.
Example 2:

Input: arr = [1,1,2,2,2,2], target = 5
Output: 12
Explanation: 
arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
We choose one 1 from [1,1] in 2 ways,
and two 2s from [2,2,2,2] in 6 ways.
 

Constraints:

3 <= arr.length <= 3000
0 <= arr[i] <= 100
0 <= target <= 300
*/

class Solution {
    public int threeSumMulti(int[] arr, int target) {
        long[] count = new long[101];
        for (int num: arr) count[num]++;
        long res = 0;
        for (int i=0;i<=100;i++) {
            for (int j=i;j<=100;j++) {
                int k = target - i - j;
                if (k > 100 || k < 0) continue;
                else if (i == j && j == k) {
                    res += count[i]*(count[i]-1)*(count[i]-2)/6;
                } else if (i == j && j != k) {
                    res += count[i]*(count[i]-1)/2*count[k];
                } else if (j < k) {
                    res += count[i]*count[j]*count[k];
                }
            }
        }
        return (int) (res % (1e9 + 7));
    }
}
