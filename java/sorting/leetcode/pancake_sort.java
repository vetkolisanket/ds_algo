/*
Pancake Sort

Given an array of integers arr:

Write a function flip(arr, k) that reverses the order of the first k elements in the array arr.
Write a function pancakeSort(arr) that sorts and returns the input array. You are allowed to use only the function flip you wrote in the first step in order to make changes in the array.
Example:

input:  arr = [1, 5, 4, 3, 2]

output: [1, 2, 3, 4, 5] # to clarify, this is pancakeSort's output
Analyze the time and space complexities of your solution.

Note: it’s called pancake sort because it resembles sorting pancakes on a plate with a spatula, where you can only use the spatula to flip some of the top pancakes in the plate. To read more about the problem, see the Pancake Sorting Wikipedia page.

Constraints:

[time limit] 5000ms

[input] array.integer arr

[input] integer k

0 ≤ k
[output] array.integer
*/

//TC O(n^2) SC O(1)

import java.io.*;
import java.util.*;

class Solution {

  static int[] pancakeSort(int[] arr) {
    // your code goes here
    int n = arr.length-1;
    while (n >= 0) {
      int index = getLargestNumIndex(arr, n);
      flip(arr, index);
      flip(arr, n);
      n--;
    }
    return arr;
  }
  
    
    
  private static int getLargestNumIndex(int[] arr, int k) {
    int index = 0, val = arr[0];
    for (int i=1; i<= k; i++) {
      if (arr[i] > val) {
        val = arr[i];
        index = i;
      }
    }
    return index;
  }
    

  public static void main(String[] args) {

  }
  
  public static void flip(int[] arr, int k) {
    int lo = 0, hi = k;
    while (lo < hi) {
      int temp = arr[lo];
      arr[lo] = arr[hi];
      arr[hi] = temp;
      lo++;
      hi--;
    }
  }
  


}
