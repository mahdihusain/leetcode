Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized and return the distance.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

If no land or water exists in the grid, return -1.

 

Example 1:



Input: [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: 
The cell (1, 1) is as far as possible from all the land with distance 2.
Example 2:



Input: [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: 
The cell (2, 2) is as far as possible from all the land with distance 4.
 

Note:

1 <= grid.length == grid[0].length <= 100
grid[i][j] is 0 or 1

//parallel bfs
class Solution {
    
    int[][] directions = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    
    public int maxDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        LinkedList<int[]> q = new LinkedList<int[]>();
        boolean[][] visited = new boolean[m][n];
        
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j]==1) {
                    visited[i][j] = true;
                    q.addLast(new int[]{i,j});
                }
            }
        }
        
        if(q.size()==m*n) {
            return -1;
        }
        
        int level = -1;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int k=0; k<size; k++) {
                int[] ij = q.removeFirst();
                int i = ij[0], j = ij[1];
                for(int[] dir: directions) {
                    int newI = i + dir[0], newJ = j + dir[1];
                    if(newI>=0 && newI<m && newJ>=0 && newJ<n && !visited[newI][newJ]) {
                        visited[newI][newJ] = true;
                        q.addLast(new int[]{newI, newJ});
                    }
                }
            }
            level++;
        }
        
        return level;
    }
    
}
