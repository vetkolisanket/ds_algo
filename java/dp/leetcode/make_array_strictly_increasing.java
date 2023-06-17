/*
1187. Make Array Strictly Increasing

Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.

In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].

If there is no way to make arr1 strictly increasing, return -1.

 

Example 1:

Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
Output: 1
Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
Example 2:

Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
Output: 2
Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].
Example 3:

Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
Output: -1
Explanation: You can't make arr1 strictly increasing.
 

Constraints:

1 <= arr1.length, arr2.length <= 2000
0 <= arr1[i], arr2[i] <= 10^9

*/

//Soln using DP TC O(M*N*logN) SC O(M*N) where M,N are the length of the arrays arr1 and arr2 respectively
class Solution {

    private Map<Pair<Integer, Integer>, Integer> dp = new HashMap();

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);

        int answer = dfs(0, -1, arr1, arr2);
        return answer == 2_001 ? -1 : answer;
    }

    private int dfs(int i, int prev, int[] arr1, int[] arr2) {
        if (i == arr1.length) return 0;
        Pair<Integer, Integer> key = new Pair(i, prev);
        if (dp.containsKey(key)) return dp.get(key);
        int cost = 2001;
        if (arr1[i] > prev) {
            cost = dfs(i+1, arr1[i], arr1, arr2);
        }

        int idx = bisectRight(arr2, prev);

        if (idx < arr2.length) {
            cost = Math.min(cost, 1 + dfs(i+1, arr2[idx], arr1, arr2));
        }
        dp.put(key, cost);
        return cost;
    }

    private int bisectRight(int[] arr, int val) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] <= val) {
                left = mid+1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
