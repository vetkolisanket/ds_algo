/*
342. Power of Four

Given an integer n, return true if it is a power of four. Otherwise, return false.

An integer n is a power of four, if there exists an integer x such that n == 4x.

 

Example 1:

Input: n = 16
Output: true
Example 2:

Input: n = 5
Output: false
Example 3:

Input: n = 1
Output: true
 

Constraints:

-2^31 <= n <= 2^31 - 1
 

Follow up: Could you solve it without loops/recursion?
*/

//My soln TC O(log(n)) SC O(1)
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n < 1) return false;
        while (n != 1) {
            if (n%4 != 0) return false;
            n /= 4;
        }
        return true;
    }
}

//A cleaner O(log(n)) soln
class Solution {
  public boolean isPowerOfTwo(int n) {
    if (n == 0) return false;
    while (n % 4 == 0) n /= 4;
    return n == 1;
  }
}

//Brute force + precomputation TC O(1) SC O(1)
class Powers {
  private int n = 15;
  public List<Integer> nums = new ArrayList();
  Powers() {
    int lastNum = 1;
    nums.add(lastNum);
    for (int i = 1; i < n + 1; ++i) {
      lastNum = lastNum * 4;
      nums.add(lastNum);
    }
  }
}

class Solution {
  public static Powers p = new Powers();
  public boolean isPowerOfFour(int num) {
    return p.nums.contains(num);
  }
}

//Math TC O(1) SC O(1)
class Solution {
  public boolean isPowerOfFour(int num) {
    return (num > 0) && (Math.log(num) / Math.log(2) % 2 == 0);
  }
}

//Bit manipulation TC O(1) SC O(1)
class Solution {
  public boolean isPowerOfFour(int num) {
    return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0xaaaaaaaa) == 0);
  }
}

//Bit manipulation + Math TC O(1) SC O(1)
class Solution {
  public boolean isPowerOfFour(int num) {
    return (num > 0) && ((num & (num - 1)) == 0) && (num % 3 == 1);
  }
}
