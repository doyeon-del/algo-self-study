import java.util.*;

// 좌표 및 정렬을 위한 Point 클래스
class Point implements Comparable<Point> {
    int r, c;
    
    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
    
    // (r 오름차순, r이 같으면 c 오름차순) 정렬
    @Override
    public int compareTo(Point o) {
        if (this.r == o.r) return Integer.compare(this.c, o.c);
        return Integer.compare(this.r, o.r);
    }
}

class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] game_board, int[][] table) {
        int n = game_board.length;
        
        // 1. game_board에서 빈 공간(0) 추출
        List<List<Point>> emptySpaces = new ArrayList<>();
        boolean[][] visitedBoard = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visitedBoard[i][j] && game_board[i][j] == 0) {
                    emptySpaces.add(extract(game_board, i, j, visitedBoard, 0));
                }
            }
        }
        
        // 2. table에서 퍼즐 조각(1) 추출
        List<List<Point>> blocks = new ArrayList<>();
        boolean[][] visitedTable = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visitedTable[i][j] && table[i][j] == 1) {
                    blocks.add(extract(table, i, j, visitedTable, 1));
                }
            }
        }
        
        // 3. 퍼즐 조각과 빈 공간 매칭
        boolean[] usedBlock = new boolean[blocks.size()];
        int answer = 0;
        
        for (List<Point> empty : emptySpaces) {
            for (int i = 0; i < blocks.size(); i++) {
                if (usedBlock[i]) continue;
                List<Point> block = blocks.get(i);
                
                // 크기(칸 수)가 다르면 채울 수 없음
                if (empty.size() != block.size()) continue;
                
                // 4방향 회전하며 일치 여부 확인
                boolean isMatched = false;
                List<Point> rotatedBlock = block;
                for (int r = 0; r < 4; r++) {
                    if (isSame(empty, rotatedBlock)) {
                        isMatched = true;
                        break;
                    }
                    rotatedBlock = rotate(rotatedBlock); // 90도 회전
                }
                
                if (isMatched) {
                    answer += block.size(); // 채운 칸 수 누적
                    usedBlock[i] = true;    // 사용된 퍼즐 조각 체크
                    break;
                }
            }
        }
        
        return answer;
    }
    
    // BFS로 연결된 영역 추출
    public List<Point> extract(int[][] board, int r, int c, boolean[][] visited, int target) {
        List<Point> list = new ArrayList<>();
        Queue<Point> q = new LinkedList<>();
        
        q.add(new Point(r, c));
        visited[r][c] = true;
        
        while (!q.isEmpty()) {
            Point cur = q.poll();
            list.add(cur);
            
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                
                if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length) {
                    if (!visited[nr][nc] && board[nr][nc] == target) {
                        visited[nr][nc] = true;
                        q.add(new Point(nr, nc));
                    }
                }
            }
        }
        
        return normalize(list);
    }
    
    // (0, 0) 기준으로 좌표 이동 후 정렬
    public List<Point> normalize(List<Point> list) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;

        for (Point p : list) {
            minR = Math.min(minR, p.r);
            minC = Math.min(minC, p.c);
        }

        List<Point> normalized = new ArrayList<>();
        for (Point p : list) {
            normalized.add(new Point(p.r - minR, p.c - minC));
        }
        Collections.sort(normalized);
        return normalized;
    }
    
    // 시계 방향 90도 회전
    public List<Point> rotate(List<Point> list) {
        List<Point> rotated = new ArrayList<>();
        for (Point p : list) {
            rotated.add(new Point(p.c, -p.r));
        }
        return normalize(rotated);
    }
    
    // 두 정규화된 좌표 리스트가 완전히 동일한지 비교
    private boolean isSame(List<Point> p1, List<Point> p2) {
        for (int i = 0; i < p1.size(); i++) {
            if (p1.get(i).r != p2.get(i).r || p1.get(i).c != p2.get(i).c) {
                return false;
            }
        }
        return true;
    }
}