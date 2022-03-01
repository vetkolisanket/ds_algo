/*
Minimum number of jumps

Input :
N = 6
arr = {1, 4, 3, 2, 6, 7}
Output: 2 
Explanation: 
First we jump from the 1st to 2nd element 
and then jump to the last element.

Your task:
You don't need to read input or print anything. Your task is to complete function minJumps() which takes the array arr and it's size N as input parameters and returns the minimum number of jumps. If not possible returns -1.


Expected Time Complexity: O(N)
Expected Space Complexity: O(1)


Constraints:
1 ≤ N ≤ 10^7
0 ≤ arri ≤ 10^7
*/

//An O(n) time and O(1) space soln
class Solution{
    static int minJumps(int[] arr){
        // your code here
        int jumps=0;
        int curMax = 0, curReach = 0;
        for (int i=0;i<arr.length;i++) {
            if (i==arr.length-1) return jumps;
            
            curMax = Math.max(curMax, i+arr[i]);
            
            if (i==curReach) {
                jumps++;
                curReach = curMax;
            }
            
            if (arr[i] == 0 && i == curMax) return -1;
        }
        return jumps;
    }
}

//My O(n^2) time and O(n) space soln
class Solution{
    static int minJumps(int[] arr){
        // your code here
        int[] dp = new int[arr.length];
        Arrays.fill(dp, arr.length+1);
        dp[arr.length-1] = 0;
        for (int i = arr.length-2;i>=0;i--) {
            if (i+arr[i] >= arr.length-1) {
                dp[i] = 1;
            } else {
                int min = arr.length;
                for (int j=i+arr[i];j>i;j--) {
                    min = Math.min(min, dp[j]);
                }
                dp[i] = min+1;
            }
        }
        return dp[0] == arr.length+1 ? -1 : dp[0];
    }
}
