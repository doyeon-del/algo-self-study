import java.util.*;
class Solution {
    public int[] solution(int[] array) {
        //array.sort();
        List<Integer> arrlist = new ArrayList<>();
        for (int n : array){
            arrlist.add(n);            
        }
        
        Arrays.sort(array);
        int maxNum = array[array.length - 1];
        
        
        int[] answer = new int[2];
        answer[0] = maxNum;
        answer[1] = arrlist.indexOf(maxNum);
        return answer;
    }
}