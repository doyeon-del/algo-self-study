import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        HashMap<String, Integer> runners = new HashMap<>();
        
        for (String p : participant){
            runners.put(p, runners.getOrDefault(p, 0)+1);
        }
        
        for (String c : completion){
            int count = runners.get(c);
            runners.put(c, count - 1);
        }
        
        
        Set<String> keySet = runners.keySet();
        for (String k : keySet){
            if (runners.get(k) > 0){
                return k;
            }
        }
        
        return answer;
    }
}