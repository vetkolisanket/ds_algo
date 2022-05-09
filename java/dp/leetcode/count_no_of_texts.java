/*
2266. Count Number of Texts

Alice is texting Bob using her phone. The mapping of digits to letters is shown in the figure below.


In order to add a letter, Alice has to press the key of the corresponding digit i times, where i is the position of the letter in the key.

For example, to add the letter 's', Alice has to press '7' four times. Similarly, to add the letter 'k', Alice has to press '5' twice.
Note that the digits '0' and '1' do not map to any letters, so Alice does not use them.
However, due to an error in transmission, Bob did not receive Alice's text message but received a string of pressed keys instead.

For example, when Alice sent the message "bob", Bob received the string "2266622".
Given a string pressedKeys representing the string received by Bob, return the total number of possible text messages Alice could have sent.

Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: pressedKeys = "22233"
Output: 8
Explanation:
The possible text messages Alice could have sent are:
"aaadd", "abdd", "badd", "cdd", "aaae", "abe", "bae", and "ce".
Since there are 8 possible messages, we return 8.
Example 2:

Input: pressedKeys = "222222222222222222222222222222222222"
Output: 82876089
Explanation:
There are 2082876103 possible text messages Alice could have sent.
Since we need to return the answer modulo 109 + 7, we return 2082876103 % (109 + 7) = 82876089.
 

Constraints:

1 <= pressedKeys.length <= 10^5
pressedKeys only consists of digits from '2' - '9'.

*/

class Solution {
    
    public int countTexts(String pressedKeys) {
        int mod = (int) 1e9+7;
        int n = pressedKeys.length();
        int[] dp = new int[n+1];
        //Base case
        dp[0] = 1;
        for (int i=1;i<=n;i++) {
            /*By default addition of ith char will create atleast same no. of texts as (i-1)th char will make*/
            dp[i] = dp[i-1];
            if (i > 1 && pressedKeys.charAt(i-1) == pressedKeys.charAt(i-2)) {
                /*If ith and (i-1)th char are same then the no. of texts made by (i-2)th char will also make 
                them with the new char formed by combining ith and (i-1)th char*/
                dp[i] = (dp[i] + dp[i-2])%mod;
                if (i > 2 && pressedKeys.charAt(i-1) == pressedKeys.charAt(i-3)) {
                    /*If ith and (i-2)th char are same then the no. of texts made by (i-3)th char will also make 
                    them with the new char formed by combining ith and (i-1)th and (i-2)th char*/
                    dp[i] = (dp[i] + dp[i-3])%mod;
                    if (i > 3 && (pressedKeys.charAt(i-1) == '7' || pressedKeys.charAt(i-1) == '9') && pressedKeys.charAt(i-1) == pressedKeys.charAt(i-4)) {
                        /*If ith and (i-3)th char are same and the char belonged to 7 or 9 key then the no. of 
                        texts made by (i-4)th char will also make them with the new char formed by combining 
                        ith and (i-1)th (i-2)th and (i-3)th char*/
                        dp[i] = (dp[i] + dp[i-4])%mod;
                    }
                }
            }
        }
        return dp[n];
    }
}
