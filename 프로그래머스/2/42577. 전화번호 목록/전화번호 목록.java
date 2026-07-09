import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        HashMap<String, Integer> phones = new HashMap<>();
        
        for (String num : phone_book){
            phones.put(num, 1);
        }
        for (String num : phone_book){
        
            for (int i=1; i<num.length(); i++){
                if(phones.containsKey(num.substring(0, i))){
                    return false;
                }
            }
        }
        
        
        return answer;
    }
}