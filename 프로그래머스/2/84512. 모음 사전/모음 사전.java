import java.util.*;

class Solution {
    static String[] vowels = {"A","E","I","O","U"};
    public int solution(String word) {
        
        
        List<String> dic = new ArrayList<>();
        boolean[] visited = new boolean[5];
        makeDic("", dic);
        
        int answer = dic.indexOf(word);
        return answer;
    }
    
    public void makeDic(String current, List<String> dic){
        
        if (!dic.contains(current)){
            dic.add(current);
        }
        
        for (int i=0; i<5; i++){
             String newWord = current + vowels[i];
            if (newWord.length() < 6){
                
                makeDic(newWord, dic);
            }
            
        }
        
    }
}