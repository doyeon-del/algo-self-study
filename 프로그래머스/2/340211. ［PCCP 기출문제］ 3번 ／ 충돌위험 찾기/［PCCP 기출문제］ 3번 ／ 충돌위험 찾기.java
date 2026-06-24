import java.util.*;

class Solution {
    static int[] dr ={-1,1,0,0};
    static int[] dc ={0,0,-1,1};
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        List<List<int[]>> allRobotRoute = new ArrayList<>();
        
        int maxLen = 0;
        
        for (int[] r : routes){
            
            List<int[]> path = new ArrayList<>();
            
            int startP = r[0];
            int endP = r[1];
            
            int sr = points[startP-1][0];
            int sc = points[startP-1][1];
            
            path.add(new int[] {sr, sc}); 
            
            
            for (int i=1; i<r.length; i++){
                
                int nextP = r[i];
                
                int er = points[nextP-1][0];
                int ec = points[nextP-1][1];
                
                
                while(sr != er){
                if (sr < er) sr++;
                else sr--;
                path.add(new int[] {sr, sc});
                }
            
                while (sc != ec) {
                    if (sc < ec) sc++; 
                    else sc--;
                    path.add(new int[] {sr, sc});
                }
            }
            
            
            allRobotRoute.add(path);
            maxLen = Math.max(maxLen, path.size());
        }
        
        
        // 리스트 안의 최대 길이만큼 시간 봐주면서 시간대별로 같은 값을 갖는 지점이 있는지 확인
        int time = 0;
        while(time < maxLen){
            int[][] map = new int[101][101]; // 시간대 마다 새로운 배열 만들기.
            
            for (List<int[]> path : allRobotRoute){
                if (time >= path.size()) {
                    continue;
                }
                
                int r = path.get(time)[0];
                int c = path.get(time)[1]; 
                
                map[r][c]++;
                
            } // 표시를 다 끝내고, 이후에 세야함
            
            for (int i=1; i<=100; i++){
                for (int j=1; j<=100; j++){
                    if (map[i][j] >= 2){
                        answer++;
                    }
                }
            }
            
            time++;
        }
        
        return answer;
    }
}