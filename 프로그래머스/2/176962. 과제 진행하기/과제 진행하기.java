import java.util.*;
class Plan implements Comparable<Plan>{
    
    String assign;
    int startTime;
    int length;
    
    public Plan(String assign, int startTime, int length){
        this.assign = assign;
        this.startTime = startTime;
        this.length = length;
        
    }
    
    @Override
    public int compareTo(Plan o){
        return Integer.compare(this.startTime, o.startTime); // 오름 차순 정렬 
    }
    
    
    
}


class Solution {
    public String[] solution(String[][] plans) {
        
        
        List<Plan> planList = new ArrayList<>();
        
    
        for (String[] p : plans){
            String subject = p[0];
            String temp = p[1];
            int playtime = Integer.parseInt(p[2]); // 과제 길이 
            
            int hour = Integer.parseInt(temp.substring(0,2));
            int minute = Integer.parseInt(temp.substring(3,5));
            
            int startTime = hour * 60 + minute; // 분 기준으로 계산하기
            
            planList.add(new Plan(subject, startTime, playtime));
        }
        
        /// 시작하시는 시간 기준으로 내림 차순 정렬.
        Collections.sort(planList); 
        
        // 작업 과정 스택에서 확인하기
        Deque<Plan> stopStack = new ArrayDeque<>();      
        List<String> answerList = new ArrayList<>();
        
        for (int i=0; i<planList.size() - 1; i++){
            
            Plan current = planList.get(i);
            Plan next = planList.get(i+1); // 다음 플랜 미리 확인하기
            
            int endTime = current.startTime + current.length; // 현재 과제가 끝나는 시간? 근데 이거랑 다음 플랜이랑 비교하면서 진행해야 함. 
            
            // 케이스1 : 현재 과제가 다음 과제 시작 전에 끝날 경우
            if (endTime < next.startTime){
                answerList.add(current.assign); // 과제 답에다가 추가해주고
                
                int extraTime = next.startTime - endTime; // 추가 시간이 확보된다면
                
                while (!stopStack.isEmpty() && extraTime > 0){ // 남은 시간 동안 스택 과제 털기
                    Plan stopped = stopStack.peek();
                    
                    if (stopped.length <= extraTime){
                        extraTime -= stopped.length;
                        answerList.add(stopStack.pop().assign);
                        
                    } else {
                        stopped.length -= extraTime;
                        extraTime = 0;
                    }
                    
                    
                }
            } else if (endTime == next.startTime){ // 다음 과제 시작에 맞춰 끝나는 경우
                answerList.add(current.assign);
                
            } else {
                current.length -= (next.startTime - current.startTime);
                stopStack.push(current);
            }
            
            
        }
        
        // 루프가 다끝났다면 마지막 과제 처리 
        Plan last = planList.get(planList.size() - 1);
        answerList.add(last.assign);
        
        while (!stopStack.isEmpty()){
            answerList.add(stopStack.pop().assign);
        }
        
        
        //String[] answer = {};
        
        return answerList.toArray(new String[0]);
    }
}