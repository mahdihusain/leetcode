Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.

Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.

 

Example 1:

Input: queries = ["cbd"], words = ["zaaaz"]
Output: [1]
Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
Example 2:

Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
Output: [1,2]
Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
 

Constraints:

1 <= queries.length <= 2000
1 <= words.length <= 2000
1 <= queries[i].length, words[i].length <= 10
queries[i][j], words[i][j] are English lowercase letters.

class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = queries.length;
        int[] answer = new int[n];
        int[] wordsf = new int[words.length];
        
        for(int i=0; i<words.length; i++) {
            wordsf[i] = f(words[i]);
        }
        
        Arrays.sort(wordsf);
        
        for(int i=0; i<n; i++) {
            int fi = f(queries[i]);
            int startIndex = words.length, left = 0, right = words.length-1;
            while(left<=right) {
                int mid = left + (right-left)/2;
                if(fi<wordsf[mid]) {
                    startIndex = mid;
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            }
            answer[i] = words.length-startIndex;
        }
        return answer;
    }
    
    int f(String s) {
        int freq = 0;
        char smallest = 'z';
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i)<smallest) {
                smallest = s.charAt(i);
                freq = 1;
            } else if (s.charAt(i)==smallest) {
                freq++;
            }
        }
        return freq;
    }
}
