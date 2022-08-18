/*
1338. Reduce Array Size to The Half

You are given an integer array arr. You can choose a set of integers and remove all the occurrences of these integers in the array.

Return the minimum size of the set so that at least half of the integers of the array are removed.

 

Example 1:

Input: arr = [3,3,3,3,5,5,5,2,2,7]
Output: 2
Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
Possible sets of size 2 are {3,5},{3,2},{5,2}.
Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has a size greater than half of the size of the old array.
Example 2:

Input: arr = [7,7,7,7,7,7]
Output: 1
Explanation: The only possible set you can choose is {7}. This will make the new array empty.
 

Constraints:

2 <= arr.length <= 10^5
arr.length is even.
1 <= arr[i] <= 10^5
*/

//My soln TC O(nlog(n)) SC O(n)
class Solution {
    
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> map = new HashMap();
        for (int num: arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] arr2 = new int[map.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            arr2[i++] = entry.getValue();
        }
        Arrays.sort(arr2);
        int ans = 0;
        int n = arr.length;
        int sum = 0;
        for (i = arr2.length-1; i>= 0; i--) {
            sum += arr2[i];
            ans++;
            if (sum >= n/2) return ans;
        }
        return -1;
    }
}
