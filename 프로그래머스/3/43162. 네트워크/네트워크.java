import java.util.*;
class Solution {
    static int answer;
    
    public int solution(int n, int[][] computers) {
        answer = 0;
        
        // 인접 리스트 만들 필요도 없이 인접행렬로 이미 다 표시되어 있음. 
        boolean[] visited = new boolean[computers.length];
        
        for (int i=0; i<computers.length; i++){
            if (!visited[i]){
                
                bfs(i, visited, computers);
                answer++;
            }
        }
        
        return answer;
    }
    
    public void bfs(int start, boolean[] visited, int[][] computers){
        
        visited[start] = true;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start); 
        
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for (int i=0; i<computers[cur].length; i++){
                if (computers[cur][i]==1 && !visited[i]){
                    visited[i] = true;
                    queue.add(i);
                    
                }
            }
            
        }
        
        
    }
}