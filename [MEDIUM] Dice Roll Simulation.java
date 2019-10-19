A die simulator generates a random number from 1 to 6 for each roll. You introduced a constraint to the generator such that it cannot roll the number i more than rollMax[i] (1-indexed) consecutive times. 

Given an array of integers rollMax and an integer n, return the number of distinct sequences that can be obtained with exact n rolls.

Two sequences are considered different if at least one element differs from each other. Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:

Input: n = 2, rollMax = [1,1,2,2,2,3]
Output: 34
Explanation: There will be 2 rolls of die, if there are no constraints on the die, there are 6 * 6 = 36 possible combinations. In this case, looking at rollMax array, the numbers 1 and 2 appear at most once consecutively, therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.
Example 2:

Input: n = 2, rollMax = [1,1,1,1,1,1]
Output: 30
Example 3:

Input: n = 3, rollMax = [1,1,1,2,2,3]
Output: 181
 

Constraints:

1 <= n <= 5000
rollMax.length == 6
1 <= rollMax[i] <= 15

class Solution {
    public int dieSimulator(int n, int[] rollMax) {
        int mod = (int)1e9 +7;
        // int[i][6] stores sum of int[i][0~5]
        int[][] dp = new int[n+1][7];
        for(int i=0; i<6; i++){
            dp[1][i] = 1;
        }
        dp[1][6] = 6;
        for(int i=2; i<=n; i++){
            int total = 0;
            for(int j=0; j<6; j++){
                dp[i][j] = dp[i-1][6];
                if(i-rollMax[j]==1){
                    dp[i][j] = (dp[i][j]-1+mod)%mod;
                }else if(i-rollMax[j]>=2){
                    int minus = (dp[i-rollMax[j]-1][6] - dp[i-rollMax[j]-1][j] +mod)%mod;
                    dp[i][j] = (dp[i][j] - minus +mod)%mod;
                }
                total = (total + dp[i][j])%mod;
            }
            dp[i][6] = total;
        }
        return dp[n][6];
    }
}
