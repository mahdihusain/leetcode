Given an array nums of integers, a move consists of choosing any element and decreasing it by 1.

An array A is a zigzag array if either:

Every even-indexed element is greater than adjacent elements, ie. A[0] > A[1] < A[2] > A[3] < A[4] > ...
OR, every odd-indexed element is greater than adjacent elements, ie. A[0] < A[1] > A[2] < A[3] > A[4] < ...
Return the minimum number of moves to transform the given array nums into a zigzag array.

 

Example 1:

Input: nums = [1,2,3]
Output: 2
Explanation: We can decrease 2 to 0 or 3 to 1.
Example 2:

Input: nums = [9,6,1,6,2]
Output: 4
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 1000

class Solution {
    public int movesToMakeZigzag(int[] nums) {
        int left = 0, right = 0, n = nums.length;
        int[] res = new int[2];
        
        if(n==1) {
            return 0;
        }
        
        for (int i=0; i<n; i++) {
            left = i>0 ? nums[i-1] : 1001;
            right = i<n-1 ? nums[i+1] : 1001;
            res[i%2] += Math.max(0, nums[i] - Math.min(left, right) + 1);
        }
        
        return Math.min(res[0], res[1]);
    }
    
}
