/*
1502. Can Make Arithmetic Progression From Sequence

A sequence of numbers is called an arithmetic progression if the difference between any two consecutive elements is the same.

Given an array of numbers arr, return true if the array can be rearranged to form an arithmetic progression. Otherwise, return false.

 

Example 1:

Input: arr = [3,5,1]
Output: true
Explanation: We can reorder the elements as [1,3,5] or [5,3,1] with differences 2 and -2 respectively, between each consecutive elements.
Example 2:

Input: arr = [1,2,4]
Output: false
Explanation: There is no way to reorder the elements to obtain an arithmetic progression.
 

Constraints:

2 <= arr.length <= 1000
-10^6 <= arr[i] <= 10^6

*/

//Soln by sorting the array TC O(NlogN) SC O(logN)
class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int diff = arr[1] - arr[0];
        for (int i=2;i<arr.length;i++) {
            if (diff != arr[i]-arr[i-1]) return false;
        }
        return true;
    }
}

//Better TC soln TC O(N) SC O(N)
class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        int min = Arrays.stream(arr).min().getAsInt();
        int max = Arrays.stream(arr).max().getAsInt();

        if (min == max) return true;

        int n = arr.length;
        if ((max-min)%(n-1) != 0) return false;
        int diff = (max-min)/(n-1);
        Set<Integer> set = new HashSet();
        for (int i=0;i<n;i++) {
            if ((arr[i] - min)%diff != 0) return false;
            set.add(arr[i]);
        }
        return set.size() == n;
    }
}

//In-place O(N) soln TC O(N) SC O(1)
class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        int min = Arrays.stream(arr).min().getAsInt();
        int max = Arrays.stream(arr).max().getAsInt();

        if (min == max) return true;

        int n = arr.length;
        if ((max-min)%(n-1) != 0) return false;
        int diff = (max-min)/(n-1);
        int i=0;
        while (i < n) {
            if ((arr[i]-min)%diff != 0) {
                return false;
            } else if ((arr[i]-min)/diff == i) {
                i++;
            } else {
                int j = (arr[i]-min)/diff;
                if (arr[j] == arr[i]) return false;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        return true;
    }
}
