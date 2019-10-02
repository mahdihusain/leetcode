Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made.

It is guaranteed that the answer is unique.

 

Example 1:

Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.
Example 2:

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
Example 3:

Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"
 

Constraints:

1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lower case English letters.

class Solution {
    public String removeDuplicates(String s, int k) {
        LinkedList<Character> charL = new LinkedList();
        LinkedList<Integer> freqL = new LinkedList();
        char[] charArr = s.toCharArray();
        for(char c: charArr){
            if(charL.isEmpty()){
                charL.push(c);
                freqL.push(1);
            }else{
                if(charL.peek()==c){
                    if(freqL.peek()==k-1){
                        charL.pop();
                        freqL.pop();
                    }else{
                        int freq = freqL.pop();
                        freqL.push(freq+1);
                    }
                }else{
                    charL.push(c);
                    freqL.push(1);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Character> itc = charL.descendingIterator();
        Iterator<Integer> itf = freqL.descendingIterator();
        while(itc.hasNext()){
            char c = itc.next();
            int freq = itf.next();
            for(int i=0; i<freq; i++){
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
