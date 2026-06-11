import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Deque<Integer> stack = new ArrayDeque<>();
      
        for (int i=0; i<prices.length; i++){
            
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]){
                int idx = stack.pop();
                answer[idx] = i - idx;
            }
            stack.push(i);
            
            
        }
        
        while(!stack.isEmpty()){
            int temp = stack.pop();
            answer[temp] = prices.length - temp - 1;
        }
        
        return answer;
    }
}