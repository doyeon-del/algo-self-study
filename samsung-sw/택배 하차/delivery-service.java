import java.util.*;

class Box implements Comparable<Box> {

    int id, r, c, h, w;
    boolean removed = false;

    public Box(int id, int r, int c, int h, int w) {
        this.id = id;
        this.r = r;
        this.c = c;
        this.h = h;
        this.w = w;
    }

    @Override
    public int compareTo(Box o) {
        return this.id - o.id;
    }

}

public class Main {
    static int N, M;
    static int[][] map;


    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N + 1][N + 1];
        List<Box> boxes = new ArrayList<>();  // 박스객체들 채워주기 

        // 시뮬레이션 -------- 택배 번호에 맞게 들어간 것 표시하기 
        // M번 동안 택배 번호, 세로 크기, 가로 크기, 좌측 좌표
        for (int m = 0; m < M; m++) {
            int k = sc.nextInt(); // 택배 번호
            int h = sc.nextInt(); // 세로 크기
            int w = sc.nextInt(); // 가로 크기
            int c = sc.nextInt(); // 좌측 좌표 -> 항상 맨 왼쪽 좌표 기준으로 내려가게 됨.
            int startR = 1;

            while (true) {
                boolean canGoDown = true; 
                int nextBottom = startR + h; // 맨 행 아래 값 표시하기

                if (nextBottom > N) { 
                    canGoDown = false;
                    
                } else {
                    for (int currC = c; currC<c+w; currC++) {
                        if(map[nextBottom][currC]!=0) {
                            canGoDown=false;
                            break;
                        }
                    }
                }
                
                if(canGoDown) {
                    startR++;
                } else {
                    break;
                }

            } // whlie 문 끝

            // 찾은 최종 자리값 기준으로 블록 표시해주기
            for (int r = startR ; r<startR+h; r++) {
                for (int j = c; j < c +w; j++) {
                    map[r][j] = k; // 택배 번호로 표시해주기
                }
            }
            
            boxes.add(new Box(k, startR, c, h, w));

        }

        
        // 택배 하차 로직 
        
        int removedCount = 0;
        boolean turnLeft = true; // true면 왼쪽 하차 차례, false면 오른쪽 하차 차례

        while (removedCount < M) {
            Collections.sort(boxes); // 항상 ID(k) 순서대로 검사하기 위함
            boolean moved = false;

            if (turnLeft) {
                // 왼쪽 하차 시도
                for (Box b : boxes) {
                    if (b.removed) continue;
                    if (canExistLeft(b)) {
                        removeBox(b);
                        applyGravity(boxes);
                        System.out.println(b.id);
                        removedCount++;
                        moved = true;
                        break; // 하나 뺐으면 이번 턴 종료
                    }
                }
                // 왼쪽 턴이 끝나면 (뺐든 못 뺐든) 다음은 오른쪽 턴
                turnLeft = false; 
            } else {
                // 오른쪽 하차 시도
                for (Box b : boxes) {
                    if (b.removed) continue;
                    if (canExistRight(b)) {
                        removeBox(b);
                        applyGravity(boxes);
                        System.out.println(b.id);
                        removedCount++;
                        moved = true;
                        break; // 하나 뺐으면 이번 턴 종료
                    }
                }
                // 오른쪽 턴이 끝나면 다음은 왼쪽 턴
                turnLeft = true;
            }
        }
        
        
        
        
        
        
        
        

    }


    private static void applyGravity(List<Box> boxes) {
        // 1. r 좌표 기준 내림차순 정렬 (바닥에 가까운 것부터 처리)
        Collections.sort(boxes, (b1, b2) -> (b2.r - b1.r));

        for (Box b : boxes) {
            if (b.removed) continue;

            int currentR = b.r;
            int targetR = currentR;

            // 아래로 몇 칸 갈 수 있는지 체크
            while (true) {
                int nextBottom = targetR + b.h;
                if (nextBottom > N) break; // 바닥 도달

                boolean canMove = true;
                for (int col = b.c; col < b.c + b.w; col++) {
                    if (map[nextBottom][col] != 0) {
                        canMove = false;
                        break;
                    }
                }

                if (canMove) targetR++;
                else break;
            }

            // 실제 위치가 변했다면 map 갱신
            if (targetR != currentR) {
                // 기존 위치 지우기
                for (int r = b.r; r < b.r + b.h; r++) {
                    for (int c = b.c; c < b.c + b.w; c++) map[r][c] = 0;
                }
                // 새 위치 정보 갱신
                b.r = targetR;
                // 새 위치에 그리기
                for (int r = b.r; r < b.r + b.h; r++) {
                    for (int c = b.c; c < b.c + b.w; c++) map[r][c] = b.id;
                }
            }
        }
    }

    private static void removeBox(Box b) {
        
        for(int r=b.r; r<b.r+b.h; r++) {
            for (int c=b.c; c<b.c+b.w; c++) {
                map[r][c] = 0; 
            }
        }
        
        b.removed = true; 
        
    }

    private static boolean canExistLeft(Box b) {
        // 박스가 차지하는 모든 행(r ~ r+h-1)에 대해 검사
        for (int row = b.r; row < b.r + b.h; row++) {
            // 박스의 왼쪽 끝(b.c)보다 더 왼쪽 열들을 전부 검사
            for (int col = b.c - 1; col >= 1; col--) {
                if (map[row][col] != 0) return false; // 장애물 발견
            }
        }
        return true;
    }

    private static boolean canExistRight(Box b) {
        for (int row = b.r; row < b.r + b.h; row++) {
            // 박스의 오른쪽 끝(b.c + b.w)부터 오른쪽 끝 열까지 검사
            for (int col = b.c + b.w; col <= N; col++) {
                if (map[row][col] != 0) return false;
            }
        }
        return true;
    }
}