/*
1287. Element Appearing More Than 25% In Sorted Array

Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.

 

Example 1:

Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6
Example 2:

Input: arr = [1,1]
Output: 1
 

Constraints:

1 <= arr.length <= 10^4
0 <= arr[i] <= 10^5
*/

//Soln TC O(N) SC O(1)
class Solution {
    public int findSpecialInteger(int[] arr) {
        int diff = arr.length/4 + 1;
        int ans = arr[0];
        for (int i=0;i<=arr.length-diff;i++) {
            if (arr[i] == arr[i+diff-1]) {
                ans = arr[i];
                break;
            }
        }
        return ans;
    }
}
