/*
Given an array of positive integers arr, calculate the sum of all possible odd-length subarrays.

A subarray is a contiguous subsequence of the array.

Return the sum of all odd-length subarrays of arr.

 

Example 1:

Input: arr = [1,4,2,5,3]
Output: 58
Explanation: The odd-length subarrays of arr and their sums are:
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
Example 2:

Input: arr = [1,2]
Output: 3
Explanation: There are only 2 subarrays of odd length, [1] and [2]. Their sum is 3.
Example 3:

Input: arr = [10,11,12]
Output: 66
 

Constraints:

1 <= arr.length <= 100
1 <= arr[i] <= 1000

*/

class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int sum=0;
        for(int i=0;i<arr.length;i++) {
            sum += arr[i] * (((i+1)*(arr.length - i) + 1)/2);
        }
        return sum;
    }
}

/*
For each element i of the passed array arr[], the solution to this problem is to sum together all: arr[i] * (number of odd length subarrays that include a[i])

Examples of multipliers for arrays of various lengths are shown below. For the given length of arr[], multiply all arr[i] by the ith number in the coresponding line of the examples below, then sum all products for the solution to the problem. From the example below, if arr.length is 5, then the answer to this problem is: arr[0] * 3 + arr[1] * 4 + arr[2] * 5 + arr[3] * 4 + arr[4] * 3 where the bold numbers are the multipliers from the examples below.

arr.length == 2 : 1 1
arr.length == 3 : 2 2 2
arr.length == 4 : 2 3 3 2
arr.length == 5 : 3 4 5 4 3
arr.length == 6 : 3 5 6 6 5 3
arr.length == 7 : 4 6 8 8 8 6 4
arr.length == 8 : 4 7 9 10 10 9 7 4
arr.length == 9 : 5 8 11 12 13 12 11 8 5
arr.length == 10 : 5 9 12 14 15 15 14 12 9 5
arr.length == 11 : 6 10 14 16 18 18 18 16 14 10 6
arr.length == 12 : 6 11 15 18 20 21 21 20 18 15 11 6

The multipliers in the examples above, can be calculated for index i in arr[] by:
((i + 1) * (arr.length - i) + 1) / 2

The example below graphically shows how the multipliers are related to odd length subarrays for arr.length == 7. The odd length subarrays are shown in square brackets "[--]", for length 1, 3, 5, and 7 subarrays. Within a vertical column in the example below, count the number of subarrays in that column, to get the multiplier shown on the last line of the example.

[-] [-] [-] [-] [-] [-] [-]   subarrays of length 1
[---------]  .   .   .   .    subarrays of length 3
 .  [---------]  .   .   . 
 .   .  [---------]  .   .
 .   .   .  [---------]  .
 .   .   .   .  [---------]
[-----------------]  .   .    subarrays of length 5
 .  [-----------------]  .
 .   .  [-----------------]
[-------------------------]   subarray of length 7
 4   6   8   8   8   6   4    multipliers
*/
