/*
875. Koko Eating Bananas

Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

 

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23
 

Constraints:

1 <= piles.length <= 10^4
piles.length <= h <= 10^9
1 <= piles[i] <= 10^9

*/

//Soln using binary search TC O(nlog(m)) SC O(1) where n is the length of the pile and m is the max value in the pile
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        for (int pile: piles) {
            max = Math.max(pile, max);
        }
        int start = 1, end = max;
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (isPossible(piles, h, mid)) {
                if (!isPossible(piles, h, mid-1)) return mid;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return start;
    }

    private boolean isPossible(int[] piles, int h, double val) {
        int hours = 0;
        for (int pile: piles) {
            hours += Math.ceil(pile/val);
        }
        return hours <= h;
    }
}


