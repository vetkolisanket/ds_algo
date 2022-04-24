/*
Alexa is fond of mathematics. She is solving a maths problem in which she is given an array arr of N integers, and she has to find the rightmost non-zero digit of the resultant number formed after multiplying all the elements of the array. Note: if there is no rightmost non-zero digit, then return -1.
Example 1:
Input:
N = 4
arr = {3, 23, 30, 45}
Output:
5
Explanation:
Product of these numbers is 93150. Rightmost 
non-zero digit is 5.
Example 2:
Input:
N = 5
arr = {1, 2, 3, 4, 5}
Output:
2
Explanation:
Product of these numbers is 120. Rightmost 
non-zero digit is 2.
Your Task:
Your task is to complete the function rightmostNonZeroDigit() which takes an array arr and an integer N as the input parameters  and returns the rightmost non-zero digit in the product of array elements, if there is no right most non zero digit, then return -1. Expected Time Complexity: O(N*log(max(arr))). Expected Auxiliary Space: O(1).
Constraints:

*/

// { Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.io.*;
import java.lang.*;

class GFG{
	public static void main(String [] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		while(test-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int [] arr = new int[N];
			String [] str = br.readLine().trim().split(" ");
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(str[i]);
			}
			Solution obj = new Solution();
			System.out.println(obj.rightmostNonZeroDigit(N, arr));
		}
	}
}// } Driver Code Ends


//User function Template for Java

class Solution{
	int rightmostNonZeroDigit(int N, int [] arr) {
		//Write your code here
		int cnt5=0,cnt2=0,res=1;
		for (int i=0;i<N;i++) {
		    if (arr[i] == 0) return -1;
		    while (arr[i]%5==0) {
		        arr[i] /= 5;
		        cnt5++;
		    }
		    while (arr[i]%2==0) {
		        arr[i] /= 2;
		        cnt2++;
		    }
		    res = (res*arr[i])%10;
		}
		int minCnt = Math.min(cnt5, cnt2);
		cnt5 -= minCnt;
		cnt2 -= minCnt;
		while (cnt5 > 0) {
		    res = (res*5)%10;
		    cnt5--;
		}
		while (cnt2 > 0) {
		    res = (res*2)%10;
		    cnt2--;
		}
		return res;
	}
}
