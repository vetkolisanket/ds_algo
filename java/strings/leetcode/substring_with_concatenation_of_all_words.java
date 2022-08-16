/*
30. Substring with Concatenation of All Words

You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.

You can return the answer in any order.

 

Example 1:

Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []
Example 3:

Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]
 

Constraints:

1 <= s.length <= 104
s consists of lower-case English letters.
1 <= words.length <= 5000
1 <= words[i].length <= 30
words[i] consists of lower-case English letters.
*/

//Soln from 2nd attempt TC O(abn + (ab)^2) and SC O(ab) where a is the no. of words, b is length of each word and n is the length of the string
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList();
        Map<String, Integer> wordMap = new HashMap();
        for (String word: words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        int sLen = s.length(), len = words.length, wLen = words[0].length();
        for (int i=0;i<=sLen-len*wLen;i++) {
            Map<String, Integer> includedMap = new HashMap(wordMap);
            int wordCount = 0;
            for (int j=i;j<i+len*wLen;j += wLen) {
                String ss = s.substring(j, j+wLen);
                if (includedMap.getOrDefault(ss, 0) != 0) {
                    includedMap.put(ss, includedMap.get(ss)-1);
                    wordCount++;
                } else break;
            }
            if (wordCount == len) list.add(i);
        }
        return list;
    }
}

//A faster soln using sliding window TC O(a + nb) SC O(a + b)
class Solution {
    private HashMap<String, Integer> wordCount = new HashMap();
    private int n;
    private int wordLength;
    private int substringSize;
    private int k;
    
    public List<Integer> findSubstring(String s, String[] words) {
        n = s.length();
        k = words.length;
        wordLength = words[0].length();
        substringSize = wordLength * k;
        
        for (String word: words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        
        List<Integer> answer = new ArrayList();
        for (int i = 0; i < wordLength; i++) {
            slidingWindow(i, s, answer);
        }
        
        return answer;
    }
    
    private void slidingWindow(int left, String s, List<Integer> answer) {
        HashMap<String, Integer> wordsFound = new HashMap();
        int wordsUsed = 0;
        boolean excessWord = false;
        
        for (int right = left; right <= n - wordLength; right += wordLength) {
            String sub = s.substring(right, right + wordLength);
            if (!wordCount.containsKey(sub)) {
                wordsFound.clear();
                wordsUsed = 0;
                excessWord = false;
                left = right + wordLength;
            } else {
                while (right - left == substringSize || excessWord) {
                    String leftmostWord = s.substring(left, left + wordLength);
                    left += wordLength;
                    wordsFound.put(leftmostWord, wordsFound.get(leftmostWord) - 1);
                    
                    if (wordsFound.get(leftmostWord) >= wordCount.get(leftmostWord)) {
                        excessWord = false;
                    } else {
                        wordsUsed--;
                    }
                }
                
                wordsFound.put(sub, wordsFound.getOrDefault(sub, 0) + 1);
                if (wordsFound.get(sub) <= wordCount.get(sub)) {
                    wordsUsed++;
                } else {
                    excessWord = true;
                }
                
                if (wordsUsed == k && !excessWord) {
                    answer.add(left);
                }
            }
        }
    }
}


class Solution {
    public static List<Integer> findSubstring(String S, String[] L) {
    List<Integer> res = new ArrayList<Integer>();
    if (S == null || L == null || L.length == 0) return res;
    int len = L[0].length(); // length of each word
    
    Map<String, Integer> map = new HashMap<String, Integer>(); // map for L
    for (String w : L) map.put(w, map.containsKey(w) ? map.get(w) + 1 : 1);
    
    for (int i = 0; i <= S.length() - len * L.length; i++) {
        Map<String, Integer> copy = new HashMap<String, Integer>(map);
        for (int j = 0; j < L.length; j++) { // checkc if match
            String str = S.substring(i + j*len, i + j*len + len); // next word
            if (copy.containsKey(str)) { // is in remaining words
                int count = copy.get(str);
                if (count == 1) copy.remove(str);
                else copy.put(str, count - 1);
                if (copy.isEmpty()) { // matches
                    res.add(i);
                    break;
                }
            } else break; // not in L
        }
    }
    return res;
}
    
}

// A better soln but didn't understand
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
	int N = s.length();
	List<Integer> indexes = new ArrayList<Integer>(s.length());
	if (words.length == 0) {
		return indexes;
	}
	int M = words[0].length();
	if (N < M * words.length) {
		return indexes;
	}
	int last = N - M + 1;
	
