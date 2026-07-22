import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        int tYear = Integer.parseInt(today.substring(0,4));
        int tMonth = Integer.parseInt(today.substring(5,7));
        int tDay = Integer.parseInt(today.substring(8,10));
        
        int todayDays = (tYear * 12 * 28) + (tMonth * 28) + tDay;
        
        HashMap<String, Integer> termMap = new HashMap<>();
        for (String term : terms){
            
            // substring 대신에 split쓸껄
            String[] splitTerm = term.split(" ");
            String type = splitTerm[0];
            int length = Integer.parseInt(splitTerm[1]);
                
            //ring type = term.substring(0,1);
            //t length = Integer.parseInt(term.substring(2,3));
            
            termMap.put(type, length);
            
        }
        
        List<Integer> list = new ArrayList<>();
        
        for (int i=0; i<privacies.length; i++){
            String current = privacies[i];
            int cYear = Integer.parseInt(current.substring(0,4));
            int cMonth = Integer.parseInt(current.substring(5,7));
            int cDay = Integer.parseInt(current.substring(8,10));
            
            String ctype = current.substring(11,12);
            
            int termlen = termMap.get(ctype); // 유효기간은 달 기준이긴 함.
            
            int collectDays = (cYear * 12 * 28) + (cMonth * 28) + cDay;
            
            if (collectDays + (termlen * 28) <= todayDays) {
                list.add(i+1);
            }
            
            
            
        }
        
        
        int[] answer = new int[list.size()];
        for (int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}