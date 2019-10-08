Given an integer array arr and an integer k, modify the array by repeating it k times.

For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].

Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.

As the answer can be very large, return the answer modulo 10^9 + 7.

 

Example 1:

Input: arr = [1,2], k = 3
Output: 9
Example 2:

Input: arr = [1,-2,1], k = 5
Output: 2
Example 3:

Input: arr = [-1,-2], k = 7
Output: 0
 

Constraints:

1 <= arr.length <= 10^5
1 <= k <= 10^5
-10^4 <= arr[i] <= 10^4

//kadane and sliding window solution
class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        long modulo = 1_000_000_007;
        long sw = (long) kadanesAlgo(arr);
        // long sw = (long) slideWindow(arr);
        
        if(k==1){
            return (int) sw;
        }
        
        long sum = (long) sum(arr);
        long left = (long) slideLeft(arr);
        long right = (long) slideRight(arr);
        
        return (int) Math.max(sw, ((k-2)*sum + left + right) % modulo);
    }
    
    int slideWindow(int[] arr){
        int left = 0;
        int right;
        int sum = 0;
        int csum = 0;
        
        for(right=0; right< arr.length; right++){
            csum += arr[right];
            while(csum < 0){
                csum -= arr[left];
                left++;
            }
            
            if(sum<csum){
                sum = csum;
            }
        }
        return sum;
    }
    
    int kadanesAlgo(int[] arr){
        int sum = 0;
        int csum = 0;
        
        for(int i=0; i<arr.length; i++){
            csum += arr[i];
            if(csum > sum){
                sum = csum;
            }else if(csum<0){
                csum = 0;
            }
        }
        return sum;
    }
    
    int slideLeft(int[] arr){
        int start;
        int end = arr.length-1;
        int csum = 0;
        int sum = 0;
        
        for(start=end; start>=0; start--){
            csum += arr[start];
            if(csum > sum){
                sum = csum;
            }
        }
        return sum;
    }

    int slideRight(int[] arr){
        int start = 0;
        int end;
        int csum = 0;
        int sum = 0;
        
        for(end=start; end<arr.length; end++){
            csum += arr[end];
            if(csum > sum){
                sum = csum;
            }
        }
        return sum;
    }
    
    int sum(int[] arr){
        int sum = 0;
        for(int el: arr){
            sum += el;
        }
        if(sum<0){
            sum = 0;
        }
        return sum;
    }
    
}
