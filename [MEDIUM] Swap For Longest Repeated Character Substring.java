Given a string text, we are allowed to swap two of the characters in the string. Find the length of the longest substring with repeated characters.

 

Example 1:

Input: text = "ababa"
Output: 3
Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated character substring is "aaa", which its length is 3.
Example 2:

Input: text = "aaabaaa"
Output: 6
Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa", which its length is 6.
Example 3:

Input: text = "aaabbaaa"
Output: 4
Example 4:

Input: text = "aaaaa"
Output: 5
Explanation: No need to swap, longest repeated character substring is "aaaaa", length is 5.
Example 5:

Input: text = "abcdef"
Output: 1
 

Constraints:

1 <= text.length <= 20000
text consist of lowercase English characters only.

class Solution {
    public int maxRepOpt1(String text) {
        int[] count = new int[26];
        List<int[]> repeat = new ArrayList<int[]>(text.length());
        
        char current = text.charAt(0);
        int len = 0;
        
        for (int i=0; i<text.length(); i++) {
            count[text.charAt(i)-'a']++;
            if (text.charAt(i)==current) {
                len++;
            } else {
                repeat.add(new int[]{current-'a', len});
                len = 1;
                current = text.charAt(i);
            }
        }
        repeat.add(new int[]{current-'a', len});
        
        int max = 1;
        for(int i=0; i<repeat.size(); i++) {
            len = len(repeat, i);
            if (len<count[repeat.get(i)[0]]) {
                len++;
            }
            max = Math.max(max, len);
        }
        
        for (int i=1; i<repeat.size()-1; i++) {
            if (repeat.get(i)[1]==1 && repeat.get(i-1)[0]==repeat.get(i+1)[0]) {
                len = len(repeat, i-1) + len(repeat, i+1);
                if (len<count[repeat.get(i-1)[0]]) {
                    len++;
                }
                max = Math.max(max, len);
            }
        }
        return max;
    }
    
    int len(List<int[]> repeat, int i) {
        return repeat.get(i)[1];
    }
}
