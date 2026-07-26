import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
       int[] cnt = new int[n+2];
        Arrays.fill(cnt, 1); // 전체를 1로 채우기?
        cnt[0] = 0; 
        cnt[n+1] = 0;
        
        for (int x : lost){
            cnt[x]--; // 도난 당한 학생
        }
        for (int y : reserve){
            cnt[y]++; // 여벌
        }
        
        int answer = 0;
        for (int i=1; i<=n; i++){
            if (cnt[i]==0){
                if(cnt[i-1]==2){
                    cnt[i-1]--;
                    cnt[i]++;
                } else if (cnt[i+1]==2){
                    cnt[i+1]--;
                    cnt[i]++;
                }
                
            }
            if (cnt[i]>=1) answer++;
        }
        return answer;
    }
}