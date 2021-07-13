/*
1672. Richest Customer Wealth

You are given an m x n integer grid accounts where accounts[i][j] is the amount of money the ith customer has in the jth bank. Return the wealth that the richest customer has.

A customer's wealth is the amount of money they have in all their bank accounts. The richest customer is the customer that has the maximum wealth.

Input: accounts = [[1,2,3],[3,2,1]]
Output: 6
Explanation:
1st customer has wealth = 1 + 2 + 3 = 6
2nd customer has wealth = 3 + 2 + 1 = 6
Both customers are considered the richest with a wealth of 6 each, so return 6.

Input: accounts = [[1,5],[7,3],[3,5]]
Output: 10
Explanation: 
1st customer has wealth = 6
2nd customer has wealth = 10 
3rd customer has wealth = 8
The 2nd customer is the richest with a wealth of 10.

Input: accounts = [[2,8,7],[7,1,3],[1,9,5]]
Output: 17

Constraints:

m == accounts.length
n == accounts[i].length
1 <= m, n <= 50
1 <= accounts[i][j] <= 100
*/

/*
Better solutions

T: O(mn)
S: O(1)
Variation 1: Using loops

public int maximumWealth(int[][] accounts) {
	int max = 0;
	for (int[] customer : accounts) {
		int wealth = 0;
		for (int account : customer)
			wealth += account;
		max = Math.max(max, wealth);
	}
	return max;
}
Variation 2: Using Loop and Stream

public int maximumWealth(int[][] accounts) {
	int max = 0;
	for (int[] customer : accounts) {
		int wealth = Arrays.stream(customer).sum();
		max = Math.max(max, wealth);
	}
	return max;
}
Variation 3: Using Streams

public int maximumWealth(int[][] accounts) {
	return Arrays.stream(accounts)
				 .mapToInt(customerAccount -> Arrays.stream(customerAccount).sum())
				 .max()
				 .getAsInt();
}
*/

class Solution {
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int i=0;i<accounts.length;i++) {
            int sum = 0;
            for (int j=0;j<accounts[i].length;j++) {
                sum += accounts[i][j];
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
