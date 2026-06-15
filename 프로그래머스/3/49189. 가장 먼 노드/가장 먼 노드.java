import java.util.*;

class Solution {
    static List<List<Integer>> adj;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        adj = new ArrayList<>();
        
        for (int i=0; i<=n; i++){
            adj.add(new ArrayList<>());
        }
        
        for (int[] e : edge){
            int e1 = e[0];
            int e2 = e[1];
            
            adj.get(e1).add(e2);
            adj.get(e2).add(e1);
        }
        
        boolean[] visited = new boolean[n+1];
        
        answer = bfs(1,0,visited);
        
        return answer;
    }
    
    public int bfs(int start, int maxCount, boolean[] visited){
        
        int maxDepth = 0;
        visited[start] = true;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {start, 1});
        
        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            int cur = temp[0];
            int depth = temp[1];
            
            if (depth > maxDepth){
                maxCount = 1; // 개수 1부터 다시 세주기. 
                maxDepth = depth; // 최대 깊이값 자체도 업데이트
            } else if (depth == maxDepth){
                maxCount++;
            }
            
            for (int i=2; i<= visited.length - 1; i++){
                if(!visited[i] && adj.get(cur).contains(i)){
                    
                    visited[i] = true;
                    queue.add(new int[] {i, depth+1});
                    
                }
            }

        }
        return maxCount;
        
    }
}