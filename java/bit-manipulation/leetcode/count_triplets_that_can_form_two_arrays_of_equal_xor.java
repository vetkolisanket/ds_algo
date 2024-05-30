/*
1442. Count Triplets That Can Form Two Arrays of Equal XOR

Given an array of integers arr.

We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).

Let's define a and b as follows:

a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
Note that ^ denotes the bitwise-xor operation.

Return the number of triplets (i, j and k) Where a == b.

 

Example 1:

Input: arr = [2,3,1,6,7]
Output: 4
Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)
Example 2:

Input: arr = [1,1,1,1,1]
Output: 10
 

Constraints:

1 <= arr.length <= 300
1 <= arr[i] <= 10^8

*/

//My soln TC O(N^3) SC O(N)
class Solution {
    public int countTriplets(int[] arr) {
        int n = arr.length, ans = 0;
        int[] xorArr = new int[n];
        xorArr[0] = arr[0];
        for (int i=1;i<n;i++) {
            xorArr[i] = xorArr[i-1] ^ arr[i];
        }
        for (int i=0;i<n;i++) {
            for (int j=i+1;j<n;j++) {
                for (int k=j;k<n;k++) {
                    int a = i == 0 ? xorArr[j-1] : xorArr[j-1] ^ xorArr[i-1];
                    int b = xorArr[k] ^ xorArr[j-1];
                    if (a == b) ans++;
                }
            }
        }
        return ans;
    }
}

//O(N^2) TC and O(N) soln
class Solution {
    public int countTriplets(int[] arr) {
        int count = 0;
        int[] prefixXor = new int[arr.length+1];
        for (int i=1;i<prefixXor.length;i++) {
            prefixXor[i] = prefixXor[i-1] ^ arr[i-1];
        }
        for (int start=0;start<prefixXor.length;start++) {
            for (int end=start+1;end<prefixXor.length;end++) {
                if (prefixXor[end] == prefixXor[start]) {
                    count += (end - start - 1);
                }
            }
        }
        return count;
    }
}

//TC O(N) SC O(N) soln
class Solution {
    public int countTriplets(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap(), totalMap = new HashMap();
        countMap.put(0, 1);
        int count = 0, prefix = 0;
        for (int i=0;i<arr.length;i++) {
            prefix ^= arr[i];
            count += countMap.getOrDefault(prefix, 0) * i - 
                totalMap.getOrDefault(prefix, 0);
            countMap.put(prefix, countMap.getOrDefault(prefix, 0) + 1);
            totalMap.put(prefix, totalMap.getOrDefault(prefix, 0) + i + 1);
        }
        return count;
    }
}
