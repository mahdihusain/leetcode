Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.

 

Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
 

Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.

class Solution {
    public int maxLength(List<String> arr) {
        int currentMax = 0;
        List<String> combinations = new LinkedList<String>();
        combinations.add("");
        for(String str: arr) {
            if(!isUnique(str)) {
                continue;
            }
            List<String> newCombinations = new LinkedList<String>();
            StringBuilder sb = new StringBuilder();
            for(String existingCombination: combinations) {
                sb.setLength(0);
                sb.append(str);
                sb.append(existingCombination);
                String union = sb.toString();
                if(isUnique(union)) {
                    newCombinations.add(union);
                    currentMax = Math.max(union.length(), currentMax);
                }
            }
            combinations.addAll(newCombinations);
        }
        return currentMax;
    }
    
    boolean isUnique(String str) {
        if(str.length()>26) {
            return false;
        }
        boolean[] used = new boolean[26];
        for(char c: str.toCharArray()) {
            int index = c-'a';
            if(used[index]){
                return false;
            }
            used[index] = true;
        }
        return true;
    }
}
