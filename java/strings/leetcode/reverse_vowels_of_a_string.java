/*
345. Reverse Vowels of a String

Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

 

Example 1:

Input: s = "hello"
Output: "holle"
Example 2:

Input: s = "leetcode"
Output: "leotcede"
 

Constraints:

1 <= s.length <= 3 * 10^5
s consist of printable ASCII characters.
*/

//My soln TC O(N) SC O(N) where N is the length of the string
class Solution {
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        Set<Character> vowels = new HashSet(Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        List<Character> list = new ArrayList();
        for (char c: arr) {
            if (vowels.contains(c)) {
                list.add(c);
            }
        }
        for (int i=arr.length-1, j=0;i >=0; i--) {
            if (vowels.contains(arr[i])) {
                arr[i] = list.get(j++);
            }
        }
        return String.valueOf(arr);
    }
}

//Soln from another approach which doesn't store the vowels in a list but instead uses two pointers TC O(N) SC O(N)
class Solution {

    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        int i = 0, j = arr.length-1;
        while (i < j) {
            while (i < j && !vowels.contains(arr[i])) {
                i++;
            }
            while (j > i && !vowels.contains(arr[j])) {
                j--;
            }
            if (i < j) {
                char c = arr[i];
                arr[i++] = arr[j];
                arr[j--] = c;
            }
        }
        return new String(arr);
    }
}
