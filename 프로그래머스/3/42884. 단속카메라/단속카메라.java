import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        // 아이디어 -- 진출이 빠른 자동차부터 처리하기
        
        Arrays.sort(routes, (a,b) -> Integer.compare(a[1], b[1]));
        
        int answer = 0;
        int camera = -30001;
        
        for (int[] r : routes){
            if (r[0] > camera){
                
                camera = r[1];
                answer++;
                
            }
        }
        
        
        return answer;
    }
}