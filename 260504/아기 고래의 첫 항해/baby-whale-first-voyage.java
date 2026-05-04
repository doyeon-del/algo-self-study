import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    // 상(0), 우(1), 하(2), 좌(3)
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if(!sc.hasNext()) return;
        N = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 초기 방향 설정 (1:상, 2:하, 3:좌, 4:우)
        int cd = 0;
        if (d == 1) cd = 0;
        else if (d == 2) cd = 2;
        else if (d == 3) cd = 3;
        else if (d == 4) cd = 1;

        int cr = r, cc = c;
        System.out.println(cr + " " + cc);
        visited[cr][cc] = true;

        while (true) {
            // --- 1단계: 인접 탐험 (미방문 바다가 옆에 있는 동안 무한 반복) ---
            while (true) {
                int nextD = -1;
                // 우선순위: 직진, 좌회전, 우회전, 180도
                int[] pDirs = {cd, (cd + 3) % 4, (cd + 1) % 4, (cd + 2) % 4};
                
                for (int nd : pDirs) {
                    int nr = cr + dr[nd];
                    int nc = cc + dc[nd];
                    if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && map[nr][nc] == 0 && !visited[nr][nc]) {
                        nextD = nd;
                        break;
                    }
                }

                if (nextD != -1) {
                    cr += dr[nextD];
                    cc += dc[nextD];
                    cd = nextD;
                    visited[cr][cc] = true;
                    System.out.println(cr + " " + cc);
                } else {
                    break; // 더 이상 옆에 미방문 바다가 없음
                }
            }

            // --- 2단계: 가장 가까운 바다로 이동 ---
            int[] target = findBestTarget(cr, cc);
            if (target == null) break; // 탐험할 바다가 더 이상 없음

            int tr = target[0];
            int tc = target[1];

            // 목적지까지 한 칸씩 이동
            while (cr != tr || cc != tc) {
                // 목적지까지의 최단 거리 맵을 기반으로 다음 칸 결정
                int[][] distMap = getDistMap(tr, tc);
                int currentDist = distMap[cr][cc];
                
                int nextD = -1;
                // 이동 우선순위: 좌(3), 하(2), 우(1), 상(0)
                int[] movePriority = {3, 2, 1, 0};
                for (int pd : movePriority) {
                    int nr = cr + dr[pd];
                    int nc = cc + dc[pd];
                    if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && map[nr][nc] == 0) {
                        if (distMap[nr][nc] == currentDist - 1) {
                            nextD = pd;
                            break;
                        }
                    }
                }

                // 실제 이동
                cr += dr[nextD];
                cc += dc[nextD];
                cd = nextD;

                // 이미 방문한 곳이면 출력 안 함, 새로운 곳(목적지)이면 출력
                if (!visited[cr][cc]) {
                    visited[cr][cc] = true;
                    System.out.println(cr + " " + cc);
                }
            }
            // 도착 후 다시 1단계로 진입
        }
    }

    // BFS로 가장 가까운 미방문 바다 탐색 (행/열 우선순위 적용)
    static int[] findBestTarget(int sr, int sc) {
        Deque<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[N + 1][N + 1];
        for(int i=1; i<=N; i++) Arrays.fill(dist[i], -1);
        
        q.add(new int[]{sr, sc});
        dist[sr][sc] = 0;

        int minD = Integer.MAX_VALUE;
        List<int[]> candidates = new ArrayList<>();

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];

            if (dist[r][c] > minD) break;

            // 미방문 바다 발견
            if (!visited[r][c]) {
                if (dist[r][c] < minD) {
                    minD = dist[r][c];
                    candidates.clear();
                    candidates.add(new int[]{r, c});
                } else if (dist[r][c] == minD) {
                    candidates.add(new int[]{r, c});
                }
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && map[nr][nc] == 0 && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        if (candidates.isEmpty()) return null;
        // 행 작은 순 -> 열 작은 순 정렬
        candidates.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        return candidates.get(0);
    }

    // 목적지로부터 모든 칸까지의 최단 거리를 구하는 BFS (경로 선택용)
    static int[][] getDistMap(int tr, int tc) {
        int[][] dist = new int[N + 1][N + 1];
        for(int i=1; i<=N; i++) Arrays.fill(dist[i], -1);
        Deque<int[]> q = new ArrayDeque<>();
        
        q.add(new int[]{tr, tc});
        dist[tr][tc] = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];
                if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && map[nr][nc] == 0 && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[curr[0]][curr[1]] + 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }
        return dist;
    }
}