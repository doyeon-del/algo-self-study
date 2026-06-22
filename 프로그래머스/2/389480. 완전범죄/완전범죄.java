import java.util.*;

class Solution {
    
    static int maxA;
    static int maxB;
    static int[][] memo;
    

    public int solution(int[][] info, int n, int m) {
        
        maxA=n;
        maxB=m;
        
        memo = new int[info.length][m];
        
        // 2의 k승 해서 가능한 모든 경우를 봐야하나 봄. 
        
        for (int i=0; i<info.length; i++){
            for (int j=0; j<m; j++){
                memo[i][j] = -1;
            }
        }
                
        int answer = dfs(0, 0, info);
        
        if (answer >= n) return -1;
        
        return answer;
    }
    
    public int dfs(int index, int sumB, int[][] info){
        
        if (sumB >= maxB){
            return 1000000;
        }
        
        if (index == info.length){
            return 0;
        }
        
        if (memo[index][sumB] != -1){
            return memo[index][sumB];
        }
        
        int chooseA = info[index][0] + dfs(index+1, sumB, info);
        
        int chooseB = dfs(index + 1, sumB + info[index][1], info);
        
        return memo[index][sumB] = Math.min(chooseA, chooseB);
        
        
    }
    
    
}