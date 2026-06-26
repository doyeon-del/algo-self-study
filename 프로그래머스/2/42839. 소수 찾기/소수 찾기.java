import java.util.*;
class Solution {
    
    HashSet<Integer> numSet = new HashSet<>();
    
    public int solution(String numbers) {
        int answer = 0;
        
        char[] temp = numbers.toCharArray();
        int[] nums = new int[temp.length];
        
        for (int i=0; i<temp.length; i++){
            nums[i] = temp[i] - '0';
        }
        
        boolean[] visited = new boolean[numbers.length()];
        dfs("", nums, visited);
        
        
        for (int num : numSet){
            
            if(isPrime(num)) answer++;
            
        }
        
        return answer;
    }
    
    public void dfs(String current, int[] nums, boolean[] visited){
        
        if(!current.isEmpty()) {
            numSet.add(Integer.parseInt(current));
        }
        
        for (int i=0; i<nums.length; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(current + nums[i], nums, visited);
                visited[i] = false;
            }
            
        }
        
    }
    
    public boolean isPrime(int num){
        
        if(num < 2) return false;
        
        for (int i=2; i<num; i++){
            if(num%i==0) return false;
        }
        
        return true;
    }
}