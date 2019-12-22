Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.

 

Example 1:


Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
Output: 2
Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
Example 2:

Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
Output: 0
Example 3:

Input: mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
Output: 3
Example 4:

Input: mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
Output: 2
 

Constraints:

1 <= m, n <= 300
m == mat.length
n == mat[i].length
0 <= mat[i][j] <= 10000
0 <= threshold <= 10^5

//binary search and prefix sum
class Solution {
    int m,n;
    
    public int maxSideLength(int[][] mat, int threshold) {
        m = mat.length;
        n = mat[0].length;
        int[][] sum = new int[m+1][n+1];
        
        for(int i=1; i<m+1; i++) {
            for(int j=1; j<n+1; j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + mat[i-1][j-1];
            }
        }
        
        int right = Math.min(m,n);
        int left = 1;
        int ans = 0;
        while(left<=right) {
            int mid = left + (right-left)/2;
            if(isSquare(sum, threshold, mid)) {
                ans = mid;
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return ans;
    }
    
    boolean isSquare(int[][] sum, int threshold, int len) {
        for(int i=len; i<=m; i++) {
            for(int j=len; j<=n; j++) {
                if(sum[i][j] - sum[i-len][j] - sum[i][j-len] + sum[i-len][j-len] <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }
    
}

//sliding window
class Solution {
    int m,n;
    
    public int maxSideLength(int[][] mat, int threshold) {
        m = mat.length;
        n = mat[0].length;
        int[][] sum = new int[m+1][n+1];
        int len = 1;
        int ans = 0;
        
        for(int i=1; i<m+1; i++) {
            for(int j=1; j<n+1; j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + mat[i-1][j-1];
                if(i>=len && j>=len && sum[i][j]-sum[i-len][j]-sum[i][j-len]+sum[i-len][j-len]<=threshold) {
                    ans = len;
                    len++;
                }
            }
        }
        
        return ans;
    }
    
}
