Given a string s of '(' , ')' and lowercase English characters. 

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 

Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
Example 4:

Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"

class Solution {
    public String minRemoveToMakeValid(String s) {
        int n = s.length();
        Stack<Integer> stack = new Stack<Integer>();
        char[] arr = s.toCharArray();
        boolean[] exclude = new boolean[n];
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<n; i++) {
            if(arr[i]=='(') {
                stack.push(i);
            } else if(arr[i]==')') {
                if(!stack.isEmpty()) {
                    stack.pop();
                } else {
                    exclude[i] = true;
                }
            }
        }

        while(!stack.isEmpty()) {
            exclude[stack.pop()] = true;
        }

        for(int i=0; i<n; i++) {
            if(!exclude[i]){
                sb.append(arr[i]);
            }
        }

        return sb.toString();
    }
}
