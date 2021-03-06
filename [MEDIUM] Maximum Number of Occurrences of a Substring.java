Given a string s, return the maximum number of ocurrences of any substring under the following rules:

The number of unique characters in the substring must be less than or equal to maxLetters.
The substring size must be between minSize and maxSize inclusive.
 

Example 1:

Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
Output: 2
Explanation: Substring "aab" has 2 ocurrences in the original string.
It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
Example 2:

Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
Output: 2
Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
Example 3:

Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
Output: 3
Example 4:

Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
Output: 0
 

Constraints:

1 <= s.length <= 10^5
1 <= maxLetters <= 26
1 <= minSize <= maxSize <= min(26, s.length)
s only contains lowercase English letters.

class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        int n = s.length(), max = 0;
        
        for(int i=0; i<n-minSize+1; i++) {
            String sub = s.substring(i, i+minSize);
            if(unique(sub)<=maxLetters) {
                map.put(sub, map.getOrDefault(sub, 0)+1);
                max = Math.max(max, map.get(sub));
            }
        }
        
        return max;
    }
    
    int unique(String str) {
        Set<Character> set = new HashSet<Character>();
        int n = str.length();
        for(int i=0; i<n; i++) {
            set.add(str.charAt(i));
        }
        return set.size();
    }
    
}
