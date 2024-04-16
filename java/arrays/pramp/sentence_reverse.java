/*
Sentence Reverse

You are given an array of characters arr that consists of sequences of characters separated by space characters. Each space-delimited sequence of characters defines a word.

Implement a function reverseWords that reverses the order of the words in the array in the most efficient manner.

Explain your solution and analyze its time and space complexities.

Example:

input:  arr = [ 'p', 'e', 'r', 'f', 'e', 'c', 't', '  ',
                'm', 'a', 'k', 'e', 's', '  ',
                'p', 'r', 'a', 'c', 't', 'i', 'c', 'e' ]

output: [ 'p', 'r', 'a', 'c', 't', 'i', 'c', 'e', '  ',
          'm', 'a', 'k', 'e', 's', '  ',
          'p', 'e', 'r', 'f', 'e', 'c', 't' ]
Constraints:

[time limit] 5000ms

[input] array.character arr

0 ≤ arr.length ≤ 100
[output] array.character


*/

//Soln TC O(N) SC O(1)
import java.io.*;
import java.util.*;

class Solution {

  static char[] reverseWords(char[] arr) {
    // your code goes here
    int len = arr.length;
    swap(arr, 0, len-1);
    int start = 0;
    while (start < len) {
      int end = start;
      for (; end < len && arr[end] != ' '; end++);
      swap(arr, start, end-1);
      while (end < len && arr[end] == ' ') {
        end++;
      }
      start = end;
    }
    return arr;
  }
  
  private static void swap(char[] arr, int start, int end) {
    while (start < end) {
      char c = arr[start];
      arr[start++] = arr[end];
      arr[end--] = c;
    }
  }

  public static void main(String[] args) {
      char[] arr = new char[] { 'p', 'e', 'r', 'f', 'e', 'c', 't', ' ', 'm', 'a', 'k', 'e', 's', ' ', 'p', 'r', 'a', 'c', 't', 'i', 'c', 'e' };
        System.out.println(reverseWords(arr));
  }

}

/*
/
  0                len-1
   a b c   ' ' d e f
-> f e d<- ' ' c b a
   d e f   ' ' a b c
   
   
   swap (0, len-1)
   
 
 
 
 
 



['a', 'b', 'c', ' ', 'd', 'e', 'f']
e = 6, ' ', s = 4
[]
['d', 'e', 'f', ' ', 'a', 'b', 'c']
*/
