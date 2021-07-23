/*
13. Roman to Integer

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.

 

Example 1:

Input: s = "III"
Output: 3
Example 2:

Input: s = "IV"
Output: 4
Example 3:

Input: s = "IX"
Output: 9
Example 4:

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 5:

Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 

Constraints:

1 <= s.length <= 15
s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
It is guaranteed that s is a valid roman numeral in the range [1, 3999].
*/

class Solution {
    public int romanToInt(String s) {
        int val = 0;
        int length = s.length();
        for(int i=0;i<length;i++) {
            char c = s.charAt(i);
            if(c == 'I') {
                if(i+1 < length) {
                    char next = s.charAt(i+1);
                    if(next == 'V') {
                        val += 4;
                        i++;
                    } else if (next == 'X') {
                        val += 9;
                        i++;
                    } else val++;
                } else val++;
            } else if (c == 'V') {
                val +=5;
            } else if(c == 'X') {
                if(i+1 < length) {
                    char next = s.charAt(i+1);
                    if(next == 'L') {
                        val += 40;
                        i++;
                    } else if (next == 'C') {
                        val += 90;
                        i++;
                    } else val += 10;
                } else val += 10;
            } else if (c == 'L') {
                val +=50;
            } else if(c == 'C') {
                if(i+1 < length) {
                    char next = s.charAt(i+1);
                    if(next == 'D') {
                        val += 400;
                        i++;
                    } else if (next == 'M') {
                        val += 900;
                        i++;
                    } else val += 100;
                } else val += 100;
            } else if (c == 'D') {
                val +=500;
            } else if (c == 'M') val += 1000;
        }
        return val;
    }
}

/*
Another approach

 public int romanToInt(String s) {
    int nums[]=new int[s.length()];
    for(int i=0;i<s.length();i++){
        switch (s.charAt(i)){
            case 'M':
                nums[i]=1000;
                break;
            case 'D':
                nums[i]=500;
                break;
            case 'C':
                nums[i]=100;
                break;
            case 'L':
                nums[i]=50;
                break;
            case 'X' :
                nums[i]=10;
                break;
            case 'V':
                nums[i]=5;
                break;
            case 'I':
                nums[i]=1;
                break;
        }
    }
    int sum=0;
    for(int i=0;i<nums.length-1;i++){
        if(nums[i]<nums[i+1])
            sum-=nums[i];
        else
            sum+=nums[i];
    }
    return sum+nums[nums.length-1];
}

public static int romanToInt(String s) {
	if (s == null || s.length() == 0)
		return -1;
	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	map.put('I', 1);
	map.put('V', 5);
	map.put('X', 10);
	map.put('L', 50);
	map.put('C', 100);
	map.put('D', 500);
	map.put('M', 1000);
	int len = s.length(), result = map.get(s.charAt(len - 1));
	for (int i = len - 2; i >= 0; i--) {
		if (map.get(s.charAt(i)) >= map.get(s.charAt(i + 1)))
			result += map.get(s.charAt(i));
		else
			result -= map.get(s.charAt(i));
	}
	return result;
}
*/
