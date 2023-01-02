/*
2523. Closest Prime Numbers in Range

Given two positive integers left and right, find the two integers num1 and num2 such that:

left <= nums1 < nums2 <= right .
nums1 and nums2 are both prime numbers.
nums2 - nums1 is the minimum amongst all other pairs satisfying the above conditions.
Return the positive integer array ans = [nums1, nums2]. If there are multiple pairs satisfying these conditions, return the one with the minimum nums1 value or [-1, -1] if such numbers do not exist.

A number greater than 1 is called prime if it is only divisible by 1 and itself.

 

Example 1:

Input: left = 10, right = 19
Output: [11,13]
Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
Since 11 is smaller than 17, we return the first pair.
Example 2:

Input: left = 4, right = 6
Output: [-1,-1]
Explanation: There exists only one prime number in the given range, so the conditions cannot be satisfied.
 

Constraints:

1 <= left <= right <= 10^6
*/

//Soln
class Solution {
    public int[] closestPrimes(int left, int right) {
        List<Integer> primes = getPrimes();
        int[] res = new int[]{-1, -1};
        for (int i=1;i<primes.size();i++) {
            int num1 = primes.get(i-1);
            int num2 = primes.get(i);
            if (num1 >= left && num2 <= right && (res[0] < 0 || num2-num1 < res[1] - res[0])) {
                res[0] = num1;
                res[1] = num2;
            }
        }
        return res;
    }

    private List<Integer> getPrimes() {
        List<Integer> list = new ArrayList();
        boolean[] arr = new boolean[1000000];
        for (int i=2;i<arr.length;i++) {
            if (!arr[i]) {
                list.add(i);
                for (int j=i;j<arr.length;j += i) {
                    arr[j] = true;
                }
            }
        }
        return list;
    }
}
