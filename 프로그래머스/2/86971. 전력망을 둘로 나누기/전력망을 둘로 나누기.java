import java.util.*;
class Solution {
    static int answer; // 최대 차이가 n이니가 
    public int solution(int n, int[][] wires) {
        
        answer = n;
        
        // 존재하는 wire 들 중에 어떤 선을 끊을 것인가? 
        
        for (int w=0; w<wires.length; w++){ // 끊어줄 와이어 번호
            
            List<List<Integer>> adj = new ArrayList<>();
            for (int l=0; l<=n; l++){
                adj.add(new ArrayList<>());
            }
            
            // 인접리스트에 wire 추가해주기
            for (int i=0; i<wires.length; i++){
                if (i==w) continue; // 이번 기회에 끊는 wire는 패스 
                
                int w1 = wires[i][0];
                int w2 = wires[i][1];
                
                adj.get(w1).add(w2);
                adj.get(w2).add(w1);
            }
            
            // 현재의 인접리스트 바탕으로 bfs 탐색 진행
            boolean[] visited = new boolean[n+1];
            int diff = bfs(1, adj, visited);
            answer = Math.min(diff, answer);
            
        }

        return answer;
    }
    
    public int bfs(int start, List<List<Integer>> adj, boolean[] visited){
        int connect = 1; // 최소 1개 이상 연결되니까
        visited[start] = true;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        
        while(!queue.isEmpty()){
            int current = queue.poll(); 
            for (int c : adj.get(current)){ // 리스트 돌면서 큐에다가 넣기
                if (!visited[c]){
                    visited[c] = true; 
                    connect++;
                    queue.add(c); // 다음 값 넣어주고 ㄱ
                }
                
            }
        }
        
        // 연결된 노드 값 기준으로 차이 구하기 
        int diff = Math.abs(visited.length - 1 - 2*connect);
        return diff;
        
    }
}