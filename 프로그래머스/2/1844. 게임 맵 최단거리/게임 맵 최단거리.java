import java.util.*;

class Spot {
    
    int r;
    int c;
    int len;
    
    public Spot(int r, int c, int len){
        this.r = r;
        this.c = c;
        this.len = len;
    }
    
}


class Solution {
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    
    public int solution(int[][] maps) {
        
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];
        
        
        return bfs(maps, visited, n, m);
    }
    
    public int bfs(int[][] maps, boolean[][] visited, int r, int c){
        
        visited[0][0] = true;
        Deque<Spot> q = new ArrayDeque<>();
        q.add(new Spot(0,0,1));
        
        while(!q.isEmpty()){
            Spot cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;
            int clen = cur.len;
            
            if (cr == r-1 && cc == c-1) return clen;
            
            for (int d=0; d<4; d++){
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                
                if (nr >=0 && nc>=0 && nc<c && nr<r){
                    if(!visited[nr][nc] && maps[nr][nc] == 1){
                        visited[nr][nc] = true;
                        q.add(new Spot(nr, nc, clen+1));
                    }
                }
                
            }
            
        }
        
        
        return -1; 
    }
}