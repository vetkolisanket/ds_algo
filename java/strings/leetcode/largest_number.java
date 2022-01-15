/*
179. Largest Number

Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.

Since the result may be very large, so you need to return a string instead of an integer.

 

Example 1:

Input: nums = [10,2]
Output: "210"
Example 2:

Input: nums = [3,30,34,5,9]
Output: "9534330"
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 10^9
*/

class Solution {
    public String largestNumber(int[] nums) {
        if (nums.length == 1) return String.valueOf(nums[0]);
        
        String[] s_nums = new String[nums.length];
        for (int i=0;i<nums.length;i++) {
            s_nums[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(s_nums, (s1, s2) -> (s2+s1).compareTo(s1+s2));
        if (s_nums[0].charAt(0) == '0') return "0";
        
        StringBuilder sb = new StringBuilder();
        for (String s : s_nums) {
            sb.append(s);
        }
        return sb.toString();
    }
}

//Using StringBuilder inside comparator lambda
class Solution {
    public String largestNumber(int[] nums) {
        if (nums.length == 1) return String.valueOf(nums[0]);
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        
        String[] s_nums = new String[nums.length];
        for (int i=0;i<nums.length;i++) {
            s_nums[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(s_nums, (s1, s2) -> {
            sb1.setLength(0);
            sb1.append(s1).append(s2);
            sb2.setLength(0);
            sb2.append(s2).append(s1);
            return sb2.compareTo(sb1);
            }
                );
        if (s_nums[0].charAt(0) == '0') return "0";
        
        StringBuilder sb = new StringBuilder();
        for (String s : s_nums) {
            sb.append(s);
        }
        return sb.toString();
    }
}
