/*
869. Reordered Power of 2

You are given an integer n. We reorder the digits in any order (including the original order) such that the leading digit is not zero.

Return true if and only if we can do this so that the resulting number is a power of two.

 

Example 1:

Input: n = 1
Output: true
Example 2:

Input: n = 10
Output: false
 

Constraints:

1 <= n <= 10^9
*/

//My soln, which complexities as per me are TC O(logN*log(log(N))) and SC O(logN)
class Solution {
    
    class Power {
        
        Set<String> set;
        
        public Power() {
            set = new HashSet();
            long i = 1;
            while (i <= 1e9) {
                String s = String.valueOf(i);
                char[] arr = s.toCharArray();
                Arrays.sort(arr);
                set.add(String.valueOf(arr));
                i *= 2;
            }
        }
        
    }
    
    public boolean reorderedPowerOf2(int n) {
        String s = String.valueOf(n);
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        Power p = new Power();
        return p.set.contains(String.valueOf(arr));
    }
}

//Leetcode soln TC O(log^2N) SC O(logN)
class Solution {
    public boolean reorderedPowerOf2(int N) {
        int[] A = count(N);
        for (int i = 0; i < 31; ++i)
            if (Arrays.equals(A, count(1 << i)))
                return true;
        return false;
    }

    // Returns the count of digits of N
    // Eg. N = 112223334, returns [0,2,3,3,1,0,0,0,0,0]
    public int[] count(int N) {
        int[] ans = new int[10];
        while (N > 0) {
            ans[N % 10]++;
            N /= 10;
        }
        return ans;
    }
}
