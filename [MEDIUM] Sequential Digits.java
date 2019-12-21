An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

 

Example 1:

Input: low = 100, high = 300
Output: [123,234]
Example 2:

Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]
 

Constraints:

10 <= low <= high <= 10^9

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new LinkedList<Integer>();
        int lowDecimal = countDecimal(low);
        int highDecimal = countDecimal(high);
        for(int i=lowDecimal; i<=highDecimal; i++) {
            ans.addAll(sequential(i, low, high));
        }
        return ans;
    }
    
    int countDecimal(int num) {
        int ans = 0;
        while(num!=0) {
            ans++;
            num /= 10;
        }
        return ans;
    }
    
    List<Integer> sequential(int decimal, int low, int high) {
        List<Integer> ans = new LinkedList<Integer>();
        for(int i=1; i<=10-decimal; i++) {
            int seqDigit = digits(i, decimal);
            if(seqDigit>=low && seqDigit<=high) {
                ans.add(seqDigit);
            }
        }
        return ans;
    }
    
    int digits(int start, int decimal) {
        int ans = 0;
        for(int i=start; i<start+decimal; i++) {
            ans *= 10;
            ans += i;
        }
        return ans;
    }
    
}
