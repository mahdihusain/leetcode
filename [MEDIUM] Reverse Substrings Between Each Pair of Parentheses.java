You are given a string s that consists of lower case English letters and brackets. 

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets.

 

Example 1:

Input: s = "(abcd)"
Output: "dcba"
Example 2:

Input: s = "(u(love)i)"
Output: "iloveu"
Explanation: The substring "love" is reversed first, then the whole string is reversed.
Example 3:

Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
Example 4:

Input: s = "a(bcdefghijkl(mno)p)q"
Output: "apmnolkjihgfedcbq"
 

Constraints:

0 <= s.length <= 2000
s only contains lower case English characters and parentheses.
It's guaranteed that all parentheses are balanced.

//O(n^2)
class Solution {
    public String reverseParentheses(String s) {
        LinkedList<Character> stack = new LinkedList();
        int N = s.length();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            char c = s.charAt(i);
            if(c==')'){
                char top = stack.pop();
                sb.setLength(0);
                while(top!='('){
                    sb.append(top);
                    top = stack.pop();
                }
                String rev = sb.toString();
                for(int j=0; j<rev.length(); j++){
                    stack.push(rev.charAt(j));
                }
            }else{
                stack.push(c);
            }
        }
        sb.setLength(0);
        Iterator<Character> it = stack.descendingIterator();
        while(it.hasNext()){
            sb.append(it.next());
        }
        return sb.toString();
    }
}

//O(n)
class Solution {
    public String reverseParentheses(String s) {
        int N = s.length();
        int[] paren = new int[N];
        LinkedList<Integer> stack = new LinkedList();
        
        for(int i=0; i<N; i++){
            char c = s.charAt(i);
            if(c=='('){
                stack.push(i);
            }else if(c==')'){
                int left = stack.pop();
                paren[i] = left;
                paren[left] = i;
            }
        }
        
        int i;
        int d = 1;
        StringBuilder sb = new StringBuilder();
        for(i=0; i<N; i+=d){
            char c = s.charAt(i);
            if(c=='(' || c==')'){
                i = paren[i];
                d = -d;
            }else{
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}
