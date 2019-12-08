Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.

 

Example 1:

Input: nums = [3,6,5,1,8]
Output: 18
Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
Example 2:

Input: nums = [4]
Output: 0
Explanation: Since 4 is not divisible by 3, do not pick any number.
Example 3:

Input: nums = [1,2,3,4,4]
Output: 12
Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
 

Constraints:

1 <= nums.length <= 4 * 10^4
1 <= nums[i] <= 10^4

class Solution {
    public int maxSumDivThree(int[] nums) {
        int sum = 0;
        int[] min1 = new int[] {10001, 10001};
        int[] min2 = new int[] {10001, 10001};
        
        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            int mod = nums[i]%3;
            if(mod==1){
                updateMin(min1, nums[i]);
            }else if(mod==2){
                updateMin(min2, nums[i]);
            }
        }
        
        int mod = sum%3;
        if(mod==0){
            return sum;
        }else if(mod==1){
            int min2Sum = min2[0]+min2[1];
            if(min2Sum<min1[0]) {
                return sum - min2Sum;
            }
            return sum-min1[0];
        }else{
            int min1Sum = min1[0]+min1[1];
            if(min1Sum<min2[0]) {
                return sum - min1Sum;
            }
            return sum-min2[0];
        }
    }
    
    void updateMin(int[] min, int num) {
        if(num<min[0]) {
            min[1] = min[0];
            min[0] = num;
        } else if (num<min[1]) {
            min[1] = num;
        }
    }
    
}

//dp solution
class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] dp = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        
        for(int num: nums) {
            int[] dp2 = new int[3];
            for(int i=0; i<3; i++) {
                dp2[(num+i)%3] = Math.max(dp[(num+i)%3], dp[i]+num);
            }
            dp = dp2;
        }
        return dp[0];
    }
}
