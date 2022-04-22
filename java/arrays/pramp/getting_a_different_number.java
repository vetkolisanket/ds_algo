/*
Getting a Different Number

Given an array arr of unique nonnegative integers, implement a function getDifferentNumber that finds the smallest nonnegative integer that is NOT in the array.

Even if your programming language of choice doesn’t have that restriction (like Python), assume that the maximum value an integer can have is MAX_INT = 2^31-1. So, for instance, the operation MAX_INT + 1 would be undefined in our case.

Your algorithm should be efficient, both from a time and a space complexity perspectives.

Solve first for the case when you’re NOT allowed to modify the input arr. If successful and still have time, see if you can come up with an algorithm with an improved space complexity when modifying arr is allowed. Do so without trading off the time complexity.

Analyze the time and space complexities of your algorithm.

Example:

input:  arr = [0, 1, 2, 3]

output: 4 
Constraints:

[time limit] 5000ms

[input] array.integer arr

1 ≤ arr.length ≤ MAX_INT
0 ≤ arr[i] ≤ MAX_INT for every i, 0 ≤ i < MAX_INT
*/

//A slightly better soln
static int getDifferentNumber(int[] arr) {
    // your code goes here
    int n = arr.length;
    for (int i=0;i<n;i++) {
      int temp = arr[i];
      while (temp < n && arr[temp] != temp) {
        int temp2 = arr[temp];
        arr[temp] = temp;
        temp = temp2;
      }
    }
    for (int i=0;i<n;i++) {
      if (arr[i] != i) {
        return i;
      }
    }
    return n;
}

//My soln after a lot of help from the interviewer and committing interview costing mistakes
import java.io.*;
import java.util.*;

class Solution {

  static int getDifferentNumber(int[] arr) {
    // your code goes here
    int n = arr.length;
    for (int i=0;i<n;i++) {
      arr[i] += 1;
    }
    for (int i=0;i<n;i++) {
      if (Math.abs(arr[i])-1 < n) {
        arr[Math.abs(arr[i])-1] *= -1;
      }
    }
    for (int i=0;i<n;i++) {
      if (arr[i] > 0) {
        return i;
      }
    }
    return n;
  }

  public static void main(String[] args) {

  }

}
