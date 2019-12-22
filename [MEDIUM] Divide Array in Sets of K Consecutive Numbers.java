Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
Return True if its possible otherwise return False.

 

Example 1:

Input: nums = [1,2,3,3,4,4,5,6], k = 4
Output: true
Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
Example 2:

Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
Output: true
Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
Example 3:

Input: nums = [3,3,2,2,1,1], k = 3
Output: true
Example 4:

Input: nums = [1,2,3,4], k = 3
Output: false
Explanation: Each array should be divided in subarrays of size 3.
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= k <= nums.length

class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if(k==1) {
            return true;
        }
        
        int n = nums.length;
        if(n%k!=0) {
            return false;
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        
        for(int num: nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        
        for(int key: map.keySet()) {
            pq.add(key);
        }
        
        while(!pq.isEmpty()) {
            int num = pq.poll();
            if(map.get(num)==0) {
                continue;
            }
            int freq = map.get(num);
            for(int i=0; i<k; i++) {
                if(!map.containsKey(num+i) || map.get(num+i)<freq) {
                    return false;
                }
                map.put(num+i, map.get(num+i)-freq);
            }
            n -= k*freq;
        }
        return true;
    }
}
