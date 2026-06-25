import java.util.*;
class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        int wh = wallet[0];
        int ww = wallet[1];
        
        int bh = bill[0];
        int bw = bill[1];
        
        int wMin = Math.min(wh, ww);
        int wMax = Math.max(wh, ww);
        
        int bMin = Math.min(bh, bw);
        int bMax = Math.max(bh, bw);
        
        
        while ((bMin > wMin) || (bMax > wMax)){
            if (bh > bw){
                bh = bh/2;
                bMin = Math.min(bh, bw);
                bMax = Math.max(bh, bw);
            } else {
                bw = bw/2;
                bMin = Math.min(bh, bw);
                bMax = Math.max(bh, bw);
            }
            
            answer++;
        }
        
        return answer;
    }
}