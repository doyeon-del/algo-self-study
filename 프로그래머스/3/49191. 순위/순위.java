// 이거 위상정렬 느낌인데 방향성이 있으니까 

import java.util.*;
class Solution {
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        // 순위가 확실하다 == 본인보다 약한사람과 강한 사람 모두를 알고 있다.
        List<List<Integer>> stradj = new ArrayList<>();
        List<List<Integer>> wkadj = new ArrayList<>();
        for (int i=0; i<=n; i++){
            stradj.add(new ArrayList<>());
            wkadj.add(new ArrayList<>());
        }
        
        for (int[] r : results){
            stradj.get(r[0]).add(r[1]);
            wkadj.get(r[1]).add(r[0]);
        }
        
        for (int j=1; j<=n; j++){ // 각 노드별로 돌면서 bfs 돌려서
            
            int win = bfs(j, stradj, n);
            int lose = bfs(j, wkadj, n);
            
            if (win + lose == n-1){
                answer++;
            }
            
        }
        
        
        return answer;
    }
    
    public int bfs(int start, List<List<Integer>> adj, int n){
        
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        queue.add(start);
        int count = 0;
        
        while(!queue.isEmpty()){
            
            int cur = queue.poll();
            
            for (int i=1; i<=n; i++){
                if(!visited[i] && adj.get(cur).contains(i)){
                    visited[i] = true; 
                    count++;
                    queue.add(i);
                }
            }
            
            
            
        }
        
        return count;
    }
}