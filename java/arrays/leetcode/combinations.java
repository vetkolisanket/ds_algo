/*
77. Combinations

Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].

You may return the answer in any order.

 

Example 1:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
Example 2:

Input: n = 1, k = 1
Output: [[1]]
 

Constraints:

1 <= n <= 20
1 <= k <= n
*/

//A way faster soln, with a little modification
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList();
        combine(res, new ArrayList(), 1, n, k);
        return res;
    }

    private void combine(List<List<Integer>> res, List<Integer> list, int i, int n, int k) {
        if (0 == k) {    
            res.add(new ArrayList(list));
        } else {
            for (int j=i;j<=n-k+1;j++) {
                list.add(j);
                combine(res, list, j+1, n, k-1);
                list.remove(list.size()-1);
            }
        }
    }
}

//My soln TC O(n!/((k-1)!*(n-k)!)) SC O(k)
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList();
        combine(res, new ArrayList(), 1, n, k);
        return res;
    }
    
    private void combine(List<List<Integer>> res, List<Integer> list, int i, int n, int k) {
        if (list.size() == k) {
            res.add(new ArrayList(list));
        } else {
            for (int j=i;j<=n;j++) {
                list.add(j);
                combine(res, list, j+1, n, k);
                list.remove(list.size()-1);
            }
        }
    }
}

//Another way
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new LinkedList();
        if (n < k || k == 0) return ans;
        
        ans = combine(n-1, k-1);
        if (ans.isEmpty()) ans.add(new LinkedList());
        for (List<Integer> list: ans) list.add(n);
        
        ans.addAll(combine(n-1, k));
        return ans;
    }
}
