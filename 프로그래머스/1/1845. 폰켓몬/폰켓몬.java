import java.util.*;

class Solution {
    public int solution(int[] nums) {
        
        //int n = nums.length;
        int pickN = nums.length/2;
        
        Set<Integer> pocketSet = new HashSet<>();
        for (int n : nums){
            pocketSet.add(n);
        }
        
        if (pocketSet.size() <= pickN){
            return pocketSet.size();
        } else {
            return pickN;
        }
        
        //int answer = 0;
        //return answer;
    }
}