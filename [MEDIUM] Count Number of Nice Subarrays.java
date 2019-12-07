Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.

 

Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There is no odd numbers in the array.
Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16
 

Constraints:

1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length

//binary search
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] prefixOdd = new int[n];
        prefixOdd[0] = nums[0]%2==0?0:1;
        
        for(int i=1; i<n; i++){
            prefixOdd[i] = nums[i]%2==0?prefixOdd[i-1]:prefixOdd[i-1]+1;
        }

        if(prefixOdd[n-1]<k){
            return 0;
        }
        
        int counter = 0;
        int index = Arrays.binarySearch(prefixOdd, k);
        if(index>=0){
            counter += adjacent(prefixOdd, index);
        }
        
        int j = 0;
        for(j=0; j<n; j++){
            index = Arrays.binarySearch(prefixOdd, j+1, n, prefixOdd[j]+k);
            if(index<0){
                continue;
            }
            counter += adjacent(prefixOdd, index);
        }
        return counter;
    }
    
    int adjacent(int[] arr, int index) {
        int result = 0;
        //left
        for(int i=index-1; i>=0; i--) {
            if(arr[index]!=arr[i]) {
                break;
            }
            result++;
        }
        //right
        for(int i=index+1; i<arr.length; i++) {
            if(arr[index]!=arr[i]) {
                break;
            }
            result++;            
        }
        return result+1;
    }
}

//sliding window
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k-1);
    }
    
    public int atMost(int[] nums, int k) {
        int i = 0, j = 0, kcounter = 0, res = 0, n = nums.length;
        for(j=0; j<n; j++) {
            kcounter += nums[j]%2;
            while(i<=j && kcounter>k) {
                kcounter -= nums[i++]%2;
            }
            res += j - i + 1;
        }
        return res;
    }
    
}
