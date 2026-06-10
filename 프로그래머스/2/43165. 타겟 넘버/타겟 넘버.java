class Solution {
    static int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        
        dfs(numbers, target, 0, 0);
        
        return answer;
    }
    
    public void dfs(int[] numbers, int target, int current, int index){
        
        if (index == numbers.length){
            if (current == target) answer++;
            return;
        }
        
        
        if (index < numbers.length){
            dfs(numbers, target, current - numbers[index], index+1);
            
            dfs(numbers, target, current + numbers[index], index+1);
        
        }

       
        
    }
}