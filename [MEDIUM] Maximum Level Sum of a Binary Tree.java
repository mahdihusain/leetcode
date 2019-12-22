Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level X such that the sum of all the values of nodes at level X is maximal.

 

Example 1:



Input: [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
 

Note:

The number of nodes in the given tree is between 1 and 10^4.
-10^5 <= node.val <= 10^5

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    int[] map = new int[10_000];
    int leafLevel = 1;
    
    public int maxLevelSum(TreeNode root) {
        traverse(root, 1);
        int maxSum = Integer.MIN_VALUE, maxLevel = 1;
        for(int i=1; i<=leafLevel; i++) {
            if(maxSum<map[i]) {
                maxSum = map[i];
                maxLevel = i;
            }
        }
        return maxLevel;
    }
    
    void traverse(TreeNode node, int level) {
        if(node==null) {
            leafLevel = Math.max(leafLevel, level-1);
            return;
        }
        map[level] += node.val;
        traverse(node.left, level+1);
        traverse(node.right, level+1);
    }
    
}
