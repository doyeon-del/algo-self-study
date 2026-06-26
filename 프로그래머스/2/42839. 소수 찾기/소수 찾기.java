import java.util.*;
class Solution {
    static HashSet<Integer> numSet;
    public int solution(String numbers) {
        int answer = 0;
        numSet = new HashSet<>();
        
        
        char[] temp = numbers.toCharArray();
        int[] nums = new int[temp.length];
        for (int n=0; n<temp.length; n++){
            nums[n] = temp[n] - '0';
        }
        
        boolean[] visited = new boolean[temp.length];
        dfs("", nums, visited);
        for (int num : numSet){
            if (isPrime(num)) answer++;
        }
        
        return answer;
    }
    
    public void dfs(String current, int[] nums, boolean[] visited){
        
        if(!current.equals("")){
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
        
        if (num < 2) return false;
        
        for (int i=2; i<num; i++){
            if(num%i==0) return false;
        }
        
        return true;
    }
}