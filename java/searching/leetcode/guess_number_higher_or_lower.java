/*
374. Guess Number Higher or Lower

We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.

You call a pre-defined API int guess(int num), which returns three possible results:

-1: Your guess is higher than the number I picked (i.e. num > pick).
1: Your guess is lower than the number I picked (i.e. num < pick).
0: your guess is equal to the number I picked (i.e. num == pick).
Return the number that I picked.

 

Example 1:

Input: n = 10, pick = 6
Output: 6
Example 2:

Input: n = 1, pick = 1
Output: 1
Example 3:

Input: n = 2, pick = 1
Output: 1
 

Constraints:

1 <= n <= 2^31 - 1
1 <= pick <= n

*/

/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

//Short and simple soln
public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int left = 1;
        while (left < n) {
            int mid = (left + n) >>> 1;
            int guess = guess(mid);
            if (guess <= 0) {
                n = mid;
            } else {
                left = mid+1;
            }
        }
        return left;
    }
}

//The overflow condition can also be written as (low + high) >>> 1 instead of low + (high - low) / 2 and it is faster as well
public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int left = 1;
        while (left < n) {
            int mid = (left + n) >>> 1;
            int guess = guess(mid);
            if (guess == -1) {
                n = mid-1;
            } else if (guess == 1) {
                left = mid+1;
            } else return mid;
        }
        return left;
    }
}

//Turns out >> 1 is faster than / 2 and both do the same things
public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int left = 1;
        while (left < n) {
            int mid = left + ((n - left)>>1);
            int guess = guess(mid);
            if (guess == -1) {
                n = mid-1;
            } else if (guess == 1) {
                left = mid+1;
            } else return mid;
        }
        return left;
    }
}

//My soln TC O(log(N)) SC O(1)
public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int left = 1;
        while (left < n) {
            int mid = left + (n - left)/2;
            int guess = guess(mid);
            if (guess == -1) {
                n = mid-1;
            } else if (guess == 1) {
                left = mid+1;
            } else return mid;
        }
        return left;
    }
}
