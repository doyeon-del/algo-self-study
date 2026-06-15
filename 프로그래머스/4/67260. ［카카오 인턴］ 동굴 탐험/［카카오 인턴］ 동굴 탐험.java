import java.util.*;
class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        
        List<List<Integer>> adj = new ArrayList<>();
        List<List<Integer>> tree = new ArrayList<>();
        
        for (int i=0; i<n; i++){ // n개 방이면 이렇게 등호 빼줘도 되나? 
            adj.add(new ArrayList<>());
            tree.add(new ArrayList<>());
        }
        
        
        for (int[] p : path){
            
            tree.get(p[0]).add(p[1]);
            tree.get(p[1]).add(p[0]);
        }
        
        int[] degree = new int[n];
        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        
        queue.add(0);
        visited[0] = true;
        
        while(!queue.isEmpty()){
            int curr = queue.poll();
            
            for (int next : tree.get(curr)){
                if(!visited[next]){
                    visited[next] = true; 
                    
                    // 현재 값을 방문해야지만 자식 노드로 갈 수 있다? 
                    // 이 관계성을 트리 구조에서 한번 더 정리해주는거임?
                    adj.get(curr).add(next); 
                    degree[next]++;
                    queue.add(next);
                    
                    
                }
            }
            
        }
        
        // ---------------------------
        for (int[] o : order){
            int pre = o[0];
            int post = o[1];
            
            adj.get(pre).add(post);
            degree[post]++;
        }
        
        if(degree[0] > 0) return false;
        
        // 여기서부터 다시 방문 조건 시작.
        queue.add(0);
        int visitCount=0;
        
        while(!queue.isEmpty()){
            int curr = queue.poll();
            visitCount++; 
            
            for (int next : adj.get(curr)){
                degree[next]--;
                if (degree[next]==0){
                    queue.add(next);
                    
                }
            }
            
        }
        
        return visitCount == n;
    }
}