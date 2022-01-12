/*
166. Fraction to Recurring Decimal

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

If multiple answers are possible, return any of them.

It is guaranteed that the length of the answer string is less than 104 for all the given inputs.

 

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 4, denominator = 333
Output: "0.(012)"
 

Constraints:

-2^31 <= numerator, denominator <= 2^31 - 1
denominator != 0
*/

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder res = new StringBuilder();
        if ((numerator > 0) ^ (denominator > 0)) res.append("-");
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        res.append(num/den);
        num %= den;
        if (num == 0) return res.toString();
        
        res.append(".");
        Map<Long, Integer> map = new HashMap();
        map.put(num, res.length());
        while (num != 0) {
            num *= 10;
            res.append(num/den);
            num %= den;
            if (map.containsKey(num)) {
                int idx = map.get(num);
                res.insert(idx, "(");
                res.append(")");
                break;
            } else map.put(num, res.length());
        }
        return res.toString();
    }
}
