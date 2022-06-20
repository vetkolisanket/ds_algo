/*
1268. Search Suggestions System

You are given an array of strings products and a string searchWord.

Design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

Return a list of lists of the suggested products after each character of searchWord is typed.

 

Example 1:

Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [
["mobile","moneypot","monitor"],
["mobile","moneypot","monitor"],
["mouse","mousepad"],
["mouse","mousepad"],
["mouse","mousepad"]
]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
Example 2:

Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
Example 3:

Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 

Constraints:

1 <= products.length <= 1000
1 <= products[i].length <= 3000
1 <= sum(products[i].length) <= 2 * 104
All the strings of products are unique.
products[i] consists of lowercase English letters.
1 <= searchWord.length <= 1000
searchWord consists of lowercase English letters.
*/

//Using Trie
class Solution {
    class Trie {
        Trie[] sub = new Trie[26];
        LinkedList<String> suggestion = new LinkedList<>();
    }
    
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie root = new Trie();
        for (String p : products) { // build Trie.
            insert(p, root); // insert a product into Trie.
        }
        return search(searchWord, root);
    }
    
    private void insert(String p, Trie root) {
        Trie t = root;
        for (char c : p.toCharArray()) { // insert current product into Trie.
            if (t.sub[c - 'a'] == null)
                t.sub[c - 'a'] = new Trie();
            t = t.sub[c - 'a'];
            t.suggestion.offer(p); // put products with same prefix into suggestion list.
            Collections.sort(t.suggestion);
            if (t.suggestion.size() > 3) // maintain 3 lexicographically minimum strings.
                t.suggestion.pollLast();        
        }
    }
    
    private List<List<String>> search(String searchWord, Trie root) {
        List<List<String>> ans = new ArrayList<>();
        for (char c : searchWord.toCharArray()) { // search product.
            if (root != null) // if there exist products with current prefix.
                root = root.sub[c - 'a'];
            ans.add(root == null ? Arrays.asList() : root.suggestion); // add it if there exist products with current prefix.
        }
        return ans;
    }
}

//My soln from another attempt
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<String> productList = Arrays.asList(products);
        List<List<String>> res = new ArrayList();
        int length = searchWord.length();
        for (int i=0;i<length;i++) {
            productList = getSuggestions(productList, searchWord.substring(0, i+1));
            if (productList.size() > 3) {
                res.add(productList.subList(0, 3));
            } else res.add(productList);
        }
        return res;
    }
    
    private List<String> getSuggestions(List<String> list, String s) {
        List<String> suggestions = new ArrayList();
        for (String word: list) {
            if (word.startsWith(s)) {
                suggestions.add(word);
            }
        }
        return suggestions;
    }
}

//Sort and binary search
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> ans = new ArrayList<>();
        Arrays.sort(products); // sort products.
        for (int i = 1; i <= searchWord.length(); ++i) {
            String cur = searchWord.substring(0, i);
            int k = Arrays.binarySearch(products, cur);
            while (k > 0 && cur.equals(products[k - 1])) // in case there are more than 1 cur in products.
                --k; // find the first one. 
            if (k < 0) // no cur in products. 
                k = ~k; // find the first one larger than cur.
            List<String> suggestion = new ArrayList<>();
            for (int j = k + 3; k < products.length && k < j && products[k].startsWith(cur); ++k)
                suggestion.add(products[k]);
            ans.add(suggestion);
        }
        return ans;
    }
}

//My soln
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList();
        Arrays.sort(products);
        int len = searchWord.length();
        for (int i=1;i<=len;i++) {
            String ss = searchWord.substring(0, i);
            List<String> list = new ArrayList();
            for (String product : products) {
                if (product.startsWith(ss)) {
                    list.add(product);
                    if (list.size() == 3) break;
                }
            }
            res.add(list);
        }
        return res;
    }
}

//Another soln using TreeMap and floorKey and ceilingKey
public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        TreeMap<String, Integer> map = new TreeMap<>();
        Arrays.sort(products);
        List<String> productsList = Arrays.asList(products);

        for (int i = 0; i < products.length; i++) {
            map.put(products[i], i);
        }

        String key = "";
        for (char c : searchWord.toCharArray()) {
            key += c;
            String ceiling = map.ceilingKey(key);
            String floor = map.floorKey(key + "~");
            if (ceiling == null || floor == null) break;
            res.add(productsList.subList(map.get(ceiling), Math.min(map.get(ceiling) + 3, map.get(floor) + 1)));
        }

        while (res.size() < searchWord.length()) res.add(new ArrayList<>());
        return res;
    }
