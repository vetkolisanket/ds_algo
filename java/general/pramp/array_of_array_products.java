/*
Array of Array Products

Given an array of integers arr, you’re asked to calculate for each index i the product of all integers except the integer at that index (i.e. except arr[i]). Implement a function arrayOfArrayProducts that takes an array of integers and returns an array of the products.

Solve without using division and analyze your solution’s time and space complexities.

Examples:

input:  arr = [8, 10, 2]
output: [20, 16, 80] # by calculating: [10*2, 8*2, 8*10]

input:  arr = [2, 7, 3, 4]
output: [84, 24, 56, 42] # by calculating: [7*3*4, 2*3*4, 2*7*4, 2*7*3]
Constraints:

[time limit] 5000ms

[input] array.integer arr

0 ≤ arr.length ≤ 20
[output] array.integer

*/

class Solution {
  
  static int[] arrayOfArrayProducts(int[] arr) {
    // your code goes here
    int length = arr.length;
    if (length <= 1) return new int[0];
    int[] res = new int[length];
    Arrays.fill(res, 1);
    int product = 1;
    for (int i=0;i<length;i++) {
      res[i] = product;
      product *= arr[i];
    }
    product = 1;
    for (int i=length-1;i>=0;i--) {
      res[i] *= product;
      product *= arr[i];
    }
    return res;
  }

  public static void main(String[] args) {

  }

}
