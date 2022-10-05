/*
1996. The Number of Weak Characters in the Game

You are playing a game that contains multiple characters, and each of the characters has two main properties: attack and defense. You are given a 2D integer array properties where properties[i] = [attacki, defensei] represents the properties of the ith character in the game.

A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels. More formally, a character i is said to be weak if there exists another character j where attackj > attacki and defensej > defensei.

Return the number of weak characters.

 

Example 1:

Input: properties = [[5,5],[6,3],[3,6]]
Output: 0
Explanation: No character has strictly greater attack and defense than the other.
Example 2:

Input: properties = [[2,2],[3,3]]
Output: 1
Explanation: The first character is weak because the second character has a strictly greater attack and defense.
Example 3:

Input: properties = [[1,5],[10,4],[4,3]]
Output: 1
Explanation: The third character is weak because the second character has a strictly greater attack and defense.
 

Constraints:

2 <= properties.length <= 10^5
properties[i].length == 2
1 <= attacki, defensei <= 10^5
*/

//Greedy soln TC O(N+K) SC O(K) where N is the no. of characters and K is the maxAttack value
class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        int maxAttack = 0;
        for (int[] property: properties) {
            maxAttack = Math.max(maxAttack, property[0]);
        }
        int[] maxDefense = new int[maxAttack+2];
        for (int[] property: properties) {
            int attack = property[0];
            int defense = property[1];
            maxDefense[attack] = Math.max(maxDefense[attack], defense);
        }
        for (int i=maxDefense.length-2;i>=0;i--) {
            maxDefense[i] = Math.max(maxDefense[i], maxDefense[i+1]);
        }
        int numWeakCharacters = 0;
        for (int[] property: properties) {
            int attack = property[0];
            int defense = property[1];
            if (defense < maxDefense[attack+1]) numWeakCharacters++;
        }
        return numWeakCharacters;
    }
}

//Soln using sorting TC O(Nlog(N)) SC O(log(N))
class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else return a[0] - b[0];
        });
        int ans = 0, maxDefense = 0;
        for (int i=properties.length-1;i>=0;i--) {
            if (properties[i][1] < maxDefense) {
                ans++;
            } else maxDefense = properties[i][1];
        }
        return ans;
    }
}
