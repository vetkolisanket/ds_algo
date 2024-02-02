/*
1291. Sequential Digits

An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

 

Example 1:

Input: low = 100, high = 300
Output: [123,234]
Example 2:

Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]
 

Constraints:

10 <= low <= high <= 10^9
*/

//Soln using sliding window TC O(1) SC O(1)
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> list = new ArrayList<>();
        String sequence = "123456789";
        int lowLen = String.valueOf(low).length();
        int highLen = String.valueOf(high).length();
        for (int i=lowLen;i<=highLen;i++) {
            for (int j=0;j<=9-i;j++) {
                String subString = sequence.substring(j, j+i);
                int val = Integer.parseInt(subString);
                if (val >= low && val <= high) {
                    list.add(val);
                }
            }
        }
        return list;
    }
}
