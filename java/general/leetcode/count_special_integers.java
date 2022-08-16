/*
2376. Count Special Integers

We call a positive integer special if all of its digits are distinct.

Given a positive integer n, return the number of special integers that belong to the interval [1, n].

 

Example 1:

Input: n = 20
Output: 19
Explanation: All the integers from 1 to 20, except 11, are special. Thus, there are 19 special integers.
Example 2:

Input: n = 5
Output: 5
Explanation: All the integers from 1 to 5 are special.
Example 3:

Input: n = 135
Output: 110
Explanation: There are 110 integers from 1 to 135 that are special.
Some of the integers that are not special are: 22, 114, and 131.
 

Constraints:

1 <= n <= 2 * 10^9
*/

//TC O(log(n)) SC O(1)
class Solution {
    public int countSpecialNumbers(int n) {
        /*We are taking n+1, so as to compute all dups less than n+1 i.e. to 
        cover n as well. We have converted it into string so as to compute no. 
        of digits in n*/
        String s = String.valueOf(n+1);
        int len = s.length();
        int ans = 0;
        
        /*Loop to compute unique digit num count, where no. of digits in num 
        is less than no. of digits in n. i.e. if n = 3456, this loop will 
        count no. of unique digit nums in range 1-999. i.e. if 
        numDigits(n) = d, this loop will count unique digit nums in range 
        1-maxValForDigit(d-1)*/
        for (int i=1;i<len;++i) {
            ans += getUniqueNumsCount(i);
        }
        
        /*Set to store already processed digits*/
        Set<Integer> set = new HashSet();
        /*Loop which counts no. of unique digit nums having no. of digits 
        equal to n*/
        for (int i=0;i<len;++i) {
            /*Function which counts no. of digits possible for a particular index*/
            int digit = s.charAt(i) - '0';
            /*Loop which computes no. of unique digit nums possible for 
            a particular index*/
            int temp = getUniqueDigitsCount(set, digit, i==0);
            for (int j=i+1;j<len;++j) {
                temp *= (10-j);
            }
            
            ans += temp;
            /*If the set already contains the current digit, all further 
            digits will contain duplicates so we break the loop*/
            if (!set.add(digit)) break;
        }
        return ans;
    }
    
    /*Function which computes no. of unique digit nums of num length. i.e. 
    if num = 1, ans = 9, if num = 2 ans = 9*9, if num = 3, ans = 9*9*8 
    param: digits -> the no. of digits in the num range for which we want to 
    compute unique digits nums i.e. num = 2 -> range 10-99
    */
    private int getUniqueNumsCount(int digits) {
        int res = 9;
        for (int i=1;i<digits;++i) {
            res *= (10-i);
        }
        return res;
    }
    
    /*Function which returns the no. of unique digits possible for a given index
    param: set -> Set containing already added digits in previous indices i.e. 
    if num is 8756 and we are at digit index 2, this set will contain 8 and 7
    param: digit -> digit at the current index i.e. if num is 8756 and we are 
    at index 2, digit will contain 5 and we will be looking in range 8700 - 8749
    param: first -> boolean which indicates whether we are on the first index 
    or not. This is needed as for first index num range starts from 1-digit, 
    for all other indices it is from 0-digit
    */
    private int getUniqueDigitsCount(Set<Integer> set, int digit, boolean first) {
        int count = 0;
        for (int i = first ? 1 : 0; i < digit; ++i) {
            if (!set.contains(i)) count++;
        }
        return count;
    }
}
