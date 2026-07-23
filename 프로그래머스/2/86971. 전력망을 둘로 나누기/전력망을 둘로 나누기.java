import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = 1000;
       
        
        //-------------------
        // 모든 wire에 대해서 하나하나 끊어 보면서 확인하기
        for (int d=0 ; d<wires.length; d++){
            int[] dis = wires[d]; // 이번 턴에 무시할 wire 정해두기 
            
            
            List<List<Integer>> adj = new ArrayList<>();
            for (int a=0; a<=n; a++){
                adj.add(new ArrayList<>()); // 새로운 리스트 더해주기 
            }
        
            for (int w=0; w<wires.length; w++){ 
                if (w==d) continue; // 만약 끊을 전선이라면, 아래 단계 건너뛰기
                
                int[] wire = wires[w];
                int w1 = wire[0];
                int w2 = wire[1]; 

                adj.get(w1).add(w2);
                adj.get(w2).add(w1);
            }
            
            int currCon = conCheck(1, adj, n);
            int other = n - currCon;
            
            answer = Math.min(Math.abs(currCon - other), answer);
            
        }
        
        return answer;
    }
    
    public int conCheck(int start, List<List<Integer>> adj, int n){
        int connect = 1; // 최소한 start 지점은 포함된거니까 
        boolean[] visited = new boolean[n+1];
        
        Deque<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.add(start);
        
        while(!q.isEmpty()){
            int temp = q.poll();
            
            for (int i=1; i<=n; i++){
                if (!visited[i] && adj.get(temp).contains(i)){
                    connect++;
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
        
        return connect;
        
        
        
    }
}