import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for (String str : skill_trees){
            
            String fill = str.replaceAll("[^" + skill + "]", "");
            
            if (skill.indexOf(fill)==0){
                answer++;
            }
            
        }
        
        
        return answer;
    }
}