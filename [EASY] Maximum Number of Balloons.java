Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.

You can use each character in text at most once. Return the maximum number of instances that can be formed.

 

Example 1:



Input: text = "nlaebolko"
Output: 1
Example 2:



Input: text = "loonbalxballpoon"
Output: 2
Example 3:

Input: text = "leetcode"
Output: 0
 

Constraints:

1 <= text.length <= 10^4
text consists of lower case English letters only.

class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] freq = new int[26];
        int N = text.length();
        
        for(int i=0; i<N; i++){
            int c = text.charAt(i) - 'a';
            freq[c]++;
        }
        
        int max1 = Math.min(freq[0], freq[1]);
        int max2 = Math.min(freq[11]/2, freq[14]/2);
        int max3 = Math.min(max1, max2);
        return Math.min(max3, freq[13]);
    }
}
