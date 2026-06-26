import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = 10000;
        
        for (int cut = 0; cut<wires.length; cut++){ // 어떤 전력망을 끊을 것인지 고르기? 
                    
            List<List<Integer>> adj = new ArrayList<>();
            
            for (int i=0; i<=n; i++){
                adj.add(new ArrayList<>());
            }

            for (int w = 0; w<wires.length ; w++){
                if (cut==w) continue; // 해당 부분은 표시안해주기 
                    
                int w1 = wires[w][0];
                int w2 = wires[w][1];
                adj.get(w1).add(w2);
                adj.get(w2).add(w1);
            } 
        
            int c = connect(1,adj,n);
            int notC = n - c; 
            
            answer = Math.min(answer, Math.abs(c - notC));
      
        }
        
        return answer;
    }
    
    public int connect(int start, List<List<Integer>> adj, int n){
        
        int count = 1;
        Deque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        
        q.add(start);
        visited[start] = true;
        
        while(!q.isEmpty()){
            int temp = q.poll();
            
            for (int num=2; num<visited.length; num++){
                if (adj.get(temp).contains(num) && !visited[num]){
                    visited[num] = true;
                    count++;
                    q.add(num);
                }
            }
            
            
        }
        
        return count;
        
            
            
    }
}