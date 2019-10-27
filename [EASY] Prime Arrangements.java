Return the number of permutations of 1 to n so that prime numbers are at prime indices (1-indexed.)

(Recall that an integer is prime if and only if it is greater than 1, and cannot be written as a product of two positive integers both smaller than it.)

Since the answer may be large, return the answer modulo 10^9 + 7.

 

Example 1:

Input: n = 5
Output: 12
Explanation: For example [1,2,5,4,3] is a valid permutation, but [5,2,3,4,1] is not because the prime number 5 is at index 1.
Example 2:

Input: n = 100
Output: 682289015
 

Constraints:

1 <= n <= 100

class Solution {
    long mod = (int)1e9 +7;
    public int numPrimeArrangements(int n) {
        long p = soe(n);
        return (int)((factorial(p)*factorial(n-p))%mod);
    }
    
    long factorial(long x){
        long result = 1;
        for(long i=2; i<=x; i++){
            result = (result*i)%mod;
        }
        return result;
    }

    int soe(int n) {
        int primeCounter = n-1;
        boolean[] isComposite = new boolean[n+1];
        
        for(int i=2; i<=n; i++){
            if(!isComposite[i]){
                for(int j=i*i; j<=n; j+=i){
                    isComposite[j]=true;
                }
            }else{
                primeCounter--;
            }
        }
        
        return primeCounter;
    }
}
