/*
Permutations of a given string

Given a string S. The task is to print all permutations of a given string in lexicographically sorted order.

 

Example 1:

Input: ABC
Output:
ABC ACB BAC BCA CAB CBA
Explanation:
Given string ABC has permutations in 6 
forms as ABC, ACB, BAC, BCA, CAB and CBA .
Example 2:

Input: ABSG
Output:
ABGS ABSG AGBS AGSB ASBG ASGB BAGS 
BASG BGAS BGSA BSAG BSGA GABS GASB 
GBAS GBSA GSAB GSBA SABG SAGB SBAG 
SBGA SGAB SGBA
Explanation:
Given string ABSG has 24 permutations.
 

Your Task:  
You don't need to read input or print anything. Your task is to complete the function find_permutaion() which takes the string S as input parameter and returns a vector of string in lexicographical order.

 

Expected Time Complexity: O(n! * n)

Expected Space Complexity: O(n)

 

Constraints:
1 <= length of string <= 5
*/

class Solution {
    public List<String> find_permutation(String S) {
        // Code here
        List<String> list = new ArrayList();
        findPermutations("", S, list);
        Collections.sort(list);
        return list;
    }
    
    private void findPermutations(String a, String b, List<String> list) {
        int length = b.length();
        if (length == 0) {
            list.add(a);
        } else {
            for (int i=0;i<length;i++) {
                String s = b.substring(0, i) + b.substring(i+1);
                findPermutations(a+b.charAt(i), s, list);
            }
        }
    }
}
