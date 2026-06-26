import java.util.*;
class Solution {
    List<String> dic = new ArrayList<>();
    static char[] vowels = { 'A', 'E', 'I', 'O', 'U'};
    
    public int solution(String word) {
        
        makeDic("");
        
        return dic.indexOf(word) + 1;
    }
    
    public void makeDic(String current){
        
        if(!current.equals("")){
            dic.add(current);
        }
        
        if(current.length() == 5) return;
        
        for (int i=0; i<vowels.length; i++){
            makeDic(current + vowels[i]);
        }
        
    }
}