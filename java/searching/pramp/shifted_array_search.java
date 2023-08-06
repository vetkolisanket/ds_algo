/*
Shifted Array Search

A sorted array of distinct integers shiftArr is shifted to the left by an unknown offset and you don’t have a pre-shifted copy of it. For instance, the sequence 1, 2, 3, 4, 5 becomes 3, 4, 5, 1, 2, after shifting it twice to the left.

Given shiftArr and an integer num, implement a function shiftedArrSearch that finds and returns the index of num in shiftArr. If num isn’t in shiftArr, return -1. Assume that the offset can be any value between 0 and arr.length - 1.

Explain your solution and analyze its time and space complexities.

Example:

input:  shiftArr = [9, 12, 17, 2, 4, 5], num = 2 # shiftArr is the
                                                 # outcome of shifting
                                                 # [2, 4, 5, 9, 12, 17]
                                                 # three times to the left

output: 3 # since it’s the index of 2 in arr
Constraints:

[time limit] 5000ms
[input] array.integer arr
[input] integer num
[output] integer
*/

//Soln using altered binary search TC O(logN) SC O(1)
import java.io.*;
import java.util.*;

class Solution {

  static int shiftedArrSearch(int[] shiftArr, int num) {
    // your code goes here
    int start = 0, end = shiftArr.length-1;
    while (start <= end) {
      int mid = start + (end-start)/2;
      if (shiftArr[mid] == num) return mid;
      else if (shiftArr[start] < shiftArr[mid]) {
        if (num >= shiftArr[start] && num < shiftArr[mid]) {
          end = mid-1;
        } else start = mid+1;
      } else {
        if (num > shiftArr[mid] && num <= shiftArr[end]) {
          start = mid+1;
        } else end = mid-1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {

  }

}
