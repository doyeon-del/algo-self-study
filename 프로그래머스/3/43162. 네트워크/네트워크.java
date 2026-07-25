import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[computers.length];
        int answer = 0;
        for (int c=0; c<computers.length; c++){
            
            if(!visited[c]){
                visited[c] = true;
                answer++;
                findCon(c, computers, visited);
            }
            
        }
        
        
        return answer;
    }
    
    public void findCon(int start, int[][] computers, boolean[] visited) {
        Deque<Integer> q = new ArrayDeque<>();
        q.add(start);
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for (int i=0; i<computers.length; i++){
                if (!visited[i] && computers[cur][i] == 1){
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
        
    }
}