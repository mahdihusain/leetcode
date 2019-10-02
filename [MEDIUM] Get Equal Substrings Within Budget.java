You are given two strings s and t of the same length. You want to change s to t. Changing the i-th character of s to i-th character of t costs |s[i] - t[i]| that is, the absolute difference between the ASCII values of the characters.

You are also given an integer maxCost.

Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of twith a cost less than or equal to maxCost.

If there is no substring from s that can be changed to its corresponding substring from t, return 0.

 

Example 1:

Input: s = "abcd", t = "bcdf", maxCost = 3
Output: 3
Explanation: "abc" of s can change to "bcd". That costs 3, so the maximum length is 3.
Example 2:

Input: s = "abcd", t = "cdef", maxCost = 3
Output: 1
Explanation: Each character in s costs 2 to change to charactor in t, so the maximum length is 1.
Example 3:

Input: s = "abcd", t = "acde", maxCost = 0
Output: 1
Explanation: You can't make any change, so the maximum length is 1.
 

Constraints:

1 <= s.length, t.length <= 10^5
0 <= maxCost <= 10^6
s and t only contain lower case English letters.

//binary search
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int N = s.length();
        int left = 0;
        int right = N;
        int output = 0;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(cost(s,t,mid,maxCost)){
                output = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return output;
    }
    
    public boolean cost(String s, String t, int c, int maxCost) {
        int N = s.length();
        int[] diff = new int[N];
        for(int j=0; j<c; j++){
            diff[0] += Math.abs(s.charAt(j) - t.charAt(j));
        }
        if(diff[0]<=maxCost){
            return true;
        }
        for(int i=1; i<N; i++){
            if(i+c>N){
                continue;
            }
            diff[i] = diff[i-1] + Math.abs(s.charAt(i+c-1) - t.charAt(i+c-1)) - Math.abs(s.charAt(i-1) - t.charAt(i-1));;
            if(diff[i]<=maxCost){
                return true;
            }
        }
        return false;   
    }
}

//sliding window
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int N = s.length();
        int start = 0;
        int max = 0;
        int cost = 0;
        
        for(end=0; end<N; end++){
            cost += Math.abs(s.charAt(end) - t.charAt(end));
            while(cost>maxCost){
                cost -= Math.abs(s.charAt(start) - t.charAt(start));
                start++;
            }
            max = Math.max(max, end - start + 1);
        }
        
        return max;
    }
}
