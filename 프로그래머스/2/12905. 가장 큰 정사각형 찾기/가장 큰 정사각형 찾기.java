import java.util.*;

class Solution
{
    public int solution(int [][]board) {
        
        int maxLen = 0;
        
        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[0].length; j++){
                
                if (board[i][j]==1) maxLen = 1;
                
            }
        }
        
        for (int r=1; r<board.length; r++){
            for (int c=1; c<board[0].length; c++){
                if (board[r][c]==1){
                    int minValue = Math.min(board[r-1][c], board[r][c-1]);
                    minValue = Math.min(minValue, board[r-1][c-1]);
                    
                    board[r][c] = minValue + 1;
                    maxLen = Math.max(board[r][c], maxLen);
                }
            }
        }

        return maxLen * maxLen;
    }
    
}