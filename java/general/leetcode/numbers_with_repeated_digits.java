/*
1012. Numbers With Repeated Digits

Given an integer n, return the number of positive integers in the range [1, n] that have at least one repeated digit.

 

Example 1:

Input: n = 20
Output: 1
Explanation: The only positive number (<= 20) with at least 1 repeated digit is 11.
Example 2:

Input: n = 100
Output: 10
Explanation: The positive numbers (<= 100) with atleast 1 repeated digit are 11, 22, 33, 44, 55, 66, 77, 88, 99, and 100.
Example 3:

Input: n = 1000
Output: 262
 

Constraints:

1 <= n <= 10^9
*/

//TC O(log(n)) SC O(1)
class Solution {
    public int numDupDigitsAtMostN(int n) {
        /*We are taking n+1, so as to compute all dups less than n+1 i.e. to 
        cover n as well. We have converted it into string so as to compute no. 
        of digits in n*/
        String s = String.valueOf(n+1);
        int len = s.length();
        /*Variable to store no. of ints between 1 and n having unique digits*/
        int unique = 0;
        
        /*Loop to compute unique digit num count, where no. of digits in num 
        is less than no. of digits in n. i.e. if n = 3456, this loop will 
        count no. of unique digit nums in range 1-999. i.e. if 
        numDigits(n) = d, this loop will count unique digit nums in range 
        1-maxValForDigit(d-1)*/
        for (int i=1;i<len;i++) {
            unique += getCount(i);
        }
        
        /*Set to store already processed digits*/
        Set<Integer> set = new HashSet();
        /*Loop which counts no. of unique digit nums having no. of digits 
        equal to n*/
        for (int i=0;i<len;i++) {
            int digit = s.charAt(i) - '0';
            /*Function which counts no. of digits possible for a particular index*/
            int temp = getUniqueDigits(set, digit, i==0);
            /*Loop which computes no. of unique digit nums possible for 
            a particular index*/
            for (int j=i+1;j<len;j++) {
                temp *= (10-j);
            }
            unique += temp;
            /*If the set already contains the current digit, all further 
            digits will contain duplicates so we break the loop*/
            if (!set.add(digit)) break;
        }
        /*Our answer is total no. of nums - total no. of nums having unique digits*/
        return n-unique;
    }
    
    /*Function which computes no. of unique digit nums of num length. i.e. 
    if num = 1, ans = 9, if num = 2 ans = 9*9, if num = 3, ans = 9*9*8 
    param: num -> the no. of digits in the num range for which we want to 
    compute unique digits nums i.e. num = 2 -> range 10-99
    */
    private int getCount(int num) {
        int res = 9;
        for (int i=1;i<num;i++) {
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
    private int getUniqueDigits(Set<Integer> set, int digit, boolean first) {
        int res = 0;
        for (int i = first ? 1 : 0; i < digit; ++i) {
            if (!set.contains(i)) res++;
        }
        return res;
    }
}

//Another soln which 
public int numDupDigitsAtMostN(int N) {
        // Transform N + 1 to arrayList
        ArrayList<Integer> L = new ArrayList<Integer>();
        for (int x = N + 1; x > 0; x /= 10)
            L.add(0, x % 10);

        // Count the number with digits < N
        int res = 0, n = L.size();
        for (int i = 1; i < n; ++i)
            res += 9 * A(9, i - 1);

        // Count the number with same prefix
        HashSet<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i > 0 ? 0 : 1; j < L.get(i); ++j)
                if (!seen.contains(j))
                    res += A(9 - i, n - i - 1);
            if (seen.contains(L.get(i))) break;
            seen.add(L.get(i));
        }
        return N - res;
    }


    public int A(int m, int n) {
        return n == 0 ? 1 : A(m, n - 1) * (m - n + 1);
    }
