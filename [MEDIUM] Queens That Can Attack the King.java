On an 8x8 chessboard, there can be multiple Black Queens and one White King.

Given an array of integer coordinates queens that represents the positions of the Black Queens, and a pair of coordinates king that represent the position of the White King, return the coordinates of all the queens (in any order) that can attack the King.

 

Example 1:



Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
Output: [[0,1],[1,0],[3,3]]
Explanation:  
The queen at [0,1] can attack the king cause they're in the same row. 
The queen at [1,0] can attack the king cause they're in the same column. 
The queen at [3,3] can attack the king cause they're in the same diagnal. 
The queen at [0,4] can't attack the king cause it's blocked by the queen at [0,1]. 
The queen at [4,0] can't attack the king cause it's blocked by the queen at [1,0]. 
The queen at [2,4] can't attack the king cause it's not in the same row/column/diagnal as the king.
Example 2:



Input: queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
Output: [[2,2],[3,4],[4,4]]
Example 3:



Input: queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
Output: [[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]
 

Constraints:

1 <= queens.length <= 63
queens[0].length == 2
0 <= queens[i][j] < 8
king.length == 2
0 <= king[0], king[1] < 8
At most one piece is allowed in a cell.

class Solution {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        int N = queens.length;
        List<List<Integer>> attack = new ArrayList(8);
        int i = king[0];
        int j = king[1];
        
        //0,1,2
        //3,4
        //5,6,7
        int[] direction = new int[8];
        for(int k=0; k<8; k++){
            direction[k] = 100;
        }
        
        int[][] currAttack = new int[8][2];
        
        for(int[] queen: queens){
            Integer qi = queen[0];
            Integer qj = queen[1];
            
            if(qi==i){
                if(qj>j){
                    check(i, j, qi, qj, direction, currAttack, 4, queen);
                }else{
                    check(i, j, qi, qj, direction, currAttack, 3, queen);
                }
            } else if(qj==j){
                if(qi>i){
                    check(i, j, qi, qj, direction, currAttack, 6, queen);
                }else{
                    check(i, j, qi, qj, direction, currAttack, 1, queen);
                }
            } else if(qi-i==qj-j){
                if(qi>i){
                    check(i, j, qi, qj, direction, currAttack, 7, queen);
                }else{
                    check(i, j, qi, qj, direction, currAttack, 0, queen);
                }
            } else if(qi-i==j-qj){
                if(qi>i){
                    check(i, j, qi, qj, direction, currAttack, 5, queen);
                }else{   
                    check(i, j, qi, qj, direction, currAttack, 2, queen);
                }
            }
        }
        
        for(int k=0; k<8; k++){
            if(direction[k]<100){
                attack.add(Arrays.asList(currAttack[k][0], currAttack[k][1]));
            }
        }
        
        return attack;
    }
    
    void check(int i, int j, int qi, int qj, int[] direction, int[][] currAttack, int dir, int[] queen){
            int dist = dist(i,j,qi,qj);
            if(direction[dir] > dist){
                direction[dir] = dist;
                currAttack[dir] = queen;
            }
    }
    
    int dist(int i, int j, int qi, int qj){
        return Math.abs(qi-i) + Math.abs(qj-j);
    }
    
}
