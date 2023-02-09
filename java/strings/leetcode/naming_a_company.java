/*
2306. Naming a Company

LeetCode Logo
Problem List
287

avatar
2306. Naming a Company
Hard
1.4K
62
You are given an array of strings ideas that represents a list of names to be used in the process of naming a company. The process of naming a company is as follows:

Choose 2 distinct names from ideas, call them ideaA and ideaB.
Swap the first letters of ideaA and ideaB with each other.
If both of the new names are not found in the original ideas, then the name ideaA ideaB (the concatenation of ideaA and ideaB, separated by a space) is a valid company name.
Otherwise, it is not a valid name.
Return the number of distinct valid names for the company.

 

Example 1:

Input: ideas = ["coffee","donuts","time","toffee"]
Output: 6
Explanation: The following selections are valid:
- ("coffee", "donuts"): The company name created is "doffee conuts".
- ("donuts", "coffee"): The company name created is "conuts doffee".
- ("donuts", "time"): The company name created is "tonuts dime".
- ("donuts", "toffee"): The company name created is "tonuts doffee".
- ("time", "donuts"): The company name created is "dime tonuts".
- ("toffee", "donuts"): The company name created is "doffee tonuts".
Therefore, there are a total of 6 distinct company names.

The following are some examples of invalid selections:
- ("coffee", "time"): The name "toffee" formed after swapping already exists in the original array.
- ("time", "toffee"): Both names are still the same after swapping and exist in the original array.
- ("coffee", "toffee"): Both names formed after swapping already exist in the original array.
Example 2:

Input: ideas = ["lack","back"]
Output: 0
Explanation: There are no valid selections. Therefore, 0 is returned.
 

Constraints:

2 <= ideas.length <= 5 * 10^4
1 <= ideas[i].length <= 10
ideas[i] consists of lowercase English letters.
All the strings in ideas are unique.

*/

//Soln TC O(m*n) SC O(m*n) where m is the avg length of the word, n is the no. of words
class Solution {
    public long distinctNames(String[] ideas) {
        long ans = 0;
        Set<String>[] arr = new HashSet[26];
        for (int i=0;i<26;i++) {
            arr[i] = new HashSet();
        }
        for (String idea: ideas) {
            arr[idea.charAt(0) - 'a'].add(idea.substring(1));
        }
        for (int i=0;i<25;i++) {
            for (int j=i+1;j<26;j++) {
                int common = 0;
                for (String prefix: arr[i]) {
                    if (arr[j].contains(prefix)) common++;
                }
                ans += 2 * (arr[i].size() - common) * (arr[j].size() - common);
            }
        }
        return ans;
    }
}
