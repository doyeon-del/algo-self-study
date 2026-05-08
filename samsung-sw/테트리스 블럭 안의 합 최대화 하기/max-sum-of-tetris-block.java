import java.util.*; 

public class Main {

    static int[] dr ={-1,1,0,0};
    static int[] dc = {0,0,-1,1}; 

    static int n, m, max;
    static int[][] map; 

    static boolean visited[][];


    public static void main(String[] args) {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); 
        m = sc.nextInt(); 

        map = new int[n][m]; 

        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                map[i][j] = sc.nextInt(); 
            }
        }
        max = 0; 
        visited = new boolean[n][m]; 

        for (int r=0; r<n; r++){
            for (int c=0; c<m; c++){
                visited[r][c] = true; 
                dfs(r,c,1,map[r][c]);
                visited[r][c] = false; 

                checkShape(r,c); 
            }
        }

        System.out.println(max);

        
    }

    public static void checkShape(int r, int c){
        int totalSum = map[r][c];
        int wings = 0; // 인접한 칸의 개수
        int minWing = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 범위를 벗어나지 않는 경우에만 합산
            if (nr >= 0 && nc >= 0 && nr < n && nc < m) {
                wings++;
                totalSum += map[nr][nc];
                minWing = Math.min(minWing, map[nr][nc]); // 가장 작은 값 기억
            }
        }

        // 여기 로직이 어려웠음. 
        // 인접한 칸이 3개 미만이면 'ㅜ'자 형태를 만들 수 없음
        if (wings < 3) return;

        // 인접한 칸이 4개라면, 그중 가장 작은 값을 하나 빼서 'ㅜ'자 중 최대값 유지
        if (wings == 4) {
            totalSum -= minWing;
        }

        max = Math.max(max, totalSum);

    }

    public static void dfs(int r, int c, int count, int sum){

        if (count==4){
            max = Math.max(max, sum);
            return; 
        }

        for (int dic=0; dic<4; dic++){
            int nr = r + dr[dic];
            int nc = c + dc[dic];

            if(nr>=0 && nc>=0 && nr<n && nc<m && !visited[nr][nc]){
                visited[nr][nc] = true; 
                dfs(nr, nc, count + 1, sum + map[nr][nc]);
                visited[nr][nc] = false; 
            }

        }

    }
}