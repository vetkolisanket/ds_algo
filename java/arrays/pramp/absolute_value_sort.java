/*
Absolute Value Sort

Given an array of integers arr, write a function absSort(arr), that sorts the array according to the absolute values of the numbers in arr. If two numbers have the same absolute value, sort them according to sign, where the negative numbers come before the positive numbers.

Examples:

input:  arr = [2, -7, -2, -2, 0]
output: [0, -2, -2, 2, -7]
Constraints:

[time limit] 5000ms
[input] array.integer arr
0 ≤ arr.length ≤ 10
[output] array.integer
*/

import java.io.*;
import java.util.*;

class Solution {

    static int[] absSort(int[] arr) {
        // your code goes here
        Arrays.sort(arr);
        int i=0,j=arr.length-1;
        int[] res = new int[arr.length];
        int k = arr.length-1;
        while (k >= 0) {
            if (Math.abs(arr[i]) > Math.abs(arr[j])) {
                res[k--] = arr[i++];
            } else {
                res[k--] = arr[j--];
            }
        }
        return res;
    }

    public static void main(String[] args) {
	
    }
}
