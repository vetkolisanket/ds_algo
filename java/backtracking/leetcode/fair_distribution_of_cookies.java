/*
2305. Fair Distribution of Cookies

You are given an integer array cookies, where cookies[i] denotes the number of cookies in the ith bag. You are also given an integer k that denotes the number of children to distribute all the bags of cookies to. All the cookies in the same bag must go to the same child and cannot be split up.

The unfairness of a distribution is defined as the maximum total cookies obtained by a single child in the distribution.

Return the minimum unfairness of all distributions.

 

Example 1:

Input: cookies = [8,15,10,20,8], k = 2
Output: 31
Explanation: One optimal distribution is [8,15,8] and [10,20]
- The 1st child receives [8,15,8] which has a total of 8 + 15 + 8 = 31 cookies.
- The 2nd child receives [10,20] which has a total of 10 + 20 = 30 cookies.
The unfairness of the distribution is max(31,30) = 31.
It can be shown that there is no distribution with an unfairness less than 31.
Example 2:

Input: cookies = [6,1,3,2,2,4,1,2], k = 3
Output: 7
Explanation: One optimal distribution is [6,1], [3,2,2], and [4,1,2]
- The 1st child receives [6,1] which has a total of 6 + 1 = 7 cookies.
- The 2nd child receives [3,2,2] which has a total of 3 + 2 + 2 = 7 cookies.
- The 3rd child receives [4,1,2] which has a total of 4 + 1 + 2 = 7 cookies.
The unfairness of the distribution is max(7,7,7) = 7.
It can be shown that there is no distribution with an unfairness less than 7.
 

Constraints:

2 <= cookies.length <= 8
1 <= cookies[i] <= 10^5
2 <= k <= cookies.length
*/

//Soln using backtracking TC O(K^N) SC O(K + N) where K is the no. of students and N is the no. of cookies
class Solution {
    public int distributeCookies(int[] cookies, int k) {
        int[] distribute = new int[k];
        int avg = Arrays.stream(cookies).sum()/k;
        return dfs(cookies, distribute, 0, k, k, avg);
    }

    private int dfs(int[] cookies, int[] distribute, int i, int k, int zeroCount, int avg) {
        if (cookies.length - i < zeroCount) return Integer.MAX_VALUE;

        if (i == cookies.length) {
            int ans = Integer.MIN_VALUE;
            for (int val: distribute) {
                ans = Math.max(ans, val);
            }
            return ans;
        }

        int ans = Integer.MAX_VALUE;
        for (int j=0;j<k;j++) {
            zeroCount -= distribute[j] == 0 ? 1 : 0;
            if (distribute[j] > avg) continue;
            distribute[j] += cookies[i];
            ans = Math.min(ans, dfs(cookies, distribute, i+1, k, zeroCount, avg));
            distribute[j] -= cookies[i];
            zeroCount += distribute[j] == 0 ? 1 : 0;
        }
        return ans;
    }
}
