import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 어차피 정수값만 넣는 것이므로 바로 Collections.reverseOrder 써주기 고고 하면 됨.
        
        for (int num : priorities){
            pq.add(num);
            
        }
        
        while(!pq.isEmpty()){
            for (int i=0; i < priorities.length; i++){
                if(priorities[i] == pq.peek()){
                    pq.poll();
                    answer++;
                    
                    if(i==location){
                        return answer; 
                    }
                }
            }
        }
        
        
        return answer;
        
    }
}