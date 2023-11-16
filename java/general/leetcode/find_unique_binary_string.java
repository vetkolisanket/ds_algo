/*
1980. Find Unique Binary String

Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums. If there are multiple answers, you may return any of them.

 

Example 1:

Input: nums = ["01","10"]
Output: "11"
Explanation: "11" does not appear in nums. "00" would also be correct.
Example 2:

Input: nums = ["00","01"]
Output: "11"
Explanation: "11" does not appear in nums. "10" would also be correct.
Example 3:

Input: nums = ["111","011","001"]
Output: "101"
Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
 

Constraints:

n == nums.length
1 <= n <= 16
nums[i].length == n
nums[i] is either '0' or '1'.
All the strings of nums are unique.
*/

//Soln using Cantor's Diagonal Argument TC O(N) SC O(1)
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<nums.length;i++) {
            char c = nums[i].charAt(i);
            sb.append(c == '0' ? "1" : "0");
        }
        return sb.toString();
    }
}

//Soln using recursion TC O(N^2) SC O(N)
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        Set<String> set = new HashSet();
        for (String num: nums) {
            set.add(num);
        }
        return findString("", set);
    }

    private String findString(String s, Set<String> set) {
        if (s.length() == set.size()) {
            if (set.contains(s)) {
                return "";
            } else {
                return s;
            }
        }
        String addZero = findString(s+"0", set);
        if (addZero.length() == 0) {
            return findString(s+"1", set);
        }
        return addZero;
    }
}