	//map each string in words array to some index and compute target counters
	Map<String, Integer> mapping = new HashMap<String, Integer>(words.length);
	int [][] table = new int[2][words.length];
	int failures = 0, index = 0;
	for (int i = 0; i < words.length; ++i) {
		Integer mapped = mapping.get(words[i]);
		if (mapped == null) {
			++failures;
			mapping.put(words[i], index);
			mapped = index++;
		}
		++table[0][mapped];
	}
	
	//find all occurrences at string S and map them to their current integer, -1 means no such string is in words array
	int [] smapping = new int[last];
	for (int i = 0; i < last; ++i) {
		String section = s.substring(i, i + M);
		Integer mapped = mapping.get(section);
		if (mapped == null) {
			smapping[i] = -1;
		} else {
			smapping[i] = mapped;
		}
	}
	
	//fix the number of linear scans
	for (int i = 0; i < M; ++i) {
		//reset scan variables
		int currentFailures = failures; //number of current mismatches
		int left = i, right = i;
		Arrays.fill(table[1], 0);
		//here, simple solve the minimum-window-substring problem
		while (right < last) {
			while (currentFailures > 0 && right < last) {
				int target = smapping[right];
				if (target != -1 && ++table[1][target] == table[0][target]) {
					--currentFailures;
				}
				right += M;
			}
			while (currentFailures == 0 && left < right) {
				int target = smapping[left];
				if (target != -1 && --table[1][target] == table[0][target] - 1) {
					int length = right - left;
					//instead of checking every window, we know exactly the length we want
					if ((length / M) ==  words.length) {
						indexes.add(left);
					}
					++currentFailures;
				}
				left += M;
			}
		}
		
	}
	return indexes;
}
    
}

// Another soln
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
		/**
		 * Let n=s.length, k=words[0].length traverse s with indices i, i+k,
		 * i+2k, ... for 0<=i<k, so that the time complexity is O(n).
		 */
		List<Integer> res = new ArrayList<Integer>();
		int n = s.length(), m = words.length, k;
		if (n == 0 || m == 0 || (k = words[0].length()) == 0)
			return res;

		HashMap<String, Integer> wDict = new HashMap<String, Integer>();

		for (String word : words) {
			if (wDict.containsKey(word))
				wDict.put(word, wDict.get(word) + 1);
			else
				wDict.put(word, 1);
		}

		int i, j, start, x, wordsLen = m * k;
		HashMap<String, Integer> curDict = new HashMap<String, Integer>();
		String test, temp;
		for (i = 0; i < k; i++) {
			curDict.clear();
			start = i;
			if (start + wordsLen > n)
				return res;
			for (j = i; j + k <= n; j += k) {
				test = s.substring(j, j + k);

				if (wDict.containsKey(test)) {
					if (!curDict.containsKey(test)) {
						curDict.put(test, 1);

						start = checkFound(res, start, wordsLen, j, k, curDict, s);
						continue;
					}

					// curDict.containsKey(test)
					x = curDict.get(test);
					if (x < wDict.get(test)) {
						curDict.put(test, x + 1);

						start = checkFound(res, start, wordsLen, j, k, curDict, s);
						continue;
					}

					// curDict.get(test)==wDict.get(test), slide start to
					// the next word of the first same word as test
					while (!(temp = s.substring(start, start + k)).equals(test)) {
						decreaseCount(curDict, temp);
						start += k;
					}
					start += k;
					if (start + wordsLen > n)
						break;
					continue;
				}

				// totally failed up to index j+k, slide start and reset all
				start = j + k;
				if (start + wordsLen > n)
					break;
				curDict.clear();
			}
		}
		return res;
	}

	public int checkFound(List<Integer> res, int start, int wordsLen, int j, int k,
			HashMap<String, Integer> curDict, String s) {
		if (start + wordsLen == j + k) {
			res.add(start);
			// slide start to the next word
			decreaseCount(curDict, s.substring(start, start + k));
			return start + k;
		}
		return start;
	}

	public void decreaseCount(HashMap<String, Integer> curDict, String key) {
		// remove key if curDict.get(key)==1, otherwise decrease it by 1
		int x = curDict.get(key);
		if (x == 1)
			curDict.remove(key);
		else
			curDict.put(key, x - 1);
	}
    
}
