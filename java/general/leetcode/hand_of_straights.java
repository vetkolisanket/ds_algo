/*
846. Hand of Straights

Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.

Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return true if she can rearrange the cards, or false otherwise.

 

Example 1:

Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
Example 2:

Input: hand = [1,2,3,4,5], groupSize = 4
Output: false
Explanation: Alice's hand can not be rearranged into groups of 4.

 

Constraints:

1 <= hand.length <= 104
0 <= hand[i] <= 109
1 <= groupSize <= hand.length
 

Note: This question is the same as 1296: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/


*/

//My soln using tree map TC O(NlogN) SC O(N)
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length%groupSize != 0) return false;
        TreeMap<Integer, Integer> map = new TreeMap();
        for (int num: hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        while (!map.isEmpty()) {
            int prev = -1;
            for (int i=0;i<groupSize;i++) {
                if (i==0) {
                    prev = map.firstKey();
                } else if (map.containsKey(prev+1)) {
                    prev = prev+1;
                } else {
                    return false;
                }
                map.put(prev, map.get(prev) - 1);
                if (map.get(prev) == 0) {
                    map.remove(prev);
                }
            }
        }
        return true;
    }
}

//A more time-efficient soln TC O(N) SC O(N)
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        Map<Integer, Integer> map = new HashMap<>();
        for (int num: hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int card: hand) {
            while (map.getOrDefault(card, 0) > 0) {
                int startCard = card;
                while (map.getOrDefault(startCard-1, 0) > 0) {
                    startCard--;
                }
                for (int nextCard = startCard; nextCard < startCard + groupSize; 
                    nextCard++) {
                    if (map.getOrDefault(nextCard, 0) == 0) return false;
                    map.put(nextCard, map.get(nextCard)-1);
                }
            }
        }
        return true;
    }
}
