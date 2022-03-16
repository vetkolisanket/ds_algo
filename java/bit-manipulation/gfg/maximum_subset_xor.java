/*
Maximum subset XOR

Given an array arr[] of N positive integers. Find an integer denoting the maximum XOR subset value in the given array arr[].

Example 1:

Input : 
N = 3
arr[] = {2, 4, 5}
Output : 7
Explanation : 
The subset {2, 5} has maximum 
subset XOR value.
Example 2 :

Input : 
N= 3
arr[] = {9, 8, 5}
Output : 13
Explanation : 
The subset {8, 5} has maximum 
subset XOR value.
Your Task :
You don't need to read input or print anything. Your task is to complete the function maxSubarrayXOR() which takes the array and an integer as input and returns the maximum subset XOR value.
 
Expected Time Complexity : O(N*Log(max(arr[i])))
Expected Auxiliary Space : O(1)
 
Contraints :
1 <= N <= 10^5
1 <= arr[i] <= 10^6
*/

class Solution
{
    
    private static final int INT_BITS = 32;
    
    public static int maxSubarrayXOR(int arr[], int n)
    {
        //add code here.
        //to keep track of the max value index for the iteration i.
        int index = 0;
        for (int i=INT_BITS-1;i>=0;i--) {
            //to keep track of the index of max value for bit at pos i
            int maxIndex = index;
            //to keep track of max element for iteration i
            int maxEle = Integer.MIN_VALUE;
            //iterate from index and above as items from index-1 to 0 are already
            //processed in previous iterations
            for (int j=index;j<n;j++) {
                //if the ith bit of arr[j] is set and it is greater than max
                //element so far, update max element and max index
                if ((arr[j] & (1<<i)) != 0 && arr[j] > maxEle) {
                    maxEle = arr[j];
                    maxIndex = j;
                }
            }
            //If the max element is not updated continue
            if (maxEle == Integer.MIN_VALUE) continue;
            //swap the element at index and maxIndex
            int temp = arr[index];
            arr[index] = arr[maxIndex];
            arr[maxIndex] = temp;
            maxIndex = index;
            //XOR all the elements other than maxIndex element with maxIndex 
            //element whose ith bit is also set just like maxIndex element
            for (int j=0;j<n;j++) {
                if (j != maxIndex && (arr[j] & (1<<i)) != 0) {
                    arr[j] ^= arr[maxIndex];
                }
            }
            //Increment index for the next iteration to store the max element in 
            //the remaining elements
            index++;
        }
        int res = 0;
        //XOR all the elements to find the answer
        for (int i=0;i<n;i++) {
            res ^= arr[i];
        }
        return res;
    }
}
