/*
Count Inversions

Given an array of integers. Find the Inversion Count in the array. 

Inversion Count: For an array, inversion count indicates how far (or close) the array is from being sorted. If array is already sorted then the inversion count is 0. If an array is sorted in the reverse order then the inversion count is the maximum. 
Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j.
 

Example 1:

Input: N = 5, arr[] = {2, 4, 1, 3, 5}
Output: 3
Explanation: The sequence 2, 4, 1, 3, 5 
has three inversions (2, 1), (4, 1), (4, 3).
Example 2:

Input: N = 5
arr[] = {2, 3, 4, 5, 6}
Output: 0
Explanation: As the sequence is already 
sorted so there is no inversion count.
Example 3:

Input: N = 3, arr[] = {10, 10, 10}
Output: 0
Explanation: As all the elements of array 
are same, so there is no inversion count.
Your Task:
You don't need to read input or print anything. Your task is to complete the function inversionCount() which takes the array arr[] and the size of the array as inputs and returns the inversion count of the given array.

Expected Time Complexity: O(NLogN).
Expected Auxiliary Space: O(N).

Constraints:
1 ≤ N ≤ 5*10^5
1 ≤ arr[i] ≤ 10^18
*/

class Solution
{
    // arr[]: Input Array
    // N : Size of the Array arr[]
    //Function to count inversions in the array.
    
    static long inversionCount(long arr[], long N)
    {
        // Your Code Here
        return mergeSortAndCount(arr, 0, (int)N-1);
    }
    
    private static long mergeSortAndCount(long[] arr, int low, int high) {
        long count = 0;
        if (low < high) {
            int mid = low + (high-low)/2;
            count += mergeSortAndCount(arr, low, mid);
            count += mergeSortAndCount(arr, mid+1, high);
            count += mergeAndCount(arr, low, mid, high);
        }
        return count;
    }
    
    private static long mergeAndCount(long[] arr, int low, int mid, int high) {
        long[] left = Arrays.copyOfRange(arr, low, mid+1);
        long[] right = Arrays.copyOfRange(arr, mid+1, high+1);
        int i=0,j=0,k=low;
        long count = 0;
        while (i<left.length && j<right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
                count += (mid+1) - (low+i);
            }
        }
        while (i<left.length) arr[k++] = left[i++];
        while (j<right.length) arr[k++] = right[j++];
        return count;
    }
    
}
