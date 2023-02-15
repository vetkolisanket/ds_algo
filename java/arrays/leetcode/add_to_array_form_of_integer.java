/*
989. Add to Array-Form of Integer

The array-form of an integer num is an array representing its digits in left to right order.

For example, for num = 1321, the array form is [1,3,2,1].
Given num, the array-form of an integer, and an integer k, return the array-form of the integer num + k.
*/

//Soln TC O(m+n) SC O(1) where m is the length of the array and n is the no. of digits in k
class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        int i = num.length-1, carry = 0;
        List<Integer> list = new ArrayList();
        while (i >= 0 || k > 0) {
            int val1 = i >= 0 ? num[i--] : 0;
            int val2 = k%10;
            k /= 10;
            int sum = val1 + val2 + carry;
            list.add(0, sum%10);
            carry = sum/10;
        }
        if (carry > 0) list.add(0, carry);
        return list;
    }
}
