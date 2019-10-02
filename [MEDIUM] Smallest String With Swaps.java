You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.

You can swap the characters at any pair of indices in the given pairs any number of times.

Return the lexicographically smallest string that s can be changed to after using the swaps.

 

Example 1:

Input: s = "dcab", pairs = [[0,3],[1,2]]
Output: "bacd"
Explaination: 
Swap s[0] and s[3], s = "bcad"
Swap s[1] and s[2], s = "bacd"
Example 2:

Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
Output: "abcd"
Explaination: 
Swap s[0] and s[3], s = "bcad"
Swap s[0] and s[2], s = "acbd"
Swap s[1] and s[2], s = "abcd"
Example 3:

Input: s = "cba", pairs = [[0,1],[1,2]]
Output: "abc"
Explaination: 
Swap s[0] and s[1], s = "bca"
Swap s[1] and s[2], s = "bac"
Swap s[0] and s[1], s = "abc"
 

Constraints:

1 <= s.length <= 10^5
0 <= pairs.length <= 10^5
0 <= pairs[i][0], pairs[i][1] < s.length
s only contains lower case English letters.

class Solution {
    
    int[] cc;
    int[] size;
    
    int findRoot(int p){
        while(p != cc[p]){
            cc[p] = cc[cc[p]];
            p = cc[p];
        }
        return p;
    }
    
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int N = s.length();
        cc = new int[N];
        size = new int[N];
        for(int i=0; i<N; i++){
            cc[i] = i;
            size[i] = 1;
        }
        for(List<Integer> pair: pairs){
            int first = pair.get(0);
            int second = pair.get(1);
            int rootf = findRoot(first);
            int roots = findRoot(second);
            if(rootf!=roots){
                if(size[rootf] < size[roots]){
                    cc[rootf] = roots;
                    size[roots] += size[rootf];
                }else{
                    cc[roots] = rootf;
                    size[rootf] += size[roots];
                }
            }
        }
        Map<Integer, List<Character>> ccMap = new HashMap();
        for(int i=0; i<N; i++){
            int root = findRoot(i);
            List<Character> cList = ccMap.computeIfAbsent(root, (k) -> new LinkedList());
            cList.add(s.charAt(i));
        }
        for(List<Character> cList: ccMap.values()){
            Collections.sort(cList);
        }
        Map<Integer, Integer> counter = new HashMap();
        StringBuilder sb = new StringBuilder(N);
        for(int i=0; i<N; i++){
            int root = findRoot(i);
            List<Character> cList = ccMap.get(root);
            char c = cList.remove(0);
            sb.append(c);
        }
        return sb.toString();
    }
}
