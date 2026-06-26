import java.util.*;

class Solution {
    static int answer;
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        int n = dungeons.length;
        boolean[] visited = new boolean[n];
        // 어떤 던전을 먼저 돌 것인가
        dfs(0, k, dungeons, visited);
        
        return answer;
    }
    
    public void dfs(int index, int hp, int[][] dungeons, boolean[] visited){
        
        answer = Math.max(answer, index);
        
        // 순열이니까 0부터 시작해야함
        for (int i = 0; i < dungeons.length; i++){
            
            if (!visited[i] && dungeons[i][0] <= hp){
                visited[i] = true;
                
                dfs(index+1, hp - dungeons[i][1], dungeons, visited);
                visited[i] = false;
            }
            
            
        }
        
        
    }
}