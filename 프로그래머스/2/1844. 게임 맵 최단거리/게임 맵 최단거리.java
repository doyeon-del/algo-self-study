import java.util.*;
class Solution {
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int answer;
    
    public int solution(int[][] maps) {
        answer = -1;
        int n = maps.length;
        int m = maps[0].length;
        // boolean[][] visited = new visited[n][m];
        
        return bfs(0,0, n, m, maps);
    }
    
    public int bfs(int sr, int sc, int er, int ec, int[][] maps){
        
        Deque<int[]> q = new ArrayDeque<>();
        maps[sr][sc] = 0; 
        q.add(new int[] {sr, sc, 1});
        
        while(!q.isEmpty()){
            int[] temp = q.poll();
            int cr = temp[0];
            int cc = temp[1];
            int cl = temp[2];
            
            if (cr == er - 1 && cc == ec - 1){

                return cl; // 바로 종료 
            }
            
            for (int d=0; d<4; d++){
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                
                if (nr >=0 && nc>=0 && nr<er && nc<ec && maps[nr][nc]==1){
                    maps[nr][nc] = 0; // 방문 표시
                    q.add(new int[] {nr, nc, cl + 1});
                }
                
            }
        }
        
        return -1;
        
    }
}