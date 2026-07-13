import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Deque<Character> stack = new ArrayDeque<>();
        
        for (char c : s.toCharArray()){
            if (c=='('){ // 여는 괄호는 무조건 stack에 넣기. 
                stack.addLast(c);
                
            } else { // 현재 닫는 기호
                
                if(stack.isEmpty()){
                    return false;
                } else {
                    
                    stack.pollLast();
                }
                
                
                
            }
        }
        
        if (!stack.isEmpty()) return false;

        return answer;
    }
}