import java.util.*;
class Solution {
    
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        Arrays.sort(mats); // 오름차순 정렬
        
        for (int r=0; r<park.length; r++){
            for (int c=0; c<park[0].length; c++){
                
                if (park[r][c].equals("-1")){
                    
                    
                    for (int m=mats.length-1; m>=0; m--){
                        
                        int size = mats[m];
                        if (size <= answer) break;
                        
                        if (r+size <= park.length && c + size <= park[0].length){
                            boolean flag = true; 
                            
                            for (int i=r; i<r+size; i++){
                                 for (int j=c; j<c+size; j++){
                                     
                                     if (!park[i][j].equals("-1")){
                                         flag = false;
                                         break;
                                     }
                                     
                                 }
                                if(!flag) break;
                            }
                            if (flag) {
                            answer = Math.max(answer, size);
                            break;
                        }
                        }
                        
                        
                        
                    }
                    
                    
                }
                
            }
        }
        
        
        
        
        
        return answer;
    }
}