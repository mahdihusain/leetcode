Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.

 

Example 1:



Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).
Example 2:



Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1
Example 3:

Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2
 

Constraints:

1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1

class Solution {
    int n = 0;
    int m = 0;
    public int closedIsland(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int counter = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(i==0 || j==0 || i==n-1 || j==m-1) {
                    fill(grid, i, j);
                }
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(grid[i][j]==0) {
                    counter++;
                    fill(grid, i, j);
                }
            }
        }
        return counter;
    }
    
    void fill(int[][] grid, int i, int j) {
        if(i<0 || i>n-1 || j<0 || j>m-1 || grid[i][j]==1) {
            return;
        }
        grid[i][j]=1;
        fill(grid, i-1, j);
        fill(grid, i, j-1);
        fill(grid, i+1, j);
        fill(grid, i, j+1);
    }
}
